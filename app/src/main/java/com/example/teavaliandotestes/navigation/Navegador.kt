package com.example.teavaliandotestes.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.teavaliandotestes.navigation.rotas.TelaCadastroRoute
import com.example.teavaliandotestes.navigation.rotas.TelaProvaRoute
import com.example.teavaliandotestes.navigation.rotas.TelaRelatorioRoute
import com.example.teavaliandotestes.presentation.ui.screens.TelaCadastro
import com.example.teavaliandotestes.presentation.ui.screens.TelaProva
import com.example.teavaliandotestes.presentation.ui.screens.TelaRelatorio


@Composable
fun Navegador(){

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = TelaCadastroRoute){
        composable<TelaCadastroRoute> {
            TelaCadastro(validarNavegacao = { idAluno ->
                navController.navigate(TelaProvaRoute(idPai = idAluno))
            })
        }
        composable<TelaProvaRoute> {
            TelaProva(navegar = { idAluno ->
                navController.navigate(TelaRelatorioRoute(idPai = idAluno))
            })
        }
        composable<TelaRelatorioRoute>{
            TelaRelatorio(navegar = {
                navController.navigate(TelaCadastroRoute){
                    popUpTo(id = 0)
                }
            })
        }
    }
}