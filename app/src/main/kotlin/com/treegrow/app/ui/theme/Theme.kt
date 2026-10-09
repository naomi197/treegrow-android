package com.treegrow.app.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val PrimaryGreen = Color(0xFF2E7D32)
val SecondaryGreen = Color(0xFF66BB6A)
val TertiaryGreen = Color(0xFFA5D6A7)

private val LightColors = lightColorScheme(
    primary = PrimaryGreen,
    secondary = SecondaryGreen,
    tertiary = TertiaryGreen,
)

@Composable
fun TreeGrowTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColors,
        content = content,
    )
}
