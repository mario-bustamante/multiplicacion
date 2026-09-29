package com.mbmath.multiplication.ui.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mbmath.multiplication.model.Question
import com.mbmath.multiplication.ui.components.AppScaffold
import com.mbmath.multiplication.R
import com.mbmath.multiplication.ui.components.AppScore

@Composable
fun ScoreScreen(
    questions: List<Question>,
    backToPlay: () -> Unit
) {
    AppScaffold(
        title = stringResource(R.string.results),
        verticalScrollEnabled = false,
        content = { innerPadding ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                AppScore(
                    questions = questions,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .clip(RoundedCornerShape(10.dp))
                        .border(1.dp, colorResource(R.color.black), RoundedCornerShape(10.dp))
                        .padding(10.dp)
                )

                Spacer(Modifier.height(30.dp))
                Button(
                    onClick = backToPlay,
                    //modifier = Modifier.fillMaxWidth()
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                        Spacer(Modifier.width(8.dp))
                        Text(stringResource(R.string.play_again))
                    }
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ScoreScreenPreview() {
    val questions = listOf(
        Question(
            factor1 = intArrayOf(2, 4, 5),
            factor2 = intArrayOf(3, 2, 2),
            results = intArrayOf(6, 8, 10),
            correctOptionIndex = 0,
            selectedOption = 1
        ),
        Question(
            factor1 = intArrayOf(9, 4, 5),
            factor2 = intArrayOf(3, 2, 2),
            results = intArrayOf(27, 8, 10),
            correctOptionIndex = 0,
            selectedOption = 2
        )
    )

    ScoreScreen(
        questions = questions,
        backToPlay = {},
    )
}