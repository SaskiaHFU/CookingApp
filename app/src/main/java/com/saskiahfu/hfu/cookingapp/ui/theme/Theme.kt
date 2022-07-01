package com.saskiahfu.hfu.cookingapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = Black,
    secondary = LightYellow,
    background = White,
    surface = LightYellow,

    )

private val LightColorPalette = lightColors(
    primary = Black,
    secondary = LightYellow,
    background = White,
    surface = LightYellow,
)

@Composable
fun CookingAppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
