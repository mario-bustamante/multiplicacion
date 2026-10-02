package com.mbmath.multiplication.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.mbmath.multiplication.R
import com.mbmath.multiplication.model.Question

@Composable
fun AppIcon(
    modifier: Modifier = Modifier,
    tint: Color,
    imageVector: ImageVector,
    contentDescription: String? = null,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(25.dp)
            .background(Color.Transparent, shape = CircleShape)
            .border(
                width = 2.dp,
                color = tint,
                shape = CircleShape
            )
    ) {
        Icon(
            modifier = Modifier
                .size(18.dp),
            imageVector = imageVector,
            contentDescription = contentDescription,
            tint = tint
        )
    }
}