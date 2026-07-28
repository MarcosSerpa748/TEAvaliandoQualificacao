package com.example.teavaliandotestes.domain.repositorios

import com.example.teavaliandotestes.data.local.entidades.ResultadoProvaEntity

interface ResultadoProvaRepositorio {

    suspend fun inserirResultadoProva(resultadoProva: ResultadoProvaEntity)
    suspend fun buscarResultados(idPai:Long): List<ResultadoProvaEntity>
}