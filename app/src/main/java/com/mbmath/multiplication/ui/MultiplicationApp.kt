package com.mbmath.multiplication.ui

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mbmath.multiplication.game.GameState
import com.mbmath.multiplication.game.GameViewModel
import com.mbmath.multiplication.game.Screens
import com.mbmath.multiplication.model.Difficulty
import com.mbmath.multiplication.model.GameConfiguration
import com.mbmath.multiplication.model.GameMode
import com.mbmath.multiplication.ui.screens.CreditsScreen
import com.mbmath.multiplication.ui.screens.HelpScreen
import com.mbmath.multiplication.ui.screens.HomeScreen
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
            state = state,
            showHelp = viewModel::showHelp,
            showCredits  = viewModel::showCredits,
            onPlay = viewModel::onPlay,
            onConfigurationChange = viewModel::updateConfiguration
        )
        is Screens.Help -> HelpScreen(
            configuration = screen.configuration,
            onPlay = viewModel::onPlay,
            onHome = viewModel::onHome,
            backToPlay = viewModel::backToPlay
        )
        Screens.Play -> PlayScreen(
            state = state,
            showHelp = viewModel::showHelp,
            submitAnswer = viewModel::submitAnswer,
            showResults = viewModel::showResults,
            onHome = viewModel::onHome,
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
        state = GameState(
            screen = Screens.Play,
            configuration = GameConfiguration(
                player = "Ana",
                gameMode = GameMode.MIXED,
                difficulty = Difficulty.INTERMEDIATE
            )
        ),
        showHelp = {},
        showCredits  = {},
        onPlay = {}
    )
}