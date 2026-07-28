package com.example.teavaliandotestes.domain.usecases

import com.example.teavaliandotestes.data.local.entidades.AlunoEntity
import com.example.teavaliandotestes.domain.repositorios.AlunoRepositorio
import javax.inject.Inject

class DeletarAlunoUseCase@Inject constructor(private val alunoRepositorio: AlunoRepositorio){

    suspend operator fun invoke(aluno: AlunoEntity){
        alunoRepositorio.deletarAluno(aluno)
    }
}