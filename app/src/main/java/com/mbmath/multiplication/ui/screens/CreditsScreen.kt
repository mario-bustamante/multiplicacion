package com.mbmath.multiplication.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mbmath.multiplication.ui.components.AppScaffold
import com.mbmath.multiplication.ui.components.AssetImage
import com.mbmath.multiplication.R

@Composable
fun CreditsScreen(onHome: () -> Unit) {
    AppScaffold(
        title = stringResource(R.string.credits),
        onHome = onHome,
        content = { innerPadding ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AssetImage("images/logo.png", Modifier.size(150.dp))
                Spacer(Modifier.height(30.dp))
                Text(stringResource(R.string.designed_by))
                Spacer(Modifier.height(10.dp))
                Text(stringResource(R.string.implemented_by))
                Spacer(Modifier.height(10.dp))
                Text(stringResource(R.string.idea_by))
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CreditsScreenPreview() {
    CreditsScreen(
        onHome = {},
    )
}