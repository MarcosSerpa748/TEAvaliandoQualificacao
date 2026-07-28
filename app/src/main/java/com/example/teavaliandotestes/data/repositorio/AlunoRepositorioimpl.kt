package com.example.teavaliandotestes.data.repositorio

import com.example.teavaliandotestes.data.local.daos.AlunoDAO
import com.example.teavaliandotestes.data.local.entidades.AlunoEntity
import com.example.teavaliandotestes.domain.repositorios.AlunoRepositorio
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import javax.inject.Inject


class AlunoRepositorioimpl@Inject constructor(private val dao: AlunoDAO): AlunoRepositorio
{
    override suspend fun inserirAluno(nome: String, dataNascimento: LocalDate, nomeProfessora: String, turma: String):Long{

        val aluno = AlunoEntity(nome = nome,dataNascimento = dataNascimento, nomeProfessora = nomeProfessora,turma = turma)
        return dao.adicionarAluno(aluno)
    }

    override suspend fun deletarAluno(aluno: AlunoEntity){
        dao.deletarAluno(aluno)
    }

    override fun todosAlunos(): Flow<List<AlunoEntity>> {
        return dao.todosAlunos()
    }

}