package com.example.teavaliandotestes.data.local.entidades

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "resultado_prova",
    foreignKeys = [
        ForeignKey(
            entity = AlunoEntity::class,
            parentColumns = ["id"],
            childColumns = ["idPai"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("idPai")])
data class ResultadoProvaEntity(
    @PrimaryKey(autoGenerate = true)val id:Long = 0,
    val idPai:Long,
    val dataProva: LocalDate = LocalDate.now(),
    val acertosEscrita:Int,
    val acertosLeitura:Int,
    val acertosFonologica:Int,
    val acertosOral:Int
)
