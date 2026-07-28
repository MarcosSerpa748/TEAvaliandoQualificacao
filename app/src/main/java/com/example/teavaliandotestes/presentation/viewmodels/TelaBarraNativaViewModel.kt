package com.example.teavaliandotestes.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.teavaliandotestes.presentation.uistates.TelaBarraNativaUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class TelaBarraNativaViewModel@Inject constructor(): ViewModel(){

    private val _ativarAnimacao = MutableStateFlow(false)

    val uiState = _ativarAnimacao.map {
        TelaBarraNativaUIState(ativarAnimacao = it)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = TelaBarraNativaUIState()
    )

    fun alterarValor(valor: Boolean){
        _ativarAnimacao.value = valor
    }
}