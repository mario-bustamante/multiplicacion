package com.multiplicacion.app.model

data class Pregunta(
    val valor1: IntArray = intArrayOf(0, 0, 0),
    val valor2: IntArray = intArrayOf(0, 0, 0),
    val resultado: IntArray = intArrayOf(0, 0, 0),
    val correcto: Int = 0,
    val respuesta: Int = 0
) {
    fun responder(opcion: Int): Pregunta = copy(respuesta = opcion + 1)
}