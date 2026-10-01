package com.mbmath.multiplication.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.background
import androidx.compose.foundation.rememberScrollState
import androidx.core.os.LocaleListCompat
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mbmath.multiplication.R
import androidx.compose.ui.platform.LocalLocale
import androidx.compose.ui.tooling.preview.Preview
import com.mbmath.multiplication.ui.screens.HomeScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScaffold(
    content: @Composable (PaddingValues) -> Unit,
    bottomBar: (@Composable () -> Unit)? = null,
    verticalScrollEnabled: Boolean = true,
    title: String = "",
    onHome: (() -> Unit)? = null,
    onCredits: (() -> Unit)? = null,
    showResults: (() -> Unit)? = null,
    showLanguageSelector: Boolean = false
) {
    var languageMenuExpanded by remember { mutableStateOf(false) }
    val currentLanguage = AppCompatDelegate.getApplicationLocales()
        .get(0)
        ?.language
        ?: LocalLocale.current.platformLocale.language
    val languageCode = when (currentLanguage) {
        "es" -> "ES"
        "pt" -> "PT"
        "ja" -> "JA"
        else -> "EN"
    }
    val languages = listOf(
        "en" to "English",
        "es" to "Español",
        "pt" to "Português",
        "ja" to "日本語"
    )

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),

        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier.height(86.dp),
                title = {
                    Text(
                        title.ifEmpty { stringResource(R.string.app_name) },
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        fontSize = 19.sp
                    )
                },
                actions = {
                    if (showLanguageSelector) {
                        Box {
                            val languageDescription = stringResource(R.string.select_language)
                            TextButton(onClick = { languageMenuExpanded = true }) {
                                Text(
                                    languageCode,
                                    color = Color.White,
                                    modifier = Modifier.semantics {
                                        contentDescription = languageDescription
                                    }
                                )
                            }
                            DropdownMenu(
                                expanded = languageMenuExpanded,
                                onDismissRequest = { languageMenuExpanded = false }
                            ) {
                                languages.forEach { (languageTag, languageName) ->
                                    DropdownMenuItem(
                                        text = { Text(languageName) },
                                        onClick = {
                                            languageMenuExpanded = false
                                            AppCompatDelegate.setApplicationLocales(
                                                LocaleListCompat.forLanguageTags(languageTag)
                                            )
                                        }
                                    )
                                }
                            }
                        }
                    }
                    onCredits?.let { onClick ->
                        IconButton(onClick = onClick) {
                            Icon(
                                imageVector = Icons.Default.Info,
                                contentDescription = stringResource(R.string.credits),
                                tint = Color.LightGray
                            )
                        }
                    }
                    showResults?.let { onClick ->
                        IconButton(onClick = onClick) {
                            Icon(
                                imageVector = Icons.Default.CheckCircle,
                                contentDescription = stringResource(R.string.credits),
                                tint = Color.LightGray
                            )
                        }
                    }
                },
                navigationIcon = {
                    onHome?.let { onClick ->
                        IconButton(onClick = onClick) {
                            Icon(
                                imageVector = Icons.Default.Home,
                                contentDescription = stringResource(R.string.home),
                                tint = Color.LightGray
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF1565C0),
                    titleContentColor = Color.White
                )
            )
        },
        bottomBar = bottomBar ?: {}
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
              //
        ) {
            AssetImage(
                path = "images/background.png",
                modifier = Modifier
                    .fillMaxSize()
                    //.blur(0.5.dp)
                    //.background(Color.White.copy(alpha = 1f))
                ,
                contentScale = ContentScale.Crop,
                alignment = Alignment.TopCenter,
                colorFilter = ColorFilter.colorMatrix(
                    ColorMatrix().apply {
                        setToScale(2f, 2f, 2.5f, 1f)
                    }
                )
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .then(
                        if (verticalScrollEnabled) {
                            Modifier.verticalScroll(rememberScrollState())
                        } else {
                            Modifier
                        }
                    )
                    .padding(20.dp)
            ) {
                content(paddingValues)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AppScaffoldPreview() {
    HomeScreen(
        showInstructions = {},
        onCredits  = {}
    )
}