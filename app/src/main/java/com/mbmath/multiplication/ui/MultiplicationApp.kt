package com.mbmath.multiplication.ui

import android.graphics.BitmapFactory
import androidx.cardview.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.resolveDefaults
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mbmath.multiplication.game.GameState
import com.mbmath.multiplication.game.GameViewModel
import com.mbmath.multiplication.game.PantallaJuego
import com.mbmath.multiplication.model.Dificultad
import com.mbmath.multiplication.model.GameConfiguration
import com.mbmath.multiplication.model.ModoJuego
import com.mbmath.multiplication.model.Question


@Composable
fun MultiplicationApp(viewModel: GameViewModel) {
    val estado by viewModel.estado.collectAsStateWithLifecycle()
    Surface(modifier = Modifier.fillMaxSize(), color = colorResource(R.color.cardview_light_background)) {
        when (val pantalla = estado.pantalla) {
            PantallaJuego.Inicio -> InicioScreen(
                onJugar = viewModel::mostrarAyuda,
                onCreditos = viewModel::mostrarCreditos
            )
            is PantallaJuego.Ayuda -> AyudaScreen(
                configuracion = pantalla.configuracion,
                onComenzar = viewModel::comenzar,
                onInicio = viewModel::volverInicio
            )
            PantallaJuego.Pregunta -> JuegoScreen(
                estado = estado,
                onResponder = viewModel::responder,
                onResultados = viewModel::mostrarResultados,
                onInicio = viewModel::volverInicio,
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
private fun InicioScreen(
    onJugar: (GameConfiguration) -> Unit,
    onCreditos: () -> Unit
) {
    var jugador by remember { mutableStateOf("") }
    var modo by remember { mutableStateOf(ModoJuego.BUSCAR_RESULTADO) }
    var dificultad by remember { mutableStateOf(Dificultad.FACIL) }

    AppColumn {
        Text("Multiplicación", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(20.dp))
        OutlinedTextField(
            value = jugador,
            onValueChange = { jugador = it },
            label = { Text("Nombre del jugador") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Selector("Tipo de juego", ModoJuego.entries, modo, { modo = it }) { it.titulo }
        Selector("Dificultad", Dificultad.entries, dificultad, { dificultad = it }) { it.titulo }
        Spacer(Modifier.height(12.dp))
        Button(
            onClick = { onJugar(GameConfiguration(jugador.trim(), modo, dificultad)) },
            enabled = jugador.isNotBlank(),
            modifier = Modifier.fillMaxWidth()
        ) { Text("Jugar") }
        OutlinedButton(onClick = onCreditos, modifier = Modifier.fillMaxWidth()) { Text("Créditos") }
    }
}

@Composable
private fun <T> Selector(
    etiqueta: String,
    opciones: List<T>,
    seleccion: T,
    onSeleccion: (T) -> Unit,
    texto: (T) -> String
) {
    Column(modifier = Modifier.padding(top = 12.dp)) {
        Text(etiqueta, fontWeight = FontWeight.Bold)
        Row(horizontalArrangement = Arrangement.spacedBy(6.dp), modifier = Modifier.fillMaxWidth()) {
            opciones.forEach { opcion ->
                OutlinedButton(onClick = { onSeleccion(opcion) }) {
                    Text(if (opcion == seleccion) "✓ ${texto(opcion)}" else texto(opcion))
                }
            }
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
private fun JuegoScreen(
    estado: GameState,
    onResponder: (Int) -> Unit,
    onResultados: () -> Unit,
    onInicio: () -> Unit,
    paddingValues: PaddingValues? = null
) {
    val pregunta = estado.questionActual ?: return
    val configuracion = estado.configuracion ?: return
    AppColumn {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
            Text("Pregunta ${estado.indice + 1} de 24", fontWeight = FontWeight.Bold)
            Spacer(Modifier.weight(1f))
            Text("Etapa ${estado.etapa}")
        }
        LinearProgressIndicator(progress = { estado.progreso }, modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(12.dp))
        Text(configuracion.modo.titulo, style = MaterialTheme.typography.titleLarge)
        Text(estado.mensaje, color = if (estado.mensaje.startsWith("Incorrecto")) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary)
        Spacer(Modifier.height(12.dp))
        if (configuracion.modo != ModoJuego.BUSCAR_RESULTADO) {
            Text("Encuentra la multiplicación de:", fontWeight = FontWeight.Bold)
            Text("${pregunta.resultado[pregunta.correcto]}", style = MaterialTheme.typography.displaySmall)
        } else {
            Text("Encuentra el resultado de:", fontWeight = FontWeight.Bold)
            Text("${pregunta.valor1[pregunta.correcto]} × ${pregunta.valor2[pregunta.correcto]}", style = MaterialTheme.typography.displaySmall)
        }
        Spacer(Modifier.height(12.dp))


        AssetImage(
            path = "images/${pregunta.valor2[pregunta.correcto]}x${pregunta.valor1[pregunta.correcto]}" +
                if (configuracion.modo == ModoJuego.BUSCAR_RESULTADO) "a.gif" else "b.gif",
            modifier = Modifier.width(80.dp),//.size(width = 56.dp, height = 76.dp),
            maintainAspectRatio = true
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
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
                    modifier = Modifier.weight(1f).fillMaxWidth()
                ) {
                    AssetImage(
                        path = "images/${pregunta.valor2[opcion]}x${pregunta.valor1[opcion]}" +
                                if (configuracion.modo == ModoJuego.BUSCAR_RESULTADO) "b.gif" else "a.gif",
                            modifier = Modifier.fillMaxWidth(),
                            maintainAspectRatio = true
                    )
                }
            }
        }
        
        Spacer(Modifier.height(8.dp))
        OutlinedButton(onClick = onResultados, modifier = Modifier.fillMaxWidth()) { Text("Resultados") }
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
private fun AppColumn(content: @Composable ColumnScope.() -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        content = content
    )
}

@Composable
private fun AssetImage(
    path: String,
    modifier: Modifier = Modifier,
    maintainAspectRatio: Boolean = false
) {
    val context = LocalContext.current
    val bitmap = remember(path) {
        runCatching { context.assets.open(path).use(BitmapFactory::decodeStream) }.getOrNull()
    }
    if (bitmap != null) {
        val imageModifier = if (maintainAspectRatio) {
            modifier.aspectRatio(bitmap.width.toFloat() / bitmap.height)
        } else {
            modifier
        }
        Image(bitmap = bitmap.asImageBitmap(), contentDescription = null, modifier = imageModifier)
    } else {
        Card(modifier = modifier) {}
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    Surface(modifier = Modifier.fillMaxSize(), color = colorResource(R.color.cardview_light_background)) {
    //Scaffold() { paddingValues ->
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
                )
            ),
            onResponder = {},
            onResultados = {},
            onInicio = {},
         //   paddingValues
        )
    }
}