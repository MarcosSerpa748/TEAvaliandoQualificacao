package com.example.teavaliandotestes.domain.usecases

import com.example.teavaliandotestes.data.local.entidades.AlunoEntity
import com.example.teavaliandotestes.domain.repositorios.AlunoRepositorio
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ExibirAlunosUseCase@Inject constructor(private val alunoRepositorio: AlunoRepositorio){

    operator fun invoke(): Flow<List<AlunoEntity>> {
        return alunoRepositorio.todosAlunos()
    }
}