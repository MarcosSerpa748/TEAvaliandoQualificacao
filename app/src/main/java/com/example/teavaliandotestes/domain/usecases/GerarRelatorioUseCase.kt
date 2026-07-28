package com.example.teavaliandotestes.domain.usecases

import com.example.teavaliandotestes.data.local.entidades.ResultadoProvaEntity
import com.example.teavaliandotestes.domain.repositorios.ResultadoProvaRepositorio
import javax.inject.Inject

class GerarRelatorioUseCase@Inject constructor(private val repositorio: ResultadoProvaRepositorio){

    suspend operator fun invoke(idPai:Long): ResultadoProvaEntity{
        val resultado = repositorio.buscarResultados(idPai = idPai)

        return resultado.first()
    }
}