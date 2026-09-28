package com.mbmath.multiplication.ui.screens

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
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mbmath.multiplication.game.GameState
import com.mbmath.multiplication.model.Question
import com.mbmath.multiplication.ui.components.AppColumn
import com.mbmath.multiplication.ui.components.AppScaffold
import com.mbmath.multiplication.ui.components.AssetImage

@Composable
fun ResultsScreen(
    estado: GameState,
    onHome: () -> Unit
) {
    AppScaffold(
        title = "Resultados",
        content = { innerPadding ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "¡¡Felicitaciones ${estado.configuracion?.jugador.orEmpty()}!!",
                    style = MaterialTheme.typography.headlineMedium
                )
                Spacer(Modifier.height(30.dp))
                AssetImage("images/personaje.png", Modifier.size(150.dp))
                Spacer(Modifier.height(20.dp))
                Text("Completaste las 24 preguntas.")
                Spacer(Modifier.height(30.dp))
                Button(
                    onClick = onHome,
                    //modifier = Modifier.fillMaxWidth()
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Home, contentDescription = null)
                        Spacer(Modifier.width(8.dp))
                        Text("Volver al inicio")
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
        estado = GameState(),
        onHome = {},
    )
}