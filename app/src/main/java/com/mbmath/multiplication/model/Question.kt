package com.mbmath.multiplication.model

data class Question(
    val factor1: IntArray = intArrayOf(0, 0, 0),
    val factor2: IntArray = intArrayOf(0, 0, 0),
    val results: IntArray = intArrayOf(0, 0, 0),
    val correctOptionIndex: Int = 0,
    val selectedOption: Int = 0,
    val errors: Int = 0,
) {
    fun selectOption(optionIndex: Int): Question = copy(selectedOption = optionIndex + 1)
}