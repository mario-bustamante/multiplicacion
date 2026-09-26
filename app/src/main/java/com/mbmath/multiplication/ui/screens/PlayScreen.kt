package com.mbmath.multiplication.ui.screens

import android.content.res.Configuration
import android.graphics.BitmapFactory
import android.graphics.BitmapFactory.decodeStream
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mbmath.multiplication.game.GameState
import com.mbmath.multiplication.game.PantallaJuego
import com.mbmath.multiplication.model.Dificultad
import com.mbmath.multiplication.model.GameConfiguration
import com.mbmath.multiplication.model.ModoJuego
import com.mbmath.multiplication.model.Question
import com.mbmath.multiplication.ui.AppColumn
import com.mbmath.multiplication.ui.components.AssetImage

@Composable
fun PlayScreen(
    estado: GameState,
    onResponder: (Int) -> Unit,
    onResultados: () -> Unit,
    onInicio: () -> Unit,
    paddingValues: PaddingValues
) {
    val pregunta = estado.questionActual ?: return
    val configuracion = estado.configuracion ?: return
    val isLandscape = LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE
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
                        onResultados()
                    },
                    icon = { Icon(Icons.Default.CheckCircle, contentDescription = null) },
                    label = { Text("Resultados") }
                )
                NavigationBarItem(
                    selected = selectedDestination == 1,
                    onClick = {
                        selectedDestination = 1
                        onInicio()
                    },
                    icon = { Icon(Icons.Default.Home, contentDescription = null) },
                    label = { Text("Inicio") }
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
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
            Text("Pregunta ${estado.indice + 1} de 24", fontWeight = FontWeight.Bold)
            Spacer(Modifier.weight(1f))
            Text("Etapa ${estado.etapa}")
        }
        LinearProgressIndicator(progress = { estado.progreso }, modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(12.dp))
        Text(configuracion.modo.titulo, style = MaterialTheme.typography.titleLarge)
        Text(
            estado.mensaje,
            color = if (estado.mensaje.startsWith("Incorrecto")) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary
        )

        Spacer(Modifier.height(12.dp))

        if (configuracion.modo != ModoJuego.BUSCAR_RESULTADO) {
            Text(
                "Encuentra la multiplicación de: ${pregunta.resultado[pregunta.correcto]}",
                fontWeight = FontWeight.Bold
            )
        } else {
            Text(
                "Encuentra el resultado de: ${pregunta.valor1[pregunta.correcto]} × ${pregunta.valor2[pregunta.correcto]}",
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(Modifier.height(12.dp))

        BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
            val imageMaxHeight = maxHeight * 0.65f
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .then(if (isLandscape) Modifier.height(imageMaxHeight) else Modifier)
            ) {
                TextButton(
                    onClick = { },
                    enabled = false,
                    shape = RectangleShape,
                    contentPadding = PaddingValues(0.dp),
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .then(if (isLandscape) Modifier.fillMaxHeight() else Modifier)
                ) {
                    AssetImage(
                        path = "images/${pregunta.valor2[pregunta.correcto]}x${pregunta.valor1[pregunta.correcto]}" +
                                if (configuracion.modo == ModoJuego.BUSCAR_RESULTADO) "a.gif" else "b.gif",
                        modifier = if (isLandscape) {
                            Modifier.fillMaxHeight()
                        } else {
                            Modifier.fillMaxWidth().heightIn(max = imageMaxHeight)
                        },
                        maintainAspectRatio = true,
                        matchHeightConstraintsFirst = isLandscape
                    )
                }

                pregunta.valor1.indices.forEach { opcion ->
                    val texto = if (configuracion.modo == ModoJuego.BUSCAR_MULTIPLICACION) {
                        "${pregunta.valor1[opcion]} × ${pregunta.valor2[opcion]}"
                    } else {
                        pregunta.resultado[opcion].toString()
                    }
                    TextButton(
                        onClick = { onResponder(opcion) },
                        enabled = opcion !in estado.opcionesDeshabilitadas && estado.opcionSeleccionada == null,
                        shape = RectangleShape,
                        contentPadding = PaddingValues(0.dp),
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .then(if (isLandscape) Modifier.fillMaxHeight() else Modifier)
                    ) {
                        AssetImage(
                            path = "images/${pregunta.valor2[opcion]}x${pregunta.valor1[opcion]}" +
                                    if (configuracion.modo == ModoJuego.BUSCAR_RESULTADO) "b.gif" else "a.gif",
                            modifier = if (isLandscape) {
                                Modifier.fillMaxHeight()
                            } else {
                                Modifier.fillMaxWidth().heightIn(max = imageMaxHeight)
                            },
                            maintainAspectRatio = true,
                            matchHeightConstraintsFirst = isLandscape
                        )
                    }
                }
            }
        }
    }

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PlayScreenPreview() {
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
        PlayScreen(
            estado = GameState(
                pantalla = PantallaJuego.Pregunta,
                configuracion = GameConfiguration(
                    jugador = "Ana",
                    modo = ModoJuego.BUSCAR_RESULTADO,
                    dificultad = Dificultad.FACIL
                ),
                questions = listOf(
                    Question(
                        valor1 = intArrayOf(2, 4, 5),
                        valor2 = intArrayOf(3, 2, 2),
                        resultado = intArrayOf(6, 8, 10),
                        correcto = 0
                    )
                ),
            ),
            onResponder = {},
            onResultados = {},
            onInicio = {},
            paddingValues
        )
    }
}