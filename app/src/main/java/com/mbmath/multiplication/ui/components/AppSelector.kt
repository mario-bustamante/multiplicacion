package com.mbmath.multiplication.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun <T> AppSelector(
    label: String,
    options: List<T>,
    selection: T,
    onSelection: (T) -> Unit,
    text: @Composable (T) -> String
) {
    Column(modifier = Modifier.padding(top = 12.dp)) {
        Text(
            label,
            modifier = Modifier.padding(bottom = 5.dp),
            fontWeight = FontWeight.Bold,
        )
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(6.dp, Alignment.CenterHorizontally),
            verticalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            options.forEach { option ->
                OutlinedButton(
                    onClick = { onSelection(option) },
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = Color(0xFF2E7D32),
                        contentColor = Color.White
                    ),
                    contentPadding = PaddingValues(10.dp)
                ) {
                    Text(if (option == selection) "✓ ${text(option)}" else text(option))
                }
            }
        }
    }
}