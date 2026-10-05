package com.mbmath.multiplication.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mbmath.multiplication.R
import com.mbmath.multiplication.game.GameState
import com.mbmath.multiplication.game.Screens
import com.mbmath.multiplication.model.Difficulty
import com.mbmath.multiplication.model.GameConfiguration
import com.mbmath.multiplication.model.GameMode
import com.mbmath.multiplication.model.Question
import com.mbmath.multiplication.ui.screens.PlayScreen

private const val CARD_ASPECT_RATIO = 755f / 1060f
private val TYPE_A_ROW_COLORS = listOf(
    Color(0xFF4F81BD),
    Color(0xFFFA8C13),
    Color(0xFFD33985),
    Color(0xFFFC0101),
    Color(0xFF01B0F2),
    Color(0xFFFE339A),
    Color(0xFF03AE4E),
    Color(0xFF7130A1),
    Color(0xFFFFBE03)
)


@Composable
fun MultiplicationVisual(
    type: String,
    firstFactor: Int,
    secondFactor: Int,
    modifier: Modifier = Modifier
) {
    val shape = RoundedCornerShape(10.dp)
    val normalizedType = type.lowercase()
    val isLandscape = LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE

    val segmentSize = if (!isLandscape) {
        8.dp // vertical
    } else {
        16.dp
    }

    val fontSize = if (!isLandscape) {
        // vertical
        if (normalizedType == "b") { 9.sp } else { 12.sp }
    } else {
        if (normalizedType == "b") { 16.sp } else { 19.sp }
    }

    val textCard = if (normalizedType == "b") {
        "${firstFactor*secondFactor}"
    } else {
        "$firstFactor x $secondFactor"
    }

    Box(
        modifier = modifier
            .aspectRatio(CARD_ASPECT_RATIO, matchHeightConstraintsFirst = true)
            .clip(shape)
            .background(if (normalizedType == "b") Color(0xFF10005C) else Color.White)
            .border(1.dp, Color.Black, shape)
    ) {
        Text(
            text = textCard,
            color = if (normalizedType == "b") Color.White else Color.Black,
            fontSize = fontSize,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .align(Alignment.TopStart)
                //.padding(4.dp)
                .padding(start = 7.dp, top=3.dp)
        )

        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .border(0.4.dp, Color.Gray)
        ) {
            repeat(firstFactor.coerceAtLeast(0)) { rowIndex ->
                Row(
                    horizontalArrangement = Arrangement.spacedBy(0.dp),
                    modifier = Modifier.height(segmentSize)
                        .background(
                            if (normalizedType == "a") {
                                TYPE_A_ROW_COLORS.getOrElse(rowIndex) { TYPE_A_ROW_COLORS.last() }
                            } else {
                                colorResource(R.color.purple_500)
                            }
                        )
                ) {
                    repeat(secondFactor.coerceAtLeast(0)) {
                        AssetImage(
                            path = "images/${if (normalizedType == "a") "a" else "b"}.png",
                            modifier = Modifier.size(segmentSize)
                        )
                    }
                }
            }
        }

        Text(
            text = textCard,
            color = if (normalizedType == "b") Color.White else Color.Black,
            fontSize = fontSize,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 7.dp, bottom = 3.dp)
        )
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
        showHelp = {},
        onHome = {}
    )
}