package com.mbmath.multiplication.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import kotlinx.coroutines.delay
import com.mbmath.multiplication.game.GameState
import com.mbmath.multiplication.game.Screens
import com.mbmath.multiplication.model.Difficulty
import com.mbmath.multiplication.model.GameConfiguration
import com.mbmath.multiplication.model.GameMode
import com.mbmath.multiplication.model.Question
import com.mbmath.multiplication.ui.components.AppScaffold
import com.mbmath.multiplication.ui.components.AppSurface
import com.mbmath.multiplication.ui.components.localizedTitle
import com.mbmath.multiplication.R
import com.mbmath.multiplication.game.GameFeedback
import com.mbmath.multiplication.ui.components.AppIcon
import com.mbmath.multiplication.ui.components.AppScore
import com.mbmath.multiplication.ui.components.MultiplicationVisual

@Composable
fun PlayScreen(
    state: GameState,
    submitAnswer: (Int) -> Unit,
    showResults: () -> Unit,
    showInstructions: (GameConfiguration) -> Unit,
    onHome: () -> Unit
) {
    val question = state.currentQuestion ?: return
    val configuration = state.configuration ?: return
    val isLandscape = LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE
    var visibleFeedback by remember { mutableStateOf<GameFeedback?>(null) }
    var feedbackEvent by remember { mutableIntStateOf(0) }

    LaunchedEffect(state.feedback, state.currentQuestion?.errors, state.questionIndex) {
        state.feedback?.let { feedback ->
            visibleFeedback = feedback
            feedbackEvent++
        }
    }

    LaunchedEffect(feedbackEvent) {
        if (feedbackEvent == 0) return@LaunchedEffect
        delay(2000)
        visibleFeedback = null
    }

    val feedbackText = when (val feedback = visibleFeedback) {
        GameFeedback.Incorrect -> stringResource(R.string.feedback_incorrect)
        is GameFeedback.Correct -> stringResource(
            R.string.feedback_correct,
            feedback.factor1,
            feedback.factor2,
            feedback.result
        )
        null -> ""
    }

    val type = if (state.currentQuestionMode == GameMode.FIND_RESULT) "a" else "b"

    AppScaffold(
        title = configuration.gameMode.localizedTitle(),
        onHome = onHome,
        showResults = showResults,
        showInstructions = { showInstructions(configuration) },
        verticalScrollEnabled = false,
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                AppSurface(modifier = Modifier.fillMaxSize()) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(15.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(stringResource(R.string.question_progress, state.questionIndex + 1), fontWeight = FontWeight.Bold)
                            Spacer(Modifier.weight(1f))
                            Text(stringResource(R.string.stage, state.stage))
                        }
                        LinearProgressIndicator(
                            progress = { state.progress },
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(Modifier.height(12.dp))

                        BoxWithConstraints(
                            modifier = Modifier
                                .then(
                                    if (isLandscape) {
                                        Modifier.fillMaxSize()
                                    } else {
                                        Modifier.fillMaxWidth()
                                    }
                                )
                        ) {
                            val imageMaxHeight = maxHeight * 0.65f
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(5.dp),
                                modifier = Modifier
                                    .then(
                                        if (isLandscape) {
                                            Modifier.fillMaxSize()
                                        } else {
                                            Modifier.fillMaxWidth()
                                        }
                                    )
                            ) {
                                TextButton(
                                    onClick = { },
                                    enabled = false,
                                    shape = RectangleShape,
                                    contentPadding = PaddingValues(0.dp),
                                    modifier = Modifier
                                        .weight(1f)
                                        .fillMaxWidth()
                                        .then(if (isLandscape) Modifier.fillMaxHeight() else Modifier)
                                ) {
                                    MultiplicationVisual(
                                        type = if (type == "a") "a" else "b",
                                        firstFactor = question.factor2[question.correctOptionIndex],
                                        secondFactor = question.factor1[question.correctOptionIndex],
                                        modifier = if (isLandscape) {
                                            Modifier.fillMaxHeight()
                                        } else {
                                            Modifier.fillMaxWidth().heightIn(max = imageMaxHeight)
                                        }
                                    )
                                }

                                question.factor1.indices.forEach { optionIndex ->
                                    val isOptionEnabled = optionIndex !in state.disabledOptions &&
                                        state.selectedOptionIndex == null
                                    TextButton(
                                        onClick = { submitAnswer(optionIndex) },
                                        enabled = isOptionEnabled,
                                        colors = ButtonDefaults.textButtonColors(
                                            disabledContentColor = Color.White.copy(alpha = 0.2f)
                                        ),
                                        shape = RectangleShape,
                                        contentPadding = PaddingValues(0.dp),
                                        modifier = Modifier
                                            .weight(1f)
                                            .fillMaxWidth()
                                            .then(if (isLandscape) Modifier.fillMaxHeight() else Modifier)
                                            .drawWithContent {
                                                drawContent()
                                                if (!isOptionEnabled) {
                                                    drawRect(Color.White.copy(alpha = 0.2f))
                                                }
                                            }
                                    ) {
                                        MultiplicationVisual(
                                            type = if (type == "a") "b" else "a",
                                            firstFactor = question.factor2[optionIndex],
                                            secondFactor = question.factor1[optionIndex],
                                            modifier = if (isLandscape) {
                                                Modifier.fillMaxHeight()
                                            } else {
                                                Modifier.fillMaxWidth().heightIn(max = imageMaxHeight)
                                            }
                                        )
                                    }
                                }
                            }
                        }

                        if (!isLandscape) {
                            Spacer(Modifier.height(12.dp))
                            AppScore(
                                questions = state.questions,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(10.dp)
                            )
                        }
                    }
                }
            }
        }
    )

    visibleFeedback?.let { feedback ->
        val isCorrect = feedback is GameFeedback.Correct
        Popup(
            alignment = Alignment.TopCenter,
            offset = with(LocalDensity.current) { IntOffset(0, 96.dp.roundToPx()) },
            onDismissRequest = { visibleFeedback = null },
            properties = PopupProperties(focusable = false)
        ) {
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = Color.White,
                contentColor = Color.Black,
                shadowElevation = 6.dp
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    AppIcon(
                        tint = colorResource(if (isCorrect) R.color.correct else R.color.incorrect),
                        imageVector = if (isCorrect) Icons.Default.Check else Icons.Default.Close
                    )
                   // Spacer(Modifier.width(6.dp))
                    Text(feedbackText)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PlayScreenPreview() {
    PlayScreen(
        state = GameState(
            screen = Screens.Play,
            configuration = GameConfiguration(
                player = "Ana",
                gameMode = GameMode.FIND_RESULT,
                difficulty = Difficulty.EASY
            ),
            questions = listOf(
                Question(
                    factor1 = intArrayOf(9, 1, 9),
                    factor2 = intArrayOf(9, 1, 9),
                    results = intArrayOf(81, 2, 81),
                    correctOptionIndex = 0,
                    selectedOption = 0
                ),
                Question(
                    factor1 = intArrayOf(2, 2, 5),
                    factor2 = intArrayOf(3, 2, 2),
                    results = intArrayOf(6, 4, 10),
                    correctOptionIndex = 1,
                    selectedOption = 1
                ),
                Question(
                    factor1 = intArrayOf(2, 3, 5),
                    factor2 = intArrayOf(3, 3, 2),
                    results = intArrayOf(6, 9, 10),
                    correctOptionIndex = 0,
                    selectedOption = 0
                ),
            ),
        ),
        submitAnswer = {},
        showResults = {},
        showInstructions = {},
        onHome = {}
    )
}