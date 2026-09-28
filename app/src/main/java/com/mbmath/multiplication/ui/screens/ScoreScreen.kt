package com.mbmath.multiplication.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mbmath.multiplication.model.Question
import com.mbmath.multiplication.ui.components.AppColumn
import com.mbmath.multiplication.ui.components.AppScaffold

@Composable
fun ScoreScreen(
    questions: List<Question>,
    onVolver: () -> Unit
) {
    AppScaffold(
        title = "Resultados",
        verticalScrollEnabled = false,
        content = { innerPadding ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {


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
                Spacer(Modifier.height(30.dp))
                Button(
                    onClick = onVolver,
                    //modifier = Modifier.fillMaxWidth()
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                        Spacer(Modifier.width(8.dp))
                        Text("Volver a jugar")
                    }
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ScoreScreenPreview() {
    val questions = listOf(
        Question(
            valor1 = intArrayOf(2, 4, 5),
            valor2 = intArrayOf(3, 2, 2),
            resultado = intArrayOf(6, 8, 10),
            correcto = 0,
            respuesta = 1
        ),
        Question(
            valor1 = intArrayOf(9, 4, 5),
            valor2 = intArrayOf(3, 2, 2),
            resultado = intArrayOf(27, 8, 10),
            correcto = 0,
            respuesta = 1
        )
    )

    ScoreScreen(
        questions = questions,
        onVolver = {},
    )
}