package com.mbmath.multiplication.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mbmath.multiplication.R
import com.mbmath.multiplication.game.GameState
import com.mbmath.multiplication.ui.components.AppButton
import com.mbmath.multiplication.ui.components.AppScaffold
import com.mbmath.multiplication.ui.components.AppSurface
import com.mbmath.multiplication.ui.components.AssetImage

@Composable
fun ResultsScreen(
    state: GameState,
    onHome: () -> Unit
) {
    AppScaffold(
        title = stringResource(R.string.results),
        content = { innerPadding ->

            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                val isWideLayout = maxWidth >= 700.dp
                AppSurface(
                    modifier = Modifier
                        .widthIn(max = 760.dp)
                        .fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Text(
                            stringResource(R.string.congratulations, state.configuration?.player.orEmpty()),
                            style = MaterialTheme.typography.headlineMedium
                        )
                        AssetImage("images/logo.webp", Modifier.size(130.dp))
                        Text(stringResource(R.string.completed_questions))

                        AppButton(
                            onClick = onHome,
                            icon = Icons.Default.Home,
                            textDescription = R.string.back_home,
                        )
                    }
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ResultsScreenPreview() {
    ResultsScreen(
        state = GameState(),
        onHome = {},
    )
}