package com.example.teavaliandotestes.data.repositorio

import com.example.teavaliandotestes.data.local.daos.QuestaoDao
import com.example.teavaliandotestes.data.local.entidades.QuestaoEntity
import com.example.teavaliandotestes.domain.enums.CategoriaQuestao
import com.example.teavaliandotestes.domain.repositorios.QuestaoRepositorio
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class QuestaoRepositorioimpl@Inject constructor(private val dao: QuestaoDao): QuestaoRepositorio{

    override suspend fun pegarQuestaoPorCategoria(valor: CategoriaQuestao):List<QuestaoEntity>{
        return dao.pegarQuestaoPorCategoria(valor)
    }

}