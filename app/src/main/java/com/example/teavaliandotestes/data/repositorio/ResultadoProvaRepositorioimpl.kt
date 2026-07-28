package com.example.teavaliandotestes.data.repositorio

import com.example.teavaliandotestes.data.local.daos.ResultadoProvaDao
import com.example.teavaliandotestes.data.local.entidades.ResultadoProvaEntity
import com.example.teavaliandotestes.domain.repositorios.ResultadoProvaRepositorio
import javax.inject.Inject

class ResultadoProvaRepositorioimpl@Inject constructor(private val dao: ResultadoProvaDao): ResultadoProvaRepositorio{

    override suspend fun inserirResultadoProva(resultadoProva: ResultadoProvaEntity) {
        dao.inserirResultadoProva(resultadoProva = resultadoProva)
    }

    override suspend fun buscarResultados(idPai: Long): List<ResultadoProvaEntity> {
        return dao.buscarResultados(idPai = idPai)
    }


}