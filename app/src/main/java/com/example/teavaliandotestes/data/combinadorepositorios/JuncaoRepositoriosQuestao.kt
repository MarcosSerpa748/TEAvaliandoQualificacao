package com.example.teavaliandotestes.data.combinadorepositorios

import com.example.teavaliandotestes.data.repositorio.QuestaoRepositorioimpl
import com.example.teavaliandotestes.domain.repositorios.QuestaoRepositorio
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class JuncaoRepositoriosQuestao(){
    @Binds
    abstract fun recebaImplementacao(implementacao: QuestaoRepositorioimpl): QuestaoRepositorio
}