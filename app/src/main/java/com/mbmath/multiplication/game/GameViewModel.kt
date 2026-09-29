package com.mbmath.multiplication.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mbmath.multiplication.model.GameConfiguration
import com.mbmath.multiplication.model.GameMode
import com.mbmath.multiplication.model.Question
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

class GameViewModel : ViewModel() {
    private val _state = MutableStateFlow(GameState())
    val state: StateFlow<GameState> = _state.asStateFlow()

    private var currentGameMode = GameMode.FIND_RESULT

    fun onCredits() {
        _state.value = _state.value.copy(screen = Screens.Credits)
    }

    fun onHome() {
        _state.value = GameState()
    }

    fun showInstructions(configuration: GameConfiguration) {
        currentGameMode = configuration.gameMode
        _state.value = _state.value.copy(
            screen = Screens.Instructions(configuration),
            configuration = configuration
        )
    }

    fun onPlay() {
        val configuration = _state.value.configuration ?: return
        currentGameMode = configuration.gameMode
        val question = createQuestion(1)
        _state.value = _state.value.copy(
            screen = Screens.Play,
            questions = listOf(question),
            questionIndex = 0,
            stage = 1,
            stageQuestionCount = 1,
            feedback = null,
            selectedOptionIndex = null,
            disabledOptions = emptySet()
        )
    }

    fun submitAnswer(optionIndex: Int) {
        val currentState = _state.value
        val question = currentState.currentQuestion ?: return
        if (optionIndex in currentState.disabledOptions || currentState.selectedOptionIndex != null) return

        val isCorrect = optionIndex == question.correctOptionIndex
        val updatedQuestions = currentState.questions.toMutableList()
        updatedQuestions[currentState.questionIndex] = question.selectOption(optionIndex)

        if (!isCorrect) {
            _state.value = currentState.copy(
                questions = updatedQuestions,
                feedback = GameFeedback.Incorrect,
                disabledOptions = currentState.disabledOptions + optionIndex
            )
            return
        }

        val result = question.results[question.correctOptionIndex]
        _state.value = currentState.copy(
            questions = updatedQuestions,
            feedback = GameFeedback.Correct(
                factor1 = question.factor1[question.correctOptionIndex],
                factor2 = question.factor2[question.correctOptionIndex],
                result = result
            ),
            selectedOptionIndex = optionIndex
        )

        viewModelScope.launch {
            delay(1000)
            advanceToNextQuestion()
        }
    }

    fun showResults() {
        _state.value = _state.value.copy(screen = Screens.Score)
    }

    fun backToPlay() {
        _state.value = _state.value.copy(screen = Screens.Play)
    }

    private fun advanceToNextQuestion() {
        val currentState = _state.value
        if (currentState.questionIndex >= 23) {
            _state.value = currentState.copy(
                screen = Screens.Results,
                questionIndex = 24
            )
            return
        }

        val nextStageQuestionCount = if (currentState.stageQuestionCount == 3) 1 else currentState.stageQuestionCount + 1
        val nextStage = if (currentState.stageQuestionCount == 3) currentState.stage + 1 else currentState.stage
        val nextQuestion = createQuestion(nextStage)
        _state.value = currentState.copy(
            questions = currentState.questions + nextQuestion,
            questionIndex = currentState.questionIndex + 1,
            stage = nextStage,
            stageQuestionCount = nextStageQuestionCount,
            feedback = null,
            selectedOptionIndex = null,
            disabledOptions = emptySet()
        )
    }

    private fun createQuestion(stage: Int): Question {
        val factorPairs = mutableListOf<Pair<Int, Int>>()
        while (factorPairs.size < 3) {
            val factorPair = valuesForStage(stage)
            if (factorPair !in factorPairs) factorPairs += factorPair
        }
        val results = factorPairs.map { (factor1, factor2) -> factor1 * factor2 }.toIntArray()
        return Question(
            factor1 = factorPairs.map { it.first }.toIntArray(),
            factor2 = factorPairs.map { it.second }.toIntArray(),
            results = results,
            correctOptionIndex = Random.nextInt(0, 3)
        )
    }

    private fun valuesForStage(stage: Int): Pair<Int, Int> {
        val factor1 = when (stage) {
            1 -> 1
            2 -> Random.nextInt(2, 4)
            3 -> Random.nextInt(4, 6)
            4 -> 6
            5 -> 7
            6 -> 8
            7 -> 9
            else -> Random.nextInt(1, 10)
        }
        return factor1 to Random.nextInt(1, 10)
    }
}