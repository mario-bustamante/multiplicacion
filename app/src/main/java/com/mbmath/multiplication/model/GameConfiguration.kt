package com.mbmath.multiplication.model

enum class GameMode {
    FIND_RESULT,
    FIND_MULTIPLICATION,
    MIXED
}

enum class Difficulty {
    EASY,
    INTERMEDIATE,
    ADVANCED
}

data class GameConfiguration(
    val player: String,
    val gameMode: GameMode,
    val difficulty: Difficulty,
    val wasLoggedIn: Boolean = false
)