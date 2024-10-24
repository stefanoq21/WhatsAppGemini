package com.stefanoq21.whatsappgemini.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = WhatsAppColor,
    onPrimary = Color.White,
    primaryContainer = PrimaryContainer,
    onPrimaryContainer = OnPrimaryContainer,
    secondary = Color.White,
    onSecondary = Color.Black,
    secondaryContainer = SecondaryContainer,
    onSecondaryContainer = Color.Black,
    tertiary = SecondaryContainer,
    onTertiary = Color.Black,
    background = Color.White,
    onBackground = Color.Black,
    surface = Color.White,
    onSurface = Color.Black,
    onSurfaceVariant = OnSurfaceVariant,
    surfaceTint = OnSurfaceVariant,
    surfaceContainer = SurfaceContainer

)

@Immutable
data class ColorFamily(
    val backgroundVariant: Color,
)

@Immutable
data class ExtendedColorScheme(
    val extra: ColorFamily = extendedLight.extra,
)

val extendedLight = ExtendedColorScheme(
    extra = ColorFamily(
        BackgroundVariantLight,
    ),
)

val extendedDark = ExtendedColorScheme(
    extra = ColorFamily(
        BackgroundVariantLight,
    ),
)


val LocalExColorScheme = staticCompositionLocalOf { ExtendedColorScheme() }


@Composable
fun WhatsAppGeminiTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val extendedColorScheme = when {
        darkTheme -> extendedDark
        else -> extendedLight
    }

    CompositionLocalProvider(
        LocalExColorScheme provides extendedColorScheme
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}