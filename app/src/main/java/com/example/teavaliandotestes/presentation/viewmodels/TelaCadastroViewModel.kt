package com.example.teavaliandotestes.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.teavaliandotestes.data.dataclass.PrimeiroCombineTelaCadastro
import com.example.teavaliandotestes.domain.exception.CampoDataNascimentoInvalidaException
import com.example.teavaliandotestes.domain.exception.CampoNomeInvalidoException
import com.example.teavaliandotestes.domain.exception.CampoProfessoraInvalidoException
import com.example.teavaliandotestes.domain.exception.CampoTurmaInvalidoException
import com.example.teavaliandotestes.domain.usecases.InserirAlunoUseCase
import com.example.teavaliandotestes.presentation.uistates.TelaCadastroUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject



@HiltViewModel
class TelaCadastroViewModel@Inject constructor(private val inserirAlunoUseCase: InserirAlunoUseCase): ViewModel(){

    private val _nomeAluno = MutableStateFlow("")
    private val _dataNascimento = MutableStateFlow<LocalDate?>(null)
    private val _nomeProfessora = MutableStateFlow("")
    private val _turma = MutableStateFlow("")
    private val _abrirTecladoData = MutableStateFlow(false)
    private val _erroCampoNome = MutableStateFlow(false)
    private val _erroCampoDataNascimento = MutableStateFlow(false)
    private val _erroCampoProfessora = MutableStateFlow(false)
    private val _erroCampoTurma = MutableStateFlow(false)

    val primeiroCombine = combine(
        _abrirTecladoData,
        _erroCampoNome,
        _erroCampoDataNascimento,
        _erroCampoProfessora,
        _erroCampoTurma
    ){abrirTecladoData,erroCampoNome,erroCampoDataNascimento,erroCampoProfessora,erroCampoTurma ->

        PrimeiroCombineTelaCadastro(
            abrirTecladoData = abrirTecladoData,
            erroCampoNome = erroCampoNome,
            erroCampoDataNascimento = erroCampoDataNascimento,
            erroCampoProfessora = erroCampoProfessora,
            erroCampoTurma = erroCampoTurma)
    }
    val uiState = combine(
        _nomeAluno,
        _dataNascimento,
        _nomeProfessora,
        _turma,
        primeiroCombine
    ){nomeAluno,dataNascimento,nomeProfessora,turma,primeiroCombine ->
        TelaCadastroUIState(
            nomeAluno = nomeAluno,
            dataNascimento = dataNascimento,
            nomeProfessora = nomeProfessora,
            turma = turma,
            abrirTecladoData = primeiroCombine.abrirTecladoData,
            erroCampoNome = primeiroCombine.erroCampoNome,
            erroCampoDataNascimento = primeiroCombine.erroCampoDataNascimento,
            erroCampoProfessora = primeiroCombine.erroCampoProfessora,
            erroCampoTurma = primeiroCombine.erroCampoTurma)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = TelaCadastroUIState()
    )

    private val _validarNavegacao = Channel<Long>()
    val validarNavegacao = _validarNavegacao.receiveAsFlow()

    fun alterarNome(valor:String){
        _nomeAluno.value = valor
    }

    fun alterarData(valor: LocalDate?){
        _dataNascimento.value = valor
    }

    fun alterarNomeProfessora(valor:String){
        _nomeProfessora.value = valor
    }
    fun alterarTurma(valor:String){
        _turma.value = valor
    }

    fun alterarEstadoTecladoData(valor:Boolean){
        _abrirTecladoData.value = valor
    }

    fun salvarAluno() {

        viewModelScope.launch {

            try {
                 val idAluno = inserirAlunoUseCase(_nomeAluno.value, _dataNascimento.value,_nomeProfessora.value,_turma.value)
                _validarNavegacao.send(idAluno)

                alterarNome("")
                alterarData(null)
                alterarNomeProfessora("")
                alterarTurma("")

                _abrirTecladoData.value = false
                _erroCampoNome.value = false
                _erroCampoDataNascimento.value = false
                _erroCampoProfessora.value = false
                _erroCampoTurma.value = false

            }catch (e: CampoNomeInvalidoException){
                _erroCampoNome.value = true
                _erroCampoDataNascimento.value = false
                _erroCampoProfessora.value = false
                _erroCampoTurma.value = false
            }catch (e: CampoDataNascimentoInvalidaException){
                _erroCampoDataNascimento.value = true
                _erroCampoNome.value = false
                _erroCampoProfessora.value = false
                _erroCampoTurma.value = false
            }catch(e: CampoProfessoraInvalidoException){
                _erroCampoProfessora.value = true
                _erroCampoNome.value = false
                _erroCampoDataNascimento.value = false
                _erroCampoTurma.value = false
            }catch (e: CampoTurmaInvalidoException){
                _erroCampoTurma.value = true
                _erroCampoNome.value = false
                _erroCampoDataNascimento.value = false
                _erroCampoProfessora.value = false
            }
        }
    }
}
