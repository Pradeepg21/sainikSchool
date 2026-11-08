package com.satya.sainikschool.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val Navy = Color(0xFF1B3A5C)
val Olive = Color(0xFF4F6B2F)
val Saffron = Color(0xFFE8862A)
val Success = Color(0xFF2E7D32)
val Danger = Color(0xFFC62828)
val Warning = Color(0xFFF9A825)
val Review = Color(0xFF6A1B9A)

private val Light = lightColorScheme(
    primary = Navy, onPrimary = Color.White,
    primaryContainer = Color(0xFFD5E3F5), onPrimaryContainer = Color(0xFF0B2239),
    secondary = Olive, onSecondary = Color.White,
    secondaryContainer = Color(0xFFDCE8C8), onSecondaryContainer = Color(0xFF1B2A0C),
    tertiary = Saffron, onTertiary = Color.White,
    tertiaryContainer = Color(0xFFFFE0C2), onTertiaryContainer = Color(0xFF3B1E00),
    background = Color(0xFFF6F7F9), surface = Color.White,
    surfaceVariant = Color(0xFFE9EDF2), onSurfaceVariant = Color(0xFF44505C),
)

private val Dark = darkColorScheme(
    primary = Color(0xFF9EC3EC), onPrimary = Color(0xFF0B2239),
    primaryContainer = Color(0xFF243F5E), onPrimaryContainer = Color(0xFFD5E3F5),
    secondary = Color(0xFFB7CF95), onSecondary = Color(0xFF1B2A0C),
    secondaryContainer = Color(0xFF384C22), onSecondaryContainer = Color(0xFFDCE8C8),
    tertiary = Color(0xFFFFB878), onTertiary = Color(0xFF3B1E00),
    tertiaryContainer = Color(0xFF6A3A0A), onTertiaryContainer = Color(0xFFFFE0C2),
    background = Color(0xFF101418), surface = Color(0xFF171C21),
    surfaceVariant = Color(0xFF26303A), onSurfaceVariant = Color(0xFFBFC8D2),
)

private val base = Typography()
private val AppTypography = base.copy(
    headlineSmall = base.headlineSmall.copy(fontWeight = FontWeight.Bold),
    titleLarge = base.titleLarge.copy(fontWeight = FontWeight.SemiBold),
    titleMedium = base.titleMedium.copy(fontWeight = FontWeight.SemiBold),
    bodyLarge = base.bodyLarge.copy(fontSize = 16.sp, lineHeight = 24.sp),
)

@Composable
fun SainikTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = if (isSystemInDarkTheme()) Dark else Light, typography = AppTypography, content = content)
}
