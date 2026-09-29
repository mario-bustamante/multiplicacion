package com.mbmath.multiplication.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.PlayArrow
import com.mbmath.multiplication.model.Difficulty
import com.mbmath.multiplication.model.GameConfiguration
import com.mbmath.multiplication.model.GameMode
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.mbmath.multiplication.ui.components.AppScaffold
import com.mbmath.multiplication.ui.components.AppSelector
import com.mbmath.multiplication.ui.components.localizedTitle
import com.mbmath.multiplication.R

@Composable
fun HomeScreen(
    showInstructions: (GameConfiguration) -> Unit,
    onCredits : () -> Unit,
) {
    var player by remember { mutableStateOf("") }
    var gameMode by remember { mutableStateOf(GameMode.FIND_RESULT) }
    var difficulty by remember { mutableStateOf(Difficulty.EASY) }

    AppScaffold(
        onCredits = onCredits,
        showLanguageSelector = true,
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = player,
                    onValueChange = { player = it },
                    label = { Text(stringResource(R.string.enter_name)) },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                      //  .height(52.dp)
                )

                Spacer(Modifier.height(10.dp))

                AppSelector(
                    stringResource(R.string.game_mode),
                    GameMode.entries,
                    gameMode,
                    { gameMode = it }) { it.localizedTitle() }

                Spacer(Modifier.height(10.dp))

                AppSelector(
                    stringResource(R.string.difficulty),
                    Difficulty.entries,
                    difficulty,
                    { difficulty = it }) { it.localizedTitle() }

                Spacer(Modifier.height(15.dp))

                Button(
                    onClick = {
                        showInstructions(GameConfiguration(player.trim(), gameMode, difficulty))
                    },
                    enabled = player.isNotBlank(),
                    //modifier = Modifier.fillMaxWidth()
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.PlayArrow, contentDescription = null)
                        Spacer(Modifier.width(8.dp))
                        Text(stringResource(R.string.start))
                    }
                }

            }
        }
    )
}




@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        showInstructions = {},
        onCredits  = {}
    )
}