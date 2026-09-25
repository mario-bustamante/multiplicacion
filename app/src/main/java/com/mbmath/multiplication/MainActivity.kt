package com.mbmath.multiplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels

import com.mbmath.multiplication.game.GameViewModel
import com.mbmath.multiplication.ui.MultiplicationApp

class MainActivity : ComponentActivity() {
    private val gameViewModel: GameViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MultiplicationApp(gameViewModel)
        }
    }
}