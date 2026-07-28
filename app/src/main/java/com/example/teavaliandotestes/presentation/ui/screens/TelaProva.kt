package com.example.teavaliandotestes.presentation.ui.screens

import android.media.AudioAttributes
import android.media.SoundPool
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.teavaliandotestes.R
import com.example.teavaliandotestes.presentation.viewmodels.TelaProvaViewModel

@Composable
fun TelaProva(navegar:(Long) -> Unit, viewModel: TelaProvaViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val contexto = LocalContext.current

    val atributos = AudioAttributes.Builder()
        .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
        .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
        .build()

    val soundPool =  remember{ SoundPool.Builder().setMaxStreams(5).setAudioAttributes(atributos).build() }

    val somClique = remember { soundPool.load(contexto,R.raw.select_006,1) }
    val somConfirmacao = remember { soundPool.load(contexto,R.raw.confirmation_002,1) }

    DisposableEffect(Unit) {
        onDispose {
            soundPool.release()
        }
    }

    if (uiState.prova.isEmpty()){
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            CircularProgressIndicator(color = Color.Black)
        }
        return
    }
    val questaoAtual = uiState.questaoAtual

    val progressoAtual = (uiState.indicieQuestaoAtual).toFloat() / uiState.prova.size.toFloat()

    val progressoAnimado by animateFloatAsState(targetValue = progressoAtual, animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec, label = "Barra de progresso")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(top = 80.dp, start = 20.dp,end = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LinearProgressIndicator(
            progress = {progressoAnimado},
            color = Color(0xFF4CAF50),
            trackColor = Color.Gray,
            gapSize = 0.dp,
            drawStopIndicator = {},
            modifier = Modifier.height(8.dp).fillMaxWidth())
        Spacer(Modifier.size(10.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = questaoAtual.enunciado,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            BotaoOuvirEnunciado(questaoAtual.arquivoLeituraEnunciado)
        }
        Spacer(modifier = Modifier.height(16.dp))

        if (questaoAtual.imagemApoio != null) {
            val idImagemApoio = remember(questaoAtual.imagemApoio) {
                contexto.resources.getIdentifier(questaoAtual.imagemApoio, "drawable", contexto.packageName)
            }
            Image(
                painter = painterResource(id = idImagemApoio),
                contentDescription = "Imagem de apoio",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))

        }

        if (questaoAtual.textApoio != null) {
            Card(
                colors = CardDefaults.cardColors(containerColor = Color.White),
                modifier = Modifier.fillMaxWidth(),
                border = BorderStroke(1.dp,Color.LightGray)
                ){

                Text(
                        text = questaoAtual.textApoio,
                        modifier = Modifier.padding(16.dp),
                        fontSize = 16.sp
                    )
                BotaoOuvirEnunciado(questaoAtual.arquivoLeituraTexto!!)

            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)){

            itemsIndexed(questaoAtual.itens){ index, elemento ->
                CartaoOpcao(
                    opcao = elemento,
                    selecionada = uiState.itemSelecionado == index,
                    onClick = {
                        viewModel.selecionarOpcao(index)
                        soundPool.play(somClique,1f,1f,1,0,1f)})
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick =
                {
                    viewModel.confirmarResposta()
                    soundPool.play(somConfirmacao,1f,1f,1,0,1f)
                },
            enabled = uiState.itemSelecionado != null,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text(
                text = if (uiState.indicieQuestaoAtual == uiState.prova.size -1) "FINALIZAR PROVA" else "CONFIRMAR E AVANÇAR",
                fontSize = 18.sp
            )
        }
        LaunchedEffect(uiState.provaFinalizada) {
            if (uiState.provaFinalizada) {
                viewModel.permitirNavegacao.collect {
                    navegar(it)
                }
            }
        }
    }
}