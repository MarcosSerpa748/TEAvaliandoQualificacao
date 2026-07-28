package com.example.teavaliandotestes.domain.usecases

import com.example.teavaliandotestes.data.local.entidades.ResultadoProvaEntity
import com.example.teavaliandotestes.domain.repositorios.ResultadoProvaRepositorio
import javax.inject.Inject

class InserirResultadoProvaUseCase@Inject constructor(private val repositorio: ResultadoProvaRepositorio) {

    suspend operator fun invoke(idPai:Long,pontuacaoEscrita:Int,pontuacaoLeitura:Int,pontuacaoConcienciaFonologica:Int,pontuacaoOral:Int){

        val resultadoProva = ResultadoProvaEntity(
            idPai = idPai,
            acertosEscrita = pontuacaoEscrita,
            acertosLeitura = pontuacaoLeitura,
            acertosOral = pontuacaoOral,
            acertosFonologica = pontuacaoConcienciaFonologica)

        repositorio.inserirResultadoProva(resultadoProva)
    }
}