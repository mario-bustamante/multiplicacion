package com.mbmath.multiplication.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.PlayArrow
import com.mbmath.multiplication.model.Dificultad
import com.mbmath.multiplication.model.GameConfiguration
import com.mbmath.multiplication.model.ModoJuego
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen(
    onJugar: (GameConfiguration) -> Unit,
    onCreditos: () -> Unit,
    paddingValues: PaddingValues
) {
    var jugador by remember { mutableStateOf("") }
    var modo by remember { mutableStateOf(ModoJuego.BUSCAR_RESULTADO) }
    var dificultad by remember { mutableStateOf(Dificultad.FACIL) }
    var selectedDestination by remember { mutableStateOf(0) }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(0.dp),
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = selectedDestination == 0,
                    onClick = {
                        selectedDestination = 0
                        onJugar(GameConfiguration(jugador.trim(), modo, dificultad))
                    },
                    enabled = jugador.isNotBlank(),
                    icon = { Icon(Icons.Default.PlayArrow, contentDescription = null) },
                    label = { Text("Jugar") }
                )
                NavigationBarItem(
                    selected = selectedDestination == 1,
                    onClick = {
                        selectedDestination = 1
                        onCreditos()
                    },
                    icon = { Icon(Icons.Default.Info, contentDescription = null) },
                    label = { Text("Créditos") }
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(20.dp)
        ) {
            OutlinedTextField(
                value = jugador,
                onValueChange = { jugador = it },
                label = { Text("Ingresa tu nombre") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(12.dp))

            Selector(
                "Tipo de juego",
                ModoJuego.entries,
                modo,
                { modo = it }) { it.titulo }

            Spacer(Modifier.height(12.dp))

            Selector(
                "Dificultad",
                Dificultad.entries,
                dificultad,
                { dificultad = it }) { it.titulo }

        }
    }
}

@Composable
@OptIn(ExperimentalLayoutApi::class)
private fun <T> Selector(
    etiqueta: String,
    opciones: List<T>,
    seleccion: T,
    onSeleccion: (T) -> Unit,
    texto: (T) -> String
) {
    Column(modifier = Modifier.padding(top = 12.dp)) {
        Text(
            etiqueta,
            modifier = Modifier.padding(bottom = 5.dp),
            fontWeight = FontWeight.Bold,
        )
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(6.dp, Alignment.CenterHorizontally),
            verticalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            opciones.forEach { opcion ->
                OutlinedButton(
                    onClick = { onSeleccion(opcion) },
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = Color(0xFF2E7D32),
                        contentColor = Color.White
                    ),
                    contentPadding = PaddingValues(10.dp)
                ) {
                    Text(if (opcion == seleccion) "✓ ${texto(opcion)}" else texto(opcion))
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Multiplicación",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        fontSize = 19.sp
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF1565C0),
                    scrolledContainerColor = Color.Unspecified,
                    navigationIconContentColor = Color.Unspecified,
                    titleContentColor = Color.White,
                    actionIconContentColor = Color.Unspecified
                ),
            )
        }

    ) { paddingValues ->

        HomeScreen(
            onJugar = {},
            onCreditos = {},
            paddingValues
        )
    }
}