package com.mbmath.multiplication.game

import com.mbmath.multiplication.model.GameConfiguration
import com.mbmath.multiplication.model.Question

sealed interface Screens {
    data object Home : Screens
    data class Instructions(val configuration: GameConfiguration) : Screens
    data object Play : Screens
    data object Score : Screens
    data object Results : Screens
    data object Credits : Screens
}

data class GameState(
    val pantalla: Screens = Screens.Home,
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