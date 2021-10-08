package az.siftoshka.junkyconverter.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = ColorPrimary,
    background = DarkGray,
    onBackground = White,
    onPrimary = White,
    surface = MediumGray,
    onSurface = White,
)

private val LightColorPalette = lightColors(
    primary = ColorPrimary,
    background = White,
    onBackground = MediumGray,
    onPrimary = White,
    surface = Gray,
    onSurface = MediumGray
)

@Composable
fun JunkyConverterTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
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