package com.mbmath.multiplication.ui.components

import android.graphics.BitmapFactory
import android.graphics.BitmapFactory.decodeStream
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext

@Composable
fun AssetImage(
    path: String,
    modifier: Modifier = Modifier,
    maintainAspectRatio: Boolean = false,
    matchHeightConstraintsFirst: Boolean = false
) {
    val context = LocalContext.current
    val bitmap = remember(path) {
        runCatching { context.assets.open(path).use(BitmapFactory::decodeStream) }.getOrNull()
    }
    if (bitmap != null) {
        val imageModifier = if (maintainAspectRatio) {
            modifier.aspectRatio(
                ratio = bitmap.width.toFloat() / bitmap.height,
                matchHeightConstraintsFirst = matchHeightConstraintsFirst
            )
        } else {
            modifier
        }
        Image(bitmap = bitmap.asImageBitmap(), contentDescription = null, modifier = imageModifier)
    } else {
        Card(modifier = modifier) {}
    }
}