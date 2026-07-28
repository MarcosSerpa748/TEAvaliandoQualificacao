package com.example.teavaliandotestes.presentation.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.teavaliandotestes.data.dataclass.OpcaoQuestao


@Composable
fun CartaoOpcao(
    opcao: OpcaoQuestao,
    selecionada: Boolean,
    onClick: () -> Unit
) {
    val context = LocalContext.current
    val idImagem = remember(opcao.imagem) {
        context.resources.getIdentifier(opcao.imagem, "drawable", context.packageName)
    }

    val corBorda = if (selecionada) Color.LightGray else Color.LightGray
    val corFundo = if (selecionada) Color(0xFF4CAF50) else Color.White

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .clickable { onClick() },
        border = BorderStroke(if (selecionada) 3.dp else 1.dp, corBorda),
        colors = CardDefaults.cardColors(containerColor = corFundo)
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (idImagem != 0) {
                Image(
                    painter = painterResource(id = idImagem),
                    contentDescription = opcao.textoOpcao,
                    modifier = Modifier.size(64.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
            Text(
                text = opcao.textoOpcao,
                fontWeight = if (selecionada) FontWeight.Bold else FontWeight.Normal,
                fontSize = 16.sp
            )
        }
    }
}