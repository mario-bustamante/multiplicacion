package com.mbmath.multiplication.game

import android.util.Log
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.mbmath.multiplication.data.GameConfigurationStore
import com.mbmath.multiplication.model.Difficulty
import com.mbmath.multiplication.model.GameConfiguration
import com.mbmath.multiplication.model.GameMode
import com.mbmath.multiplication.model.Question
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.BooleanArray
import kotlin.booleanArrayOf
import kotlin.random.Random

class GameViewModel(application: Application) : AndroidViewModel(application) {
    private val configurationStore = GameConfigurationStore(application)
    private val _state = MutableStateFlow(GameState())
    val state: StateFlow<GameState> = _state.asStateFlow()

    private var configurationChanged = false
    private var currentGameMode = GameMode.FIND_RESULT
    private var currentGameDifficulty = Difficulty.EASY
    private var questionStartedAtNanos: Long? = null

    init {
        viewModelScope.launch {
            val configuration = configurationStore.load()
            if (!configurationChanged) {
                _state.value = _state.value.copy(configuration = configuration)
            }
        }
    }

    fun showCredits() {
        _state.value = _state.value.copy(screen = Screens.Credits)
    }

    fun onHome() {
        viewModelScope.launch {
            val savedConfiguration = try {
                configurationStore.load()
            } catch (exception: java.io.IOException) {
                Log.e("GameViewModel", "Unable to load game configuration", exception)
                null
            }
            val configuration = savedConfiguration ?: _state.value.configuration

            configuration?.let {
                currentGameMode = it.gameMode
                currentGameDifficulty = it.difficulty
            }

            _state.value = _state.value.copy(
                screen = Screens.Home,
                configuration = configuration
            )
        }
    }

    fun updateConfiguration(configuration: GameConfiguration) {
        configurationChanged = true
        _state.value = _state.value.copy(configuration = configuration)
        persistConfiguration(configuration)
    }

    fun showHelp(configuration: GameConfiguration) {
        configurationChanged = true
        _state.value = _state.value.copy(
            screen = Screens.Help(configuration),
            configuration = configuration
        )
        persistConfiguration(configuration)
    }

    fun onPlay() {
        val configuration = _state.value.configuration ?: return
        val startedConfiguration = configuration.copy(wasLoggedIn = true)
        configurationChanged = true
        currentGameMode = configuration.gameMode
        currentGameDifficulty = configuration.difficulty

        val question = createQuestion(1)
        val questionMode = createQuestionMode()
        questionStartedAtNanos = System.nanoTime()

        _state.value = _state.value.copy(
            screen = Screens.Play,
            configuration = startedConfiguration,
            currentQuestionMode = questionMode,
            questions = listOf(question),
            questionIndex = 0,
            stage = 1,
            stageQuestionCount = 1,
            feedback = null,
            selectedOptionIndex = null,
            disabledOptions = emptySet()
        )
        persistConfiguration(startedConfiguration)
    }

    private fun persistConfiguration(configuration: GameConfiguration) {
        viewModelScope.launch {
            try {
                configurationStore.save(configuration)
            } catch (exception: java.io.IOException) {
                Log.e("GameViewModel", "Unable to save game configuration", exception)
            }
        }
    }

    fun submitAnswer(optionIndex: Int) {


        val currentState = _state.value

       // Log.d("submitAnswer", currentState.questions.toString())
        Log.d(
            "submitAnswer",
            currentState.questions.joinToString {
                "selectedOptions=${it.selectedOptions.contentToString()}, " +
                    "responseTimes=${it.responseTimes.contentToString()}"
            }
        )

        val question = currentState.currentQuestion ?: return
        if (optionIndex in currentState.disabledOptions || currentState.selectedOptionIndex != null) return

        val isCorrect = optionIndex == question.correctOptionIndex
        val updatedQuestions = currentState.questions.toMutableList()
        val elapsedSeconds = questionStartedAtNanos?.let {
            (System.nanoTime() - it) / 1_000_000_000f
        } ?: 0f
        val responseTimes = question.responseTimes.copyOf()
        responseTimes[optionIndex] = elapsedSeconds
        val updatedQuestion = question.selectOption(optionIndex).copy(
            responseTimes = responseTimes
        )

        if (!isCorrect) {
            updatedQuestions[currentState.questionIndex] = updatedQuestion
            _state.value = currentState.copy(
                questions = updatedQuestions,
                feedback = GameFeedback.Incorrect,
                disabledOptions = currentState.disabledOptions + optionIndex
            )
            return
        }

        questionStartedAtNanos = null
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
        questionStartedAtNanos = System.nanoTime()


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
        val difficulty: Int = when (currentGameDifficulty) {
            Difficulty.ADVANCED -> {
                3
            }
            Difficulty.INTERMEDIATE -> {
                2
            }
            else -> {
                1
            }
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

        var factor2 = Random.nextInt(difficulty, 10)

        val factor3 = factor1
        if (Random.nextBoolean()) {
            factor1 = factor2
            factor2 = factor3
        }

        return factor1 to factor2
    }
}