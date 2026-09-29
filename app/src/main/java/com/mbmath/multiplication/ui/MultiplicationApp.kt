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
    val state by viewModel.state.collectAsStateWithLifecycle()

    when (val screen = state.screen) {
        Screens.Home -> HomeScreen(
            showInstructions = viewModel::showInstructions,
            onCredits  = viewModel::onCredits
        )
        is Screens.Instructions -> InstructionsScreen(
            configuration = screen.configuration,
            onPlay = viewModel::onPlay,
            onHome = viewModel::onHome,
            onCredits = viewModel::onCredits,
        )
        Screens.Play -> PlayScreen(
            state = state,
            submitAnswer = viewModel::submitAnswer,
            showResults = viewModel::showResults,
            onHome = viewModel::onHome
        )
        Screens.Score -> ScoreScreen(
            questions = state.questions,
            backToPlay = viewModel::backToPlay
        )
        Screens.Results -> ResultsScreen(
            state = state,
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
    HomeScreen(
        showInstructions = {},
        onCredits  = {},
    )
}