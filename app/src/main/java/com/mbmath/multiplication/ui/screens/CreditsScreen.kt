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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mbmath.multiplication.model.Dificultad
import com.mbmath.multiplication.model.GameConfiguration
import com.mbmath.multiplication.model.ModoJuego

import com.mbmath.multiplication.ui.components.AppScaffold
import com.mbmath.multiplication.ui.components.AssetImage

@Composable
fun CreditsScreen(onHome: () -> Unit) {
    AppScaffold(
        title = "Créditos",
        onHome = onHome,
        content = { innerPadding ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AssetImage("images/personaje.png", Modifier.size(150.dp))
                Spacer(Modifier.height(30.dp))
                Text("Diseñado: Daniela Alejandra Olivares Diaz")
                Spacer(Modifier.height(10.dp))
                Text("Implementado: Mario Bernardo Bustamante Aguilar")
                Spacer(Modifier.height(10.dp))
                Text("Idea: Adriana Margot Mundaca Bugueño")
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