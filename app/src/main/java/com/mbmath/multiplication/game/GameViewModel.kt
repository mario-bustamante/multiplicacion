package com.mbmath.multiplication.game

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mbmath.multiplication.model.Difficulty
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
    private var currentGameDifficulty = Difficulty.EASY

    fun showCredits() {
        _state.value = _state.value.copy(screen = Screens.Credits)
    }

    fun onHome() {
        _state.value.configuration?.let { configuration ->
            currentGameMode = configuration.gameMode
            currentGameDifficulty = configuration.difficulty
        }

        _state.value = _state.value.copy(
            screen = Screens.Home,
        )
    }

    fun updateConfiguration(configuration: GameConfiguration) {
        _state.value = _state.value.copy(configuration = configuration)
    }

    fun showInstructions(configuration: GameConfiguration) {
        _state.value = _state.value.copy(
            screen = Screens.Instructions(configuration),
            configuration = configuration
        )
    }

    fun onPlay() {
        val configuration = _state.value.configuration ?: return
        
        configuration.wasLoggedIn = true
        currentGameMode = configuration.gameMode
        currentGameDifficulty = configuration.difficulty

        val question = createQuestion(1)
        val questionMode = createQuestionMode()

        _state.value = _state.value.copy(
            screen = Screens.Play,
            currentQuestionMode = questionMode,
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
        val updatedQuestion = question.selectOption(optionIndex)

        if (!isCorrect) {
            updatedQuestions[currentState.questionIndex] = updatedQuestion.copy(
                errors = question.errors + 1
            )
            _state.value = currentState.copy(
                questions = updatedQuestions,
                feedback = GameFeedback.Incorrect,
                disabledOptions = currentState.disabledOptions + optionIndex
            )
            return
        }

        updatedQuestions[currentState.questionIndex] = updatedQuestion
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
        var nextQuestion: Question
        do {
            nextQuestion = createQuestion(nextStage)
        } while (currentState.questions.any { hasSameFactorPairs(it, nextQuestion) })
        val nextQuestionMode = createQuestionMode()


        _state.value = currentState.copy(
            questions = currentState.questions + nextQuestion,
            currentQuestionMode = nextQuestionMode,
            questionIndex = currentState.questionIndex + 1,
            stage = nextStage,
            stageQuestionCount = nextStageQuestionCount,
            feedback = null,
            selectedOptionIndex = null,
            disabledOptions = emptySet()
        )
    }

    // Returns true when both questions have the same correct ordered factor pair.
    private fun hasSameFactorPairs(first: Question, second: Question): Boolean {
        val firstCorrectIndex = first.correctOptionIndex
        val secondCorrectIndex = second.correctOptionIndex
        return first.factor1[firstCorrectIndex] == second.factor1[secondCorrectIndex] &&
            first.factor2[firstCorrectIndex] == second.factor2[secondCorrectIndex]
    }

    private fun createQuestion(stage: Int): Question {
        val factorPairs = mutableListOf<Pair<Int, Int>>()
        // Keep the three ordered factor pairs unique within this question.
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

    private fun createQuestionMode(): GameMode = when (currentGameMode) {
        GameMode.FIND_RESULT -> GameMode.FIND_RESULT
        GameMode.FIND_MULTIPLICATION -> GameMode.FIND_MULTIPLICATION
        GameMode.MIXED -> if (Random.nextBoolean()) {
            GameMode.FIND_RESULT
        } else {
            GameMode.FIND_MULTIPLICATION
        }
    }

    private fun valuesForStage(stage: Int): Pair<Int, Int> {
        val difficulty: Int = if(currentGameDifficulty == Difficulty.ADVANCED) {
            3
        } else if(currentGameDifficulty == Difficulty.INTERMEDIATE) {
            2
        } else {
            1
        }
        var factor1 = when (stage) {
            1 -> Random.nextInt(difficulty, 1 + difficulty)
            2 -> Random.nextInt(difficulty, 1 + difficulty)
            3 -> Random.nextInt(1 + difficulty, 2 + difficulty)
            4 -> Random.nextInt(1 + difficulty, 2 + difficulty)
            5 -> Random.nextInt(1 + difficulty, 2 + difficulty)
            6 -> Random.nextInt(1 + difficulty, 3 + difficulty)
            7 -> Random.nextInt(2 + difficulty, 3 + difficulty)
            8 -> Random.nextInt(2 + difficulty, 3 + difficulty)
            9 -> Random.nextInt(2 + difficulty, 4 + difficulty)
            10 -> Random.nextInt(2 + difficulty, 4 + difficulty)
            11 -> Random.nextInt(2 + difficulty, 5 + difficulty)
            12 -> Random.nextInt(2 + difficulty, 5 + difficulty)
            else -> Random.nextInt(2 + difficulty, 7 + difficulty)
        }

        var factor2 = Random.nextInt(1, 10)

        val factor3 = factor1
        if (Random.nextBoolean()) {
            factor1 = factor2
            factor2 = factor3
        }

        return factor1 to factor2
    }
}