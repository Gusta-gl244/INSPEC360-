package br.com.inspec360.presentation.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val PrimaryDarkGreen = Color(0xFF2D5A3D)
private val AccentGold = Color(0xFFC4A747)
private val SeverityLightGreen = Color(0xFF4CAF50)
private val SeverityModerateYellow = Color(0xFFFFC107)
private val SeveritySevereOrange = Color(0xFFFF9800)
private val SeverityCriticalRed = Color(0xFFF44336)
private val White = Color(0xFFFFFFFF)
private val Black = Color(0xFF000000)
private val LightGray = Color(0xFFF5F5F5)
private val DarkGray = Color(0xFF424242)
private val BorderGray = Color(0xFFE0E0E0)

private val ColorScheme = lightColorScheme(
    primary = PrimaryDarkGreen,
    secondary = AccentGold,
    tertiary = AccentGold,
    background = White,
    surface = LightGray,
    onPrimary = White,
    onSecondary = Black,
    onBackground = Black,
    onSurface = DarkGray,
    error = SeverityCriticalRed
)

@Composable
fun INSPEC360Theme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = ColorScheme,
        typography = INSPEC360Typography,
        content = content
    )
}

// Exportar cores semânticas
object INSPEC360Colors {
    val PrimaryDarkGreen = PrimaryDarkGreen
    val AccentGold = AccentGold
    val SeverityLight = SeverityLightGreen
    val SeverityModerate = SeverityModerateYellow
    val SeveritySevere = SeveritySevereOrange
    val SeverityCritical = SeverityCriticalRed
    val White = White
    val Black = Black
    val LightGray = LightGray
    val DarkGray = DarkGray
    val BorderGray = BorderGray
}
