package com.example.teavaliandotestes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import com.example.teavaliandotestes.navigation.Navegador
import com.example.teavaliandotestes.ui.theme.TEAvaliandoTestesTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TEAvaliandoTestesTheme {
                Navegador()
            }
        }
    }
}



