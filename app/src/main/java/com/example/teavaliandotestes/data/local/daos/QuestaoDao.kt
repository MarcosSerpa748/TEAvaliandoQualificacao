package com.example.teavaliandotestes.data.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.teavaliandotestes.data.local.entidades.QuestaoEntity
import com.example.teavaliandotestes.domain.enums.CategoriaQuestao
import kotlinx.coroutines.flow.Flow

@Dao
interface QuestaoDao {

    @Query("SELECT * FROM questoes WHERE categoria = :valor ORDER  BY RANDOM() LIMIT 5")
    suspend fun pegarQuestaoPorCategoria(valor: CategoriaQuestao):List<QuestaoEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserirQuestoes(valor:List<QuestaoEntity>)
}