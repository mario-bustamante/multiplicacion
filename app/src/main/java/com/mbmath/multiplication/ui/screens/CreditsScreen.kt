package com.mbmath.multiplication.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mbmath.multiplication.ui.components.AppScaffold
import com.mbmath.multiplication.ui.components.AppSurface
import com.mbmath.multiplication.ui.components.AssetImage
import com.mbmath.multiplication.R

@Composable
fun CreditsScreen(onHome: () -> Unit) {
    AppScaffold(
        title = stringResource(R.string.credits),
        onHome = onHome,
        content = { innerPadding ->
            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                val isWideLayout = maxWidth >= 700.dp

                AppSurface(modifier = Modifier.fillMaxWidth()) {
                    if (isWideLayout) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(24.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(24.dp)
                        ) {
                            AssetImage(
                                path = "images/logo.webp",
                                modifier = Modifier.size(128.dp),
                                maintainAspectRatio = true
                            )
                            CreditList(modifier = Modifier.weight(1f))
                        }
                    } else {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            AssetImage(
                                path = "images/logo.webp",
                                modifier = Modifier.size(104.dp),
                                maintainAspectRatio = true
                            )
                            CreditList(modifier = Modifier.fillMaxWidth())
                        }
                    }
                }
            }
        }
    )
}

@Composable
private fun CreditList(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        CreditRow(stringResource(R.string.designed_by))
        HorizontalDivider()
        CreditRow(stringResource(R.string.implemented_by))
        HorizontalDivider()
        CreditRow(stringResource(R.string.idea_by))
    }
}

@Composable
private fun CreditRow(text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Icon(Icons.Default.Person, contentDescription = null)
        Text(
            text = text,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CreditsScreenPreview() {
    CreditsScreen(
        onHome = {},
    )
}