package com.example.teavaliandotestes.presentation.uistates

import com.example.teavaliandotestes.data.local.entidades.QuestaoEntity

data class TelaProvaUIState(
    val prova:List<QuestaoEntity> = emptyList(),
    val indicieQuestaoAtual:Int = 0,
    val itemSelecionado:Int? = null,
    val mensagemError:String? = null,
    val provaFinalizada:Boolean = false
){
    val questaoAtual:QuestaoEntity
        get() = prova[indicieQuestaoAtual]

}
