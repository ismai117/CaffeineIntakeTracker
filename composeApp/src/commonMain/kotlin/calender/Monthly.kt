package calender

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime


@Composable
fun MonthlyDateLayout(
    currentMonthOnChange: (LocalDate) -> Unit
) {

    val timeZone = TimeZone.currentSystemDefault()
    val today = Clock.System.now().toLocalDateTime(timeZone).date
    var currentMonth by remember { mutableStateOf(today) }

    var monthTitle by remember { mutableStateOf("") }

    LaunchedEffect(currentMonth) {
        currentMonthOnChange(currentMonth)
        monthTitle = "${currentMonth.month}, ${currentMonth.year}"
    }

    CalenderHeaderLayout(
        modifier = Modifier
            .padding(start = 12.dp, end = 12.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        title = {
            Text(
                text = monthTitle,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        },
        onPrevious = {
            currentMonth = currentMonth.minus(1, DateTimeUnit.MONTH)
        },
        onPreviousEnabled = {
            currentMonth.year == today.year && currentMonth.monthNumber > 1
        },
        onNext = {
            currentMonth = currentMonth.plus(1, DateTimeUnit.MONTH)
        },
        onNextEnabled = {
            currentMonth.year == today.year && currentMonth.monthNumber < today.monthNumber
        }
    )

}