package com.example.teavaliandotestes.data.combinadorepositorios

import com.example.teavaliandotestes.data.repositorio.ResultadoProvaRepositorioimpl
import com.example.teavaliandotestes.domain.repositorios.ResultadoProvaRepositorio
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class JuncaoRepositoriosResultadoProva{

    @Binds
    abstract fun entregarImplementacao(implementacao: ResultadoProvaRepositorioimpl): ResultadoProvaRepositorio
}