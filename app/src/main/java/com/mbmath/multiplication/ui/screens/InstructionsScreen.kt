package com.mbmath.multiplication.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mbmath.multiplication.game.GameState
import com.mbmath.multiplication.game.Screens
import com.mbmath.multiplication.model.Difficulty
import com.mbmath.multiplication.model.GameConfiguration
import com.mbmath.multiplication.model.GameMode
import com.mbmath.multiplication.model.Question
import com.mbmath.multiplication.ui.components.AppScaffold
import com.mbmath.multiplication.ui.components.AssetImage
import com.mbmath.multiplication.ui.components.localizedTitle
import com.mbmath.multiplication.R

@Composable
fun InstructionsScreen(
    configuration: GameConfiguration,
    onPlay: () -> Unit,
    onHome: () -> Unit,
    onCredits: () -> Unit
) {
    AppScaffold(
        onHome = onHome,
        onCredits = onCredits,
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
                //verticalArrangement = Arrangement.Center
            ) {
                Text(
                    stringResource(R.string.instructions_title),
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                Spacer(Modifier.height(30.dp))
                AssetImage("images/personaje.png", Modifier.size(160.dp))
                Spacer(Modifier.height(30.dp))
                Text(
                    stringResource(
                        R.string.instructions_selected,
                        configuration.player.replaceFirstChar { it.uppercase() },
                        configuration.gameMode.localizedTitle(),
                        configuration.difficulty.localizedTitle()
                    ),
                    textAlign = TextAlign.Center
                )
                Spacer(Modifier.height(20.dp))
                Text(
                    stringResource(R.string.instructions_body),
                    textAlign = TextAlign.Center
                )
                Spacer(Modifier.height(30.dp))

                Button(
                    onClick = onPlay,
                    //modifier = Modifier.fillMaxWidth()
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.PlayArrow, contentDescription = null)
                        Spacer(Modifier.width(8.dp))
                        Text(stringResource(R.string.play))
                    }
                }
            }
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun InstructionsScreenPreview() {
    InstructionsScreen(
        configuration = GameConfiguration(
                player = "Ana",
                gameMode = GameMode.FIND_RESULT,
                difficulty = Difficulty.EASY
            ),
        onPlay = {},
        onHome = {},
        onCredits = {}
    )
}