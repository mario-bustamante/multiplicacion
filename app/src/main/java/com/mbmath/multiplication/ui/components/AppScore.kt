package com.mbmath.multiplication.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mbmath.multiplication.R
import com.mbmath.multiplication.model.Question

@Composable
fun AppScore(
    questions: List<Question>,
    modifier: Modifier = Modifier,
) {

    val answeredQuestions = questions.mapIndexedNotNull { index, question ->
        question.takeIf { it.selectedOption != 0 }?.let { index to it }
    }

    LazyColumn(modifier = modifier) {
        items(answeredQuestions) { (index, question) ->
            val isCorrect = question.selectedOption == question.correctOptionIndex + 1
            val status = when {
                isCorrect -> stringResource(R.string.correct)
                else -> stringResource(R.string.incorrect)
            }
            val answer = question.results[question.selectedOption - 1].toString()
            Row(modifier = Modifier.padding(vertical = 6.dp)) {
                Text(
                    stringResource(
                        R.string.score_question,
                        index + 1,
                        question.factor1[question.correctOptionIndex],
                        question.factor2[question.correctOptionIndex],
                        answer
                    ),
                    modifier = Modifier.weight(1f),
                    color = if (isCorrect) colorResource(R.color.correct) else colorResource(R.color.incorrect)
                )
                Icon(
                    imageVector = if (isCorrect) Icons.Default.Check else Icons.Default.Close,
                    contentDescription = status,
                    tint = if (isCorrect) colorResource(R.color.correct) else colorResource(R.color.incorrect)
                )
            }
        }
    }
}