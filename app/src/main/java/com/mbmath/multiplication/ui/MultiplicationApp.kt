package com.mbmath.multiplication.ui

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mbmath.multiplication.game.GameViewModel
import com.mbmath.multiplication.game.Screens
import com.mbmath.multiplication.ui.screens.CreditsScreen
import com.mbmath.multiplication.ui.screens.HomeScreen
import com.mbmath.multiplication.ui.screens.InstructionsScreen
import com.mbmath.multiplication.ui.screens.PlayScreen
import com.mbmath.multiplication.ui.screens.ResultsScreen
import com.mbmath.multiplication.ui.screens.ScoreScreen

@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MultiplicationApp(viewModel: GameViewModel) {
    val estado by viewModel.estado.collectAsStateWithLifecycle()

    when (val pantalla = estado.pantalla) {
        Screens.Home -> HomeScreen(
            onJugar = viewModel::mostrarAyuda,
            onCredits  = viewModel::onCredits
        )
        is Screens.Instructions -> InstructionsScreen(
            configuration = pantalla.configuration,
            onComenzar = viewModel::comenzar,
            onHome = viewModel::onHome,
            onCredits = viewModel::onCredits,
        )
        Screens.Play -> PlayScreen(
            estado = estado,
            onResponder = viewModel::responder,
            onResultados = viewModel::mostrarResultados,
            onHome = viewModel::onHome,
            onCredits = viewModel::onCredits,
        )
        Screens.Score -> ScoreScreen(
            questions = estado.questions,
            onVolver = viewModel::volverAlJuego
        )
        Screens.Results -> ResultsScreen(
            estado = estado,
            onHome = viewModel::onHome
        )
        Screens.Credits -> CreditsScreen(onHome = viewModel::onHome)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MultiplicationAppPreview() {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "titulo",
                        fontSize = 19.sp
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White.copy(alpha = 0.7f)
                ),
                navigationIcon = {
                    IconButton(onClick = {  }) {

                    }
                }
            )
        }

    ) { paddingValues ->
        HomeScreen(
            onJugar = {},
            onCredits  = {},
        )
    }
}