package com.example.teavaliandotestes.presentation.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.teavaliandotestes.domain.enums.CategoriaQuestao
import com.example.teavaliandotestes.domain.usecases.GerarProvaUseCase
import com.example.teavaliandotestes.domain.usecases.InserirResultadoProvaUseCase
import com.example.teavaliandotestes.navigation.rotas.TelaProvaRoute
import com.example.teavaliandotestes.presentation.uistates.TelaProvaUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TelaProvaViewModel@Inject constructor(
    private val gerarProvaUseCase: GerarProvaUseCase,
    private val savedStateHandle: SavedStateHandle,
    private val inserirResultadoProvaUseCase: InserirResultadoProvaUseCase
): ViewModel(){

    private val _indicieQuestaoAtual = MutableStateFlow(0)
    private val _itemSelecionado = MutableStateFlow<Int?>(null)
    private val _provaFinalizada = MutableStateFlow(false)
    private val _prova = flow{
        val prova = gerarProvaUseCase()
        emit(prova)
    }

    val uiState = combine(
        _prova,
        _indicieQuestaoAtual,
        _itemSelecionado,
        _provaFinalizada
    ){prova,indicieQuestaoAtual,itemSelecionado,provaFinalizada ->
        TelaProvaUIState(prova = prova, indicieQuestaoAtual = indicieQuestaoAtual, itemSelecionado = itemSelecionado, provaFinalizada = provaFinalizada)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = TelaProvaUIState()
    )

    private val valoresPassados = savedStateHandle.toRoute<TelaProvaRoute>()
    val idAluno = valoresPassados.idPai

    private val pontuacoes = mutableMapOf<CategoriaQuestao,Int>().withDefault { 0 }

    private val _permitirNavegacao = Channel<Long>()
    val permitirNavegacao = _permitirNavegacao.receiveAsFlow()


    fun selecionarOpcao(valor:Int?){
        _itemSelecionado.value = valor
    }

    fun confirmarResposta(){

        if (_itemSelecionado.value == uiState.value.questaoAtual.indicieItenCorreto){

            val pontosAtuais = pontuacoes.getValue(uiState.value.questaoAtual.categoria)
            pontuacoes[uiState.value.questaoAtual.categoria] = pontosAtuais + 1
        }
        if (_indicieQuestaoAtual.value < uiState.value.prova.size - 1){

            _indicieQuestaoAtual.value += 1
            selecionarOpcao(null)

        }else{
            finalizarProva()
            lancarNavegacao()
        }
    }

    private fun finalizarProva() {
        viewModelScope.launch {
                val pontosEscrita = pontuacoes[CategoriaQuestao.ESCRITA] ?: 0
                val pontosLeitura = pontuacoes[CategoriaQuestao.LEITURA] ?: 0
                val pontuacaoConcienciaFonologica = pontuacoes[CategoriaQuestao.CONSCIENCIA_FONOLOGICA] ?: 0
                val pontuacaoOral = pontuacoes[CategoriaQuestao.LINGUAGEM_ORAL] ?: 0

                inserirResultadoProvaUseCase(
                    idPai = idAluno,
                    pontuacaoEscrita = pontosEscrita,
                    pontuacaoLeitura = pontosLeitura,
                    pontuacaoConcienciaFonologica = pontuacaoConcienciaFonologica,
                    pontuacaoOral = pontuacaoOral)

                _provaFinalizada.value = true
                println("Prova finalizada com sucesso!")

        }
    }

    private fun lancarNavegacao(){
        viewModelScope.launch {
            _permitirNavegacao.send(idAluno)
        }
    }
}