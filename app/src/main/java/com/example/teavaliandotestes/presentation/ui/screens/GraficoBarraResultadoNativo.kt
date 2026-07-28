package com.example.teavaliandotestes.presentation.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun GraficoBarrasResultadoNativo(
    pontosFonologica: Int,
    pontosEscrita: Int,
    pontosLeitura: Int,
    pontosOral: Int
) {
    val dados = listOf(
        "Leitura" to pontosLeitura,
        "Escrita" to pontosEscrita,
        "Fonol." to pontosFonologica,
        "Oral" to pontosOral
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(700.dp)
            .padding(vertical = 16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(
                text = "Desempenho por Categoria",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.Bottom
            ) {
                dados.forEach { (categoria, nota) ->
                    BarraNativa(categoria = categoria, nota = nota)
                }
            }
        }
    }
}

