package com.mbmath.multiplication.game

import com.mbmath.multiplication.model.GameConfiguration
import com.mbmath.multiplication.model.Question

sealed interface PantallaJuego {
    data object Inicio : PantallaJuego
    data class Ayuda(val configuracion: GameConfiguration) : PantallaJuego
    data object Pregunta : PantallaJuego
    data object Resultados : PantallaJuego
    data object Felicitacion : PantallaJuego
    data object Creditos : PantallaJuego
}

data class GameState(
    val pantalla: PantallaJuego = PantallaJuego.Inicio,
    val configuracion: GameConfiguration? = null,
    val questions: List<Question> = emptyList(),
    val indice: Int = 0,
    val etapa: Int = 1,
    val contadorEtapa: Int = 1,
    val mensaje: String = "",
    val opcionSeleccionada: Int? = null,
    val opcionesDeshabilitadas: Set<Int> = emptySet()
) {
    val questionActual: Question?
        get() = questions.getOrNull(indice)

    val progreso: Float
        get() = ((indice + 1).coerceAtMost(24)) / 24f
}