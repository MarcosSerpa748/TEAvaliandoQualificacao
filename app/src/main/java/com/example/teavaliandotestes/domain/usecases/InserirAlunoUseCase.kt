package com.example.teavaliandotestes.domain.usecases

import com.example.teavaliandotestes.domain.exception.CampoDataNascimentoInvalidaException
import com.example.teavaliandotestes.domain.exception.CampoNomeInvalidoException
import com.example.teavaliandotestes.domain.exception.CampoProfessoraInvalidoException
import com.example.teavaliandotestes.domain.exception.CampoTurmaInvalidoException
import com.example.teavaliandotestes.domain.repositorios.AlunoRepositorio
import java.time.LocalDate
import javax.inject.Inject

class InserirAlunoUseCase@Inject constructor(private val alunoRepositorio: AlunoRepositorio){

    suspend operator fun invoke(nomeAluno:String, dataNascimento: LocalDate?, nomeProfessora:String, turma: String):Long{
        if (nomeAluno.isBlank()){
            throw CampoNomeInvalidoException("Campo do nome está vazio")
        }
        if (dataNascimento == null){
            throw CampoDataNascimentoInvalidaException("Campo de data vazio!")
        }
        if (nomeProfessora.isBlank()){
            throw CampoProfessoraInvalidoException("O campo nome da professora está vazio!")
        }
        if (turma.isBlank()){
            throw CampoTurmaInvalidoException("O campo turma está vazio!")
        }

        return alunoRepositorio.inserirAluno(nomeAluno,dataNascimento,nomeProfessora,turma)
    }
}