package com.example.teavaliandotestes.presentation.uistates

import java.time.LocalDate

data class TelaCadastroUIState(
    val nomeAluno:String = "",
    val dataNascimento: LocalDate? = null,
    val nomeProfessora:String = "",
    val turma:String = "",
    val abrirTecladoData:Boolean = false,
    val erroCampoNome:Boolean = false,
    val erroCampoDataNascimento:Boolean = false,
    val erroCampoProfessora:Boolean = false,
    val erroCampoTurma:Boolean = false
)