package com.mbmath.multiplication.game

import com.mbmath.multiplication.model.Difficulty
import com.mbmath.multiplication.model.GameConfiguration
import com.mbmath.multiplication.model.GameMode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Test

class GameViewModelTest {
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun mixedModeKeepsQuestionModeStableUntilNextQuestion(): Unit = runTest {
        Dispatchers.setMain(StandardTestDispatcher(testScheduler))
        try {
            val viewModel = GameViewModel()
            viewModel.showHelp(
                GameConfiguration("Ana", GameMode.MIXED, Difficulty.EASY)
            )
            viewModel.onPlay()

            val firstQuestionMode = viewModel.state.value.currentQuestionMode
            assertEquals(
                true,
                firstQuestionMode == GameMode.FIND_RESULT ||
                    firstQuestionMode == GameMode.FIND_MULTIPLICATION
            )

            val question = requireNotNull(viewModel.state.value.currentQuestion)
            viewModel.submitAnswer((question.correctOptionIndex + 1) % 3)
            assertEquals(firstQuestionMode, viewModel.state.value.currentQuestionMode)

            viewModel.submitAnswer(question.correctOptionIndex)
            advanceTimeBy(1_000)
            runCurrent()

            val nextQuestionMode = viewModel.state.value.currentQuestionMode
            assertEquals(
                true,
                nextQuestionMode == GameMode.FIND_RESULT ||
                    nextQuestionMode == GameMode.FIND_MULTIPLICATION
            )
        } finally {
            Dispatchers.resetMain()
        }
    }

    @Test
    fun incorrectAnswersDisableOptionsAndIncrementErrorCount(): Unit = runTest {
        val viewModel = GameViewModel()
        viewModel.showHelp(
            GameConfiguration("Ana", GameMode.FIND_RESULT, Difficulty.EASY)
        )
        viewModel.onPlay()

        val question = requireNotNull(viewModel.state.value.currentQuestion)
        val firstIncorrectIndex = (question.correctOptionIndex + 1) % 3
        viewModel.submitAnswer(firstIncorrectIndex)

        assertEquals(setOf(firstIncorrectIndex), viewModel.state.value.disabledOptions)
        assertEquals(1, requireNotNull(viewModel.state.value.currentQuestion).errors)

        viewModel.submitAnswer(firstIncorrectIndex)
        assertEquals(1, requireNotNull(viewModel.state.value.currentQuestion).errors)

        val secondIncorrectIndex = (question.correctOptionIndex + 2) % 3
        viewModel.submitAnswer(secondIncorrectIndex)

        assertEquals(
            setOf(firstIncorrectIndex, secondIncorrectIndex),
            viewModel.state.value.disabledOptions
        )
        assertEquals(2, requireNotNull(viewModel.state.value.currentQuestion).errors)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun createsAListOf24QuestionsForACompletedGame(): Unit = runTest {
        Dispatchers.setMain(StandardTestDispatcher(testScheduler))
        try {
            val viewModel = GameViewModel()
            viewModel.showHelp(
                GameConfiguration("Ana", GameMode.FIND_RESULT, Difficulty.EASY)
            )
            viewModel.onPlay()

            assertEquals(1, viewModel.state.value.questions.size)

            repeat(24) {
                val question = requireNotNull(viewModel.state.value.currentQuestion)
                val correctIndex = question.correctOptionIndex
                println(
                    "Pregunta ${it + 1}: ${question.factor1[correctIndex]} x " +
                        "${question.factor2[correctIndex]} = ${question.results[correctIndex]}"
                )

                assertEquals(3, question.factor1.size)
                assertEquals(3, question.factor2.size)
                assertEquals(3, question.results.size)
                assertEquals(
                    question.factor1[question.correctOptionIndex] * question.factor2[question.correctOptionIndex],
                    question.results[question.correctOptionIndex]
                )

                viewModel.submitAnswer(question.correctOptionIndex)
                advanceTimeBy(1_000)
                runCurrent()
            }

            val completedGame = viewModel.state.value
            assertEquals(24, completedGame.questions.size)
            assertEquals(24, completedGame.questionIndex)
            assertEquals(Screens.Results, completedGame.screen)
            completedGame.questions.forEach { question ->
                assertEquals(question.correctOptionIndex + 1, question.selectedOption)
            }
            val correctFactorPairs = completedGame.questions.map { question ->
                val correctIndex = question.correctOptionIndex
                question.factor1[correctIndex] to question.factor2[correctIndex]
            }
            assertEquals(
                "A correct factor pair was repeated",
                correctFactorPairs.size,
                correctFactorPairs.toSet().size
            )
        } finally {
            Dispatchers.resetMain()
        }
    }
}