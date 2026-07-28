package com.example.teavaliandotestes.presentation.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.teavaliandotestes.R
import com.example.teavaliandotestes.ui.theme.ElectricBlue
import com.example.teavaliandotestes.presentation.viewmodels.TelaCadastroViewModel
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaCadastro(validarNavegacao:(Long) -> Unit, viewModel: TelaCadastroViewModel = hiltViewModel()) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy")

        Column(
            Modifier
                .fillMaxSize()
                .paint(painter = painterResource(id = R.drawable.background_cadastro), contentScale = ContentScale.Crop)
                .verticalScroll(rememberScrollState())
                .imePadding(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(R.drawable.img),
                contentDescription = "Título da tela de login",
                Modifier
                    .height(140.dp)
                    .width(250.dp)
                    .clip(RoundedCornerShape(15.dp))
            )
            Spacer(Modifier.size(150.dp))

            Image(
                painter = painterResource(R.drawable.teuzinho),
                contentDescription = "teuzinho",
                Modifier
                    .height(230.dp)
                    .width(400.dp)
            )

            OutlinedTextField(
                value = uiState.nomeAluno,
                onValueChange = { viewModel.alterarNome(it) },
                shape = RoundedCornerShape(25.dp),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Face,
                        contentDescription = null,
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    focusedBorderColor = Color.Red,
                    unfocusedBorderColor = Color.Red,
                    cursorColor = Color.Black,
                    focusedLeadingIconColor = Color.Black,
                    unfocusedLeadingIconColor = Color.Black,
                    errorBorderColor = Color.Red,
                    errorLeadingIconColor = Color.Red,
                    errorTextColor = Color.Black,
                    errorCursorColor = Color.Black,
                    errorSupportingTextColor = Color.Red,
                    errorContainerColor = Color.White
                ),
                placeholder = { Text(text = "Nome do aluno:") },
                isError = uiState.erroCampoNome,
                supportingText = {
                    if (uiState.erroCampoNome){
                        Text(text = "O campo de texto está vazio!", fontWeight = FontWeight.ExtraBold)
                    }
                }
            )
            OutlinedTextField(
                value = uiState.dataNascimento?.format(formatador) ?: "",
                onValueChange = {},
                readOnly = true,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.DateRange,
                        contentDescription = null,
                    )
                },
                placeholder = { Text(text = "Nascimento do aluno:") },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    focusedBorderColor = Color.Green,
                    unfocusedBorderColor = Color.Green,
                    cursorColor = Color.Black,
                    focusedLeadingIconColor = Color.Black,
                    unfocusedLeadingIconColor = Color.Black,
                    errorContainerColor = Color.White,
                    errorTextColor = Color.Black,
                    errorCursorColor = Color.Black,
                    errorSupportingTextColor = Color.Red,
                    errorBorderColor = Color.Red,
                    errorLeadingIconColor = Color.Red
                ),
                shape = RoundedCornerShape(25.dp),
                isError = uiState.erroCampoDataNascimento,
                supportingText = {
                    if (uiState.erroCampoDataNascimento){
                        Text(text = "Campo de data vazio!", fontWeight = FontWeight.ExtraBold)
                    }
                },
                interactionSource = remember { MutableInteractionSource() }
                    .also { source ->
                        LaunchedEffect(source) {
                            source.interactions.collect {
                                if (it is PressInteraction.Release) {
                                    viewModel.alterarEstadoTecladoData(true)
                                }
                            }
                        }
                    }
            )

            if (uiState.abrirTecladoData) {
                val datePickerState = rememberDatePickerState()

                DatePickerDialog(
                    onDismissRequest = { viewModel.alterarEstadoTecladoData(false) },
                    confirmButton = {
                        TextButton(onClick = {
                            viewModel.alterarEstadoTecladoData(false)

                            datePickerState.selectedDateMillis?.let {
                                val dataConvertida = Instant.ofEpochMilli(it).atZone(ZoneId.of("UTC")).toLocalDate()

                                viewModel.alterarData(dataConvertida)
                            }
                        }) {
                            Text(text = "Confirmar")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { viewModel.alterarEstadoTecladoData(false) }) {
                            Text(text = "Cancelar")
                        }
                    }) {
                    DatePicker(datePickerState)
                }
            }
            OutlinedTextField(
                value = uiState.nomeProfessora,
                onValueChange = { viewModel.alterarNomeProfessora(it) },
                placeholder = { Text(text = "Nome da professora:") },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    focusedBorderColor = ElectricBlue,
                    unfocusedBorderColor = ElectricBlue,
                    cursorColor = Color.Black,
                    unfocusedLeadingIconColor = Color.Black,
                    focusedLeadingIconColor = Color.Black,
                    errorContainerColor = Color.White,
                    errorTextColor = Color.Black,
                    errorCursorColor = Color.Black,
                    errorBorderColor = Color.Red,
                    errorLeadingIconColor = Color.Red,
                    errorSupportingTextColor = Color.Red
                ),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Person,
                        contentDescription = null,
                    )
                },
                shape = RoundedCornerShape(25.dp),
                isError = uiState.erroCampoProfessora,
                supportingText = {
                    if (uiState.erroCampoProfessora){
                        Text(text = "Campo professora está vazio!", fontWeight = FontWeight.ExtraBold)
                    }
                }
            )
            OutlinedTextField(
                value = uiState.turma,
                onValueChange = { viewModel.alterarTurma(it) },
                placeholder = { Text(text = "Nome da turma:") },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    focusedBorderColor = Color.Yellow,
                    unfocusedBorderColor = Color.Yellow,
                    cursorColor = Color.Black,
                    unfocusedLeadingIconColor = Color.Black,
                    focusedLeadingIconColor = Color.Black,
                    errorContainerColor = Color.White,
                    errorTextColor = Color.Black,
                    errorCursorColor = Color.Black,
                    errorBorderColor = Color.Red,
                    errorLeadingIconColor = Color.Red,
                    errorSupportingTextColor = Color.Red
                ),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Home,
                        contentDescription = null,
                    )
                },
                shape = RoundedCornerShape(25.dp),
                isError = uiState.erroCampoTurma,
                supportingText = {
                    if (uiState.erroCampoTurma){
                        Text(text = "Campo turma vazio!", fontWeight = FontWeight.ExtraBold)
                    }
                }
            )
            Button(
                onClick = {
                    viewModel.salvarAluno()
                }){
                Text("Cadastrar aluno")
                Icon(imageVector = Icons.Filled.Add, contentDescription = null)
            }
        }

    LaunchedEffect(Unit){
        viewModel.validarNavegacao.collect {idAluno ->
            validarNavegacao(idAluno)
        }
    }
}
