package com.example.teavaliandotestes.presentation.ui.screens

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.teavaliandotestes.presentation.viewmodels.TelaBarraNativaViewModel

@Composable
fun BarraNativa(categoria: String, nota: Int,viewModel: TelaBarraNativaViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val cor = when (nota) {
        0, 1 -> Color(0xFFF44336)
        2, 3, 4 -> Color(0xFFFFC107)
        5 -> Color(0xFF4CAF50)
        else -> Color.Gray
    }

    val textoTopo = when (nota) {
        0, 1 -> "Não\nConsolid."
        2, 3, 4 -> "Em\nProgresso"
        5 -> "Consolid."
        else -> ""
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier
            .fillMaxHeight()
            .width(75.dp)
    ) {
        val valorAltura = if (nota == 0) 4.dp else (nota * 99).dp
        val alturaAnimada by animateDpAsState(
            targetValue = if (uiState.ativarAnimacao) valorAltura else 0.dp,
            animationSpec = tween(1000)
        )
        Text(
            text = textoTopo,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            lineHeight = 14.sp,
            color = Color.DarkGray
        )
        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = nota.toString(),
            fontSize = 16.sp,
            fontWeight = FontWeight.ExtraBold,
            color = cor
        )
        Spacer(modifier = Modifier.height(4.dp))

        Box(
            modifier = Modifier
                .width(40.dp)
                .height(alturaAnimada)
                .background(
                    color = cor,
                    shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)
                )
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = categoria,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold
        )

        LaunchedEffect(Unit){
            viewModel.alterarValor(true)
        }
    }
}