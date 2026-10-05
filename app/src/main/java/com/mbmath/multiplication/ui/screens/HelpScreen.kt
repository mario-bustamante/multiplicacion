package com.mbmath.multiplication.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mbmath.multiplication.R
import com.mbmath.multiplication.model.Difficulty
import com.mbmath.multiplication.model.GameConfiguration
import com.mbmath.multiplication.model.GameMode
import com.mbmath.multiplication.ui.components.AppButton
import com.mbmath.multiplication.ui.components.AppScaffold
import com.mbmath.multiplication.ui.components.AppSurface
import com.mbmath.multiplication.ui.components.AssetImage
import com.mbmath.multiplication.ui.components.localizedTitle

@Composable
fun HelpScreen(
    configuration: GameConfiguration,
    onPlay: () -> Unit,
    onHome: () -> Unit,
    backToPlay: () -> Unit
) {
    AppScaffold(
        title = stringResource(R.string.instructions_title),
        onHome = if(configuration.wasLoggedIn) null else onHome,
        onBackPlay = if(configuration.wasLoggedIn) backToPlay else null,
        onPlay = if(configuration.wasLoggedIn || !configuration.player.isNotBlank()) null else onPlay,
        content = { innerPadding ->
            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                val isWideLayout = maxWidth >= 700.dp
                var showsResultInstructions = configuration.gameMode == GameMode.FIND_RESULT ||
                    configuration.gameMode == GameMode.MIXED
                var showsMultiplicationInstructions = configuration.gameMode == GameMode.FIND_MULTIPLICATION ||
                    configuration.gameMode == GameMode.MIXED

                Column(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .widthIn(max = 1000.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(15.dp)
                ) {


                    if (configuration.player.isNotBlank()) {
                        AppSurface(modifier = Modifier.fillMaxWidth()) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(if (isWideLayout) 22.dp else 16.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                AssetImage(
                                    path = "images/logo.webp",
                                    modifier = Modifier.size(if (isWideLayout) 84.dp else 64.dp),
                                    maintainAspectRatio = true
                                )
                                Text(
                                    text = stringResource(
                                        R.string.instructions_selected,
                                        configuration.player.replaceFirstChar { it.uppercase() },
                                        configuration.gameMode.localizedTitle(),
                                        configuration.difficulty.localizedTitle()
                                    ),
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }

                    } else {
                        showsResultInstructions = true
                        showsMultiplicationInstructions = true
                    }

                    if (showsResultInstructions && showsMultiplicationInstructions && isWideLayout) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            InstructionPanel(
                                title = GameMode.FIND_RESULT.localizedTitle(),
                                body = stringResource(R.string.instructions_find_result),
                                imagePath = "images/instructions_a.png",
                                modifier = Modifier.weight(1f)
                            )
                            InstructionPanel(
                                title = GameMode.FIND_MULTIPLICATION.localizedTitle(),
                                body = stringResource(R.string.instructions_find_multiplication),
                                imagePath = "images/instructions_b.png",
                                modifier = Modifier.weight(1f)
                            )
                        }
                    } else {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(14.dp)
                        ) {
                            if (showsResultInstructions) {
                                InstructionPanel(
                                    title = GameMode.FIND_RESULT.localizedTitle(),
                                    body = stringResource(R.string.instructions_find_result),
                                    imagePath = "images/instructions_a.png",
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                            if (showsMultiplicationInstructions) {
                                InstructionPanel(
                                    title = GameMode.FIND_MULTIPLICATION.localizedTitle(),
                                    body = stringResource(R.string.instructions_find_multiplication),
                                    imagePath = "images/instructions_b.png",
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                        }
                    }

                    if(configuration.player.isNotBlank()) {
                        if (configuration.wasLoggedIn) {
                            AppButton(
                                onClick = backToPlay,
                                icon = Icons.AutoMirrored.Filled.ArrowBack,
                                textDescription = R.string.play_again,
                            )
                        } else {
                            AppButton(
                                onClick = onPlay,
                                icon = Icons.Default.PlayArrow,
                                textDescription = R.string.play,
                            )
                        }
                    }
                }
            }
        }
    )
}

@Composable
private fun InstructionPanel(
    title: String,
    body: String,
    imagePath: String,
    modifier: Modifier = Modifier
) {
    AppSurface(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = body,
                style = MaterialTheme.typography.bodyLarge
            )
            AssetImage(
                path = imagePath,
                modifier = Modifier.fillMaxWidth(),
                maintainAspectRatio = true
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HelpScreenPreview() {
    HelpScreen(
        configuration = GameConfiguration(
                player = "Ana",
                gameMode = GameMode.MIXED,
                difficulty = Difficulty.EASY,
                wasLoggedIn = false
            ),
        onPlay = {},
        onHome = {},
        backToPlay = {}
    )
}