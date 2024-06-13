import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.material3.Surface
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.materialkolor.DynamicMaterialTheme
import com.materialkolor.rememberDynamicMaterialThemeState

private val AppShapes = Shapes(
    extraSmall = RoundedCornerShape(2.dp),
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(8.dp),
    large = RoundedCornerShape(16.dp),
    extraLarge = RoundedCornerShape(32.dp)
)

private val AppTypography = Typography(
    bodyMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
    )
)

val LocalThemeIsDark = compositionLocalOf { mutableStateOf(false) }

@Composable
fun AppTheme(
    content: @Composable () -> Unit,
) {
    val systemIsDark = isSystemInDarkTheme()
    val isDarkState = remember { mutableStateOf(systemIsDark) }
    CompositionLocalProvider(
        LocalThemeIsDark provides isDarkState
    ) {
        val isDark by isDarkState
        SystemAppearance(!isDark)
        DynamicMaterialTheme(
            state = rememberDynamicMaterialThemeState(
                seedColor = Color(0xFF689F38),
                isDark
            ),
            animate = false,
            typography = AppTypography,
            shapes = AppShapes,
            content = {
                Surface(content = content)
            }
        )
    }

}

@Composable
expect fun SystemAppearance(isDark: Boolean)