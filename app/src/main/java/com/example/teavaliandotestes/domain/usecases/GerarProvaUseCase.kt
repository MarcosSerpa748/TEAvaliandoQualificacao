package com.example.teavaliandotestes.domain.usecases

import com.example.teavaliandotestes.data.local.entidades.QuestaoEntity
import com.example.teavaliandotestes.domain.enums.CategoriaQuestao
import com.example.teavaliandotestes.domain.repositorios.QuestaoRepositorio
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject


class GerarProvaUseCase@Inject constructor(private val repositorio: QuestaoRepositorio){

    suspend operator fun invoke():List<QuestaoEntity> {
        val questoesEscrita = repositorio.pegarQuestaoPorCategoria(CategoriaQuestao.ESCRITA)
        val questoesLeitura = repositorio.pegarQuestaoPorCategoria(CategoriaQuestao.LEITURA)
        val questoesOral = repositorio.pegarQuestaoPorCategoria(CategoriaQuestao.LINGUAGEM_ORAL)
        val questoesFonologia = repositorio.pegarQuestaoPorCategoria(CategoriaQuestao.CONSCIENCIA_FONOLOGICA)

        val todasQuestoes = mutableListOf<QuestaoEntity>()

        todasQuestoes.addAll(questoesLeitura)
        todasQuestoes.addAll(questoesEscrita)
        todasQuestoes.addAll(questoesOral)
        todasQuestoes.addAll(questoesFonologia)

        return todasQuestoes
    }
}