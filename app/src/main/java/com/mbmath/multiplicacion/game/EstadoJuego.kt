package com.multiplicacion.app.game

import com.multiplicacion.app.model.ConfiguracionJuego
import com.multiplicacion.app.model.Pregunta

sealed interface PantallaJuego {
    data object Inicio : PantallaJuego
    data class Ayuda(val configuracion: ConfiguracionJuego) : PantallaJuego
    data object Pregunta : PantallaJuego
    data object Resultados : PantallaJuego
    data object Felicitacion : PantallaJuego
    data object Creditos : PantallaJuego
}

data class EstadoJuego(
    val pantalla: PantallaJuego = PantallaJuego.Inicio,
    val configuracion: ConfiguracionJuego? = null,
    val preguntas: List<Pregunta> = emptyList(),
    val indice: Int = 0,
    val etapa: Int = 1,
    val contadorEtapa: Int = 1,
    val mensaje: String = "",
    val opcionSeleccionada: Int? = null,
    val opcionesDeshabilitadas: Set<Int> = emptySet()
) {
    val preguntaActual: Pregunta?
        get() = preguntas.getOrNull(indice)

    val progreso: Float
        get() = ((indice + 1).coerceAtMost(24)) / 24f
}