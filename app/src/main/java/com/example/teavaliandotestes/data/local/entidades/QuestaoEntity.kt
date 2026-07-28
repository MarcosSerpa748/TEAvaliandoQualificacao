package com.example.teavaliandotestes.data.local.entidades

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.teavaliandotestes.data.dataclass.OpcaoQuestao
import com.example.teavaliandotestes.domain.enums.CategoriaQuestao


@Entity(tableName = "questoes")
data class QuestaoEntity(
    @PrimaryKey(autoGenerate = true)val id:Long = 0,
    val enunciado:String,
    val arquivoLeituraEnunciado:String,
    val textApoio:String?,
    val arquivoLeituraTexto:String?,
    val imagemApoio:String?,
    val categoria: CategoriaQuestao,
    val itens: List<OpcaoQuestao>,
    val indicieItenCorreto:Int
)
