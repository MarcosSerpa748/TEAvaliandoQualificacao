package com.example.teavaliandotestes.data.combinadorepositorios

import com.example.teavaliandotestes.domain.repositorios.AlunoRepositorio
import com.example.teavaliandotestes.data.repositorio.AlunoRepositorioimpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class JuncaoRepositoriosAluno{

    @Binds
    abstract fun recebaImplementacao(repositorio: AlunoRepositorioimpl): AlunoRepositorio
}