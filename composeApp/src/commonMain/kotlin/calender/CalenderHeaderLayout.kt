package calender

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun CalenderHeaderLayout(
    modifier: Modifier = Modifier,
    title: @Composable () -> Unit,
    onPrevious: () -> Unit,
    onPreviousEnabled: () -> Boolean = { true },
    onNext: () -> Unit,
    onNextEnabled: () -> Boolean = { true }
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CalenderNavigationButton(
            modifier = modifier.weight(1f),
            contentAlignment = Alignment.CenterStart,
            onClick = onPrevious,
            enabled = onPreviousEnabled(),
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
            contentDescription = ""
        )
        Box(
            modifier = modifier
                .weight(3f)
                .wrapContentHeight(),
            contentAlignment = Alignment.Center
        ) {
            title()
        }
        CalenderNavigationButton(
            modifier = modifier.weight(1f),
            contentAlignment = Alignment.CenterEnd,
            onClick = onNext,
            enabled = onNextEnabled(),
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = ""
        )
    }
}
