package com.mbmath.multiplication.game

import com.mbmath.multiplication.model.GameConfiguration
import com.mbmath.multiplication.model.GameMode
import com.mbmath.multiplication.model.Question

sealed interface Screens {
    data object Home : Screens
    data class Help(val configuration: GameConfiguration) : Screens
    data object Play : Screens
    data object Score : Screens
    data object Results : Screens
    data object Credits : Screens
}

sealed interface GameFeedback {
    data object Incorrect : GameFeedback
    data class Correct(val factor1: Int, val factor2: Int, val result: Int) : GameFeedback
}

data class GameState(
    val screen: Screens = Screens.Home,
    val configuration: GameConfiguration? = null,
    val currentQuestionMode: GameMode = GameMode.FIND_RESULT,
    val questions: List<Question> = emptyList(),
    val questionIndex: Int = 0,
    val stage: Int = 1,
    val stageQuestionCount: Int = 1,
    val feedback: GameFeedback? = null,
    val selectedOptionIndex: Int? = null,
    val disabledOptions: Set<Int> = emptySet()
) {
    val currentQuestion: Question?
        get() = questions.getOrNull(questionIndex)

    val progress: Float
        get() = ((questionIndex + 1).coerceAtMost(24)) / 24f
}