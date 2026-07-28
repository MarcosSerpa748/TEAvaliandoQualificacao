package com.example.teavaliandotestes.data.local.banco

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.teavaliandotestes.data.conversor.Conversor
import com.example.teavaliandotestes.data.local.daos.AlunoDAO
import com.example.teavaliandotestes.data.local.daos.QuestaoDao
import com.example.teavaliandotestes.data.local.daos.ResultadoProvaDao
import com.example.teavaliandotestes.data.local.entidades.AlunoEntity
import com.example.teavaliandotestes.data.local.entidades.QuestaoEntity
import com.example.teavaliandotestes.data.local.entidades.ResultadoProvaEntity

@Database(entities = [AlunoEntity::class, QuestaoEntity::class, ResultadoProvaEntity::class], version = 7)
@TypeConverters(Conversor::class)
abstract class AppDatabase(): RoomDatabase(){

    abstract fun gerarDaoAluno(): AlunoDAO
    abstract fun gerarDaoQuestao(): QuestaoDao
    abstract fun gerarDaoResultadoProva(): ResultadoProvaDao
}