package com.example.teavaliandotestes.presentation.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.teavaliandotestes.domain.usecases.GerarRelatorioUseCase
import com.example.teavaliandotestes.navigation.rotas.TelaRelatorioRoute
import com.example.teavaliandotestes.presentation.uistates.TelaRelatorioUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class TelaRelatorioViewModel@Inject constructor(
    private val gerarRelatorioUseCase: GerarRelatorioUseCase,
    private val savedStateHandle: SavedStateHandle):ViewModel(){

    val dadosPassados = savedStateHandle.toRoute<TelaRelatorioRoute>()
    val idPai = dadosPassados.idPai

    private val _resultado = flow {
        val resultado = gerarRelatorioUseCase(idPai)
        emit(resultado)
    }

    val uiState = _resultado
        .map {
            TelaRelatorioUIState(pontosLeitura = it.acertosLeitura, pontosEscrita = it.acertosEscrita, pontosFonologia = it.acertosFonologica, pontosOral = it.acertosOral)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = TelaRelatorioUIState()
        )

    private val _validarNavegacao = Channel<Unit>()
    val validarNavegacao = _validarNavegacao.receiveAsFlow()


    fun navegar(){
        viewModelScope.launch {
            _validarNavegacao.send(Unit)
        }
    }
}