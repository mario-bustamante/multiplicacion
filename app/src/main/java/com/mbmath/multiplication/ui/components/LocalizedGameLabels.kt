package com.mbmath.multiplication.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.mbmath.multiplication.R
import com.mbmath.multiplication.model.Difficulty
import com.mbmath.multiplication.model.GameMode

@Composable
fun GameMode.localizedTitle(): String = stringResource(
    when (this) {
        GameMode.FIND_RESULT -> R.string.mode_find_result
        GameMode.FIND_MULTIPLICATION -> R.string.mode_find_multiplication
        GameMode.MIXED -> R.string.mode_mixed
    }
)

@Composable
fun Difficulty.localizedTitle(): String = stringResource(
    when (this) {
        Difficulty.EASY -> R.string.difficulty_easy
        Difficulty.INTERMEDIATE -> R.string.difficulty_intermediate
        Difficulty.ADVANCED -> R.string.difficulty_advanced
    }
)