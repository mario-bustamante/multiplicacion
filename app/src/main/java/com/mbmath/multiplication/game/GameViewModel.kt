package com.mbmath.multiplication.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mbmath.multiplication.model.GameConfiguration
import com.mbmath.multiplication.model.ModoJuego
import com.mbmath.multiplication.model.Question
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

class GameViewModel : ViewModel() {
    private val _estado = MutableStateFlow(GameState())
    val estado: StateFlow<GameState> = _estado.asStateFlow()

    private var modoActual = ModoJuego.BUSCAR_RESULTADO

    fun mostrarCreditos() {
        _estado.value = _estado.value.copy(pantalla = PantallaJuego.Creditos)
    }

    fun volverInicio() {
        _estado.value = GameState()
    }

    fun mostrarAyuda(configuracion: GameConfiguration) {
        modoActual = configuracion.modo
        _estado.value = _estado.value.copy(
            pantalla = PantallaJuego.Ayuda(configuracion),
            configuracion = configuracion
        )
    }

    fun comenzar() {
        val configuracion = _estado.value.configuracion ?: return
        modoActual = configuracion.modo
        val pregunta = crearPregunta(1)
        _estado.value = _estado.value.copy(
            pantalla = PantallaJuego.Pregunta,
            questions = listOf(pregunta),
            indice = 0,
            etapa = 1,
            contadorEtapa = 1,
            mensaje = "",
            opcionSeleccionada = null,
            opcionesDeshabilitadas = emptySet()
        )
    }

    fun responder(opcion: Int) {
        val actual = _estado.value
        val pregunta = actual.questionActual ?: return
        if (opcion in actual.opcionesDeshabilitadas || actual.opcionSeleccionada != null) return

        val correcta = opcion == pregunta.correcto
        val preguntas = actual.questions.toMutableList()
        preguntas[actual.indice] = pregunta.responder(opcion)

        if (!correcta) {
            _estado.value = actual.copy(
                questions = preguntas,
                mensaje = "Incorrecto",
                opcionesDeshabilitadas = actual.opcionesDeshabilitadas + opcion
            )
            return
        }

        val resultado = pregunta.resultado[pregunta.correcto]
        _estado.value = actual.copy(
            questions = preguntas,
            mensaje = "Correcto   ${pregunta.valor1[pregunta.correcto]} x ${pregunta.valor2[pregunta.correcto]} = $resultado",
            opcionSeleccionada = opcion
        )

        viewModelScope.launch {
            delay(2000)
            avanzar()
        }
    }

    fun mostrarResultados() {
        _estado.value = _estado.value.copy(pantalla = PantallaJuego.Resultados)
    }

    fun volverAlJuego() {
        _estado.value = _estado.value.copy(pantalla = PantallaJuego.Pregunta)
    }

    private fun avanzar() {
        val actual = _estado.value
        if (actual.indice >= 23) {
            _estado.value = actual.copy(
                pantalla = PantallaJuego.Felicitacion,
                indice = 24
            )
            return
        }

        val siguienteContador = if (actual.contadorEtapa == 3) 1 else actual.contadorEtapa + 1
        val siguienteEtapa = if (actual.contadorEtapa == 3) actual.etapa + 1 else actual.etapa
        val siguiente = crearPregunta(siguienteEtapa)
        _estado.value = actual.copy(
            questions = actual.questions + siguiente,
            indice = actual.indice + 1,
            etapa = siguienteEtapa,
            contadorEtapa = siguienteContador,
            mensaje = "",
            opcionSeleccionada = null,
            opcionesDeshabilitadas = emptySet()
        )
    }

    private fun crearPregunta(etapa: Int): Question {
        val valores = mutableListOf<Pair<Int, Int>>()
        while (valores.size < 3) {
            val valor = valoresDeEtapa(etapa)
            if (valor !in valores) valores += valor
        }
        val resultados = valores.map { (valor1, valor2) -> valor1 * valor2 }.toIntArray()
        return Question(
            valor1 = valores.map { it.first }.toIntArray(),
            valor2 = valores.map { it.second }.toIntArray(),
            resultado = resultados,
            correcto = Random.nextInt(0, 3)
        )
    }

    private fun valoresDeEtapa(etapa: Int): Pair<Int, Int> {
        val valor1 = when (etapa) {
            1 -> 1
            2 -> Random.nextInt(2, 4)
            3 -> Random.nextInt(4, 6)
            4 -> 6
            5 -> 7
            6 -> 8
            7 -> 9
            else -> Random.nextInt(1, 10)
        }
        return valor1 to Random.nextInt(1, 10)
    }
}