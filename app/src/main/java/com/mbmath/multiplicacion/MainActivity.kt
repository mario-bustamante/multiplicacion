package com.multiplicacion.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.multiplicacion.app.game.JuegoViewModel
import com.multiplicacion.app.ui.MultiplicacionApp

class MainActivity : ComponentActivity() {
    private val juegoViewModel: JuegoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MultiplicacionApp(juegoViewModel)
        }
    }
}