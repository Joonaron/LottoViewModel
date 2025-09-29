package com.example.lottoviewmodel.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.Typography

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF6200EE),       // purple_500
    onPrimary = Color(0xFFFFFFFF),     // white
    secondary = Color(0xFF03DAC5),     // teal_200
    onSecondary = Color(0xFF000000),   // black
)

@Composable
fun LottoViewModelTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography(),
        content = content
    )
}