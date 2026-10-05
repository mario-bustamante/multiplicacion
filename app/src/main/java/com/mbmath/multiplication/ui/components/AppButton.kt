package com.mbmath.multiplication.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mbmath.multiplication.R
import com.mbmath.multiplication.model.GameConfiguration

@Composable
fun AppButton(
    onClick: () -> Unit,
    icon: ImageVector,
    textDescription: Int,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        border = BorderStroke(1.dp, colorResource(androidx.cardview.R.color.cardview_light_background)),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(R.color.button_primary),
            contentColor = colorResource(R.color.white)
        ),
        modifier = Modifier.widthIn(min = 150.dp),
    ) {
        Row(
            modifier = Modifier,
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(icon, contentDescription = stringResource(textDescription))
            Spacer(Modifier.width(5.dp))
            Text(stringResource(textDescription))
        }
    }
}