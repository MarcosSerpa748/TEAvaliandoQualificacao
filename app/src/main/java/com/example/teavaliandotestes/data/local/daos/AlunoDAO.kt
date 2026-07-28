package com.example.teavaliandotestes.data.local.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.teavaliandotestes.data.local.entidades.AlunoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AlunoDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun adicionarAluno(aluno: AlunoEntity):Long

    @Delete
    suspend fun deletarAluno(aluno: AlunoEntity)

    @Query("SELECT * FROM alunos")
    fun todosAlunos(): Flow<List<AlunoEntity>>
}