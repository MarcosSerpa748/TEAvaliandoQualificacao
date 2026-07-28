package com.example.teavaliandotestes.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.teavaliandotestes.presentation.viewmodels.TelaRelatorioViewModel

@Composable
fun TelaRelatorio(navegar:(Unit) -> Unit,viewModel: TelaRelatorioViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(top = 50.dp,start = 16.dp,end = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Relatório de Avaliação",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 24.dp)
        )
        GraficoBarrasResultadoNativo(
            pontosLeitura = uiState.pontosLeitura,
            pontosEscrita = uiState.pontosEscrita,
            pontosFonologica = uiState.pontosFonologia,
            pontosOral = uiState.pontosOral
        )

        Button(
            onClick = {
                viewModel.navegar()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Icon(imageVector = Icons.Default.Home, contentDescription = "Voltar ao Início")
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Finalizar e Voltar ao Início", fontSize = 18.sp)
        }
        LaunchedEffect(Unit) {
            viewModel.validarNavegacao.collect {permissao ->
                navegar(permissao)
            }
        }

    }
}