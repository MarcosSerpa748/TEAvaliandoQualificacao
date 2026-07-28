package com.example.teavaliandotestes.data.dataclass

data class PrimeiroCombineTelaCadastro(
    val abrirTecladoData:Boolean = false,
    val erroCampoNome:Boolean = false,
    val erroCampoDataNascimento:Boolean = false,
    val erroCampoProfessora:Boolean = false,
    val erroCampoTurma:Boolean = false
)