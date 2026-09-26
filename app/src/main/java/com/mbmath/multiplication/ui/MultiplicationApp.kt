package com.mbmath.multiplication.ui

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.content.res.Configuration
import androidx.cardview.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Surface
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.resolveDefaults
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mbmath.multiplication.game.GameState
import com.mbmath.multiplication.game.GameViewModel
import com.mbmath.multiplication.game.PantallaJuego
import com.mbmath.multiplication.model.Dificultad
import com.mbmath.multiplication.model.GameConfiguration
import com.mbmath.multiplication.model.ModoJuego
import com.mbmath.multiplication.model.Question
import com.mbmath.multiplication.ui.components.AssetImage
import com.mbmath.multiplication.ui.screens.HomeScreen
import com.mbmath.multiplication.ui.screens.PlayScreen

@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MultiplicationApp(viewModel: GameViewModel) {
    val estado by viewModel.estado.collectAsStateWithLifecycle()

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




        when (val pantalla = estado.pantalla) {
            PantallaJuego.Inicio -> HomeScreen(
                onJugar = viewModel::mostrarAyuda,
                onCreditos = viewModel::mostrarCreditos,
                paddingValues
            )
            is PantallaJuego.Ayuda -> AyudaScreen(
                configuracion = pantalla.configuracion,
                onComenzar = viewModel::comenzar,
                onInicio = viewModel::volverInicio
            )
            PantallaJuego.Pregunta -> PlayScreen(
                estado = estado,
                onResponder = viewModel::responder,
                onResultados = viewModel::mostrarResultados,
                onInicio = viewModel::volverInicio,
                paddingValues
            )
            PantallaJuego.Resultados -> ResultadosScreen(
                questions = estado.questions,
                onVolver = viewModel::volverAlJuego
            )
            PantallaJuego.Felicitacion -> FelicitacionScreen(
                estado = estado,
                onInicio = viewModel::volverInicio
            )
            PantallaJuego.Creditos -> CreditosScreen(onInicio = viewModel::volverInicio)
        }
    }
}




@Composable
private fun AyudaScreen(
    configuracion: GameConfiguration,
    onComenzar: () -> Unit,
    onInicio: () -> Unit
) {
    AppColumn {
        Text("Instrucciones", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        AssetImage("images/personaje.png", Modifier.size(160.dp))
        Text(
            "${configuracion.jugador.replaceFirstChar { it.uppercase() }}, seleccionaste " +
                "${configuracion.modo.titulo} con dificultad ${configuracion.dificultad.titulo}."
        )
        Spacer(Modifier.height(8.dp))
        Text("Observa las cartas y selecciona la respuesta que corresponde.")
        Spacer(Modifier.height(12.dp))
        Button(onClick = onComenzar, modifier = Modifier.fillMaxWidth()) { Text("Comenzar") }
        OutlinedButton(onClick = onInicio, modifier = Modifier.fillMaxWidth()) { Text("Inicio") }
    }
}


@Composable
private fun ResultadosScreen(questions: List<Question>, onVolver: () -> Unit) {
    AppColumn {
        Text("Resultados", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        LazyColumn(modifier = Modifier.weight(1f)) {
            itemsIndexed(questions) { indice, pregunta ->
                val correcta = pregunta.respuesta == pregunta.correcto + 1
                val estado = when {
                    pregunta.respuesta == 0 -> "Sin respuesta"
                    correcta -> "Correcta"
                    else -> "Incorrecta"
                }
                Text(
                    "Pregunta ${indice + 1}: ${pregunta.valor1[pregunta.correcto]} × " +
                        "${pregunta.valor2[pregunta.correcto]} = " +
                        "${if (correcta) pregunta.resultado[pregunta.correcto] else "??"} ($estado)",
                    modifier = Modifier.padding(vertical = 6.dp)
                )
            }
        }
        Button(onClick = onVolver, modifier = Modifier.fillMaxWidth()) { Text("Volver al juego") }
    }
}

@Composable
private fun FelicitacionScreen(estado: GameState, onInicio: () -> Unit) {
    AppColumn {
        Text("¡¡Felicitaciones ${estado.configuracion?.jugador.orEmpty()}!!", style = MaterialTheme.typography.headlineMedium)
        AssetImage("images/personaje.png", Modifier.size(150.dp))
        Text("Completaste las 24 preguntas.")
        Button(onClick = onInicio, modifier = Modifier.fillMaxWidth()) { Text("Volver al inicio") }
    }
}

@Composable
private fun CreditosScreen(onInicio: () -> Unit) {
    AppColumn {
        Text("Créditos", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        AssetImage("images/personaje.png", Modifier.size(150.dp))
        Text("Diseñado: Daniela Alejandra Olivares Diaz")
        Text("Implementado: Mario Bernardo Bustamante Aguilar")
        Text("Idea: Adriana Margot Mundaca Bugueño")
        Button(onClick = onInicio, modifier = Modifier.fillMaxWidth()) { Text("Inicio") }
    }
}

@Composable
fun AppColumn(content: @Composable ColumnScope.() -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        content = content
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MultiplicatioPreview() {

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
            onCreditos = {},
            paddingValues
        )
        /*
        JuegoScreen(
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
        */
    }
}