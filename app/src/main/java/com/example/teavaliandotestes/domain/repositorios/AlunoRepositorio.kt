package com.example.teavaliandotestes.domain.repositorios

import com.example.teavaliandotestes.data.local.entidades.AlunoEntity
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface AlunoRepositorio{

    suspend fun inserirAluno(nome:String, dataNascimento: LocalDate, nomeProfessora:String, turma:String):Long
    suspend fun deletarAluno(aluno: AlunoEntity)
    fun todosAlunos(): Flow<List<AlunoEntity>>
}