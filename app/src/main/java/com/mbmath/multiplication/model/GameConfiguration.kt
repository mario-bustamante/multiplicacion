package com.mbmath.multiplication.model

enum class ModoJuego(val titulo: String) {
    BUSCAR_RESULTADO("Buscar Resultado"),
    BUSCAR_MULTIPLICACION("Buscar Multiplicación"),
    COMBINADO("Combinado")
}

enum class Dificultad(val titulo: String) {
    FACIL("Fácil"),
    INTERMEDIO("Intermedio"),
    AVANZADO("Avanzado")
}

data class GameConfiguration(
    val jugador: String,
    val modo: ModoJuego,
    val dificultad: Dificultad
)