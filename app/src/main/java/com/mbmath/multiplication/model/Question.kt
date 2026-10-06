package com.mbmath.multiplication.model

data class Question(
    val factor1: IntArray = intArrayOf(0, 0, 0),
    val factor2: IntArray = intArrayOf(0, 0, 0),
    val results: IntArray = intArrayOf(0, 0, 0), // product

    val selectedOptions: BooleanArray = booleanArrayOf(false, false, false), // whether each option was selected, regardless of correctness
    val responseTimes: FloatArray = floatArrayOf(0f, 0f, 0f), // elapsed seconds when each option was selected

    val correctOptionIndex: Int = 0, // index of the correct option
    val selectedOption: Int = 0, // selected option
) {
    fun selectOption(optionIndex: Int): Question {
        val updatedSelectedOptions = selectedOptions.copyOf()
        updatedSelectedOptions[optionIndex] = true
        return copy(
            selectedOption = optionIndex + 1,
            selectedOptions = updatedSelectedOptions
        )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Question

        if (correctOptionIndex != other.correctOptionIndex) return false
        if (selectedOption != other.selectedOption) return false
        if (!factor1.contentEquals(other.factor1)) return false
        if (!factor2.contentEquals(other.factor2)) return false
        if (!results.contentEquals(other.results)) return false
        if (!selectedOptions.contentEquals(other.selectedOptions)) return false
        if (!responseTimes.contentEquals(other.responseTimes)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = correctOptionIndex
        result = 31 * result + selectedOption
        result = 31 * result + factor1.contentHashCode()
        result = 31 * result + factor2.contentHashCode()
        result = 31 * result + results.contentHashCode()
        result = 31 * result + selectedOptions.contentHashCode()
        result = 31 * result + responseTimes.contentHashCode()
        return result
    }
}