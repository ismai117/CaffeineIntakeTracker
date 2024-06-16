package calender

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.isoDayNumber
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime


@Composable
fun WeeklyDateLayout(
    currentWeekOnChange: (List<LocalDate>) -> Unit
){

    val timeZone = TimeZone.currentSystemDefault()
    val today = Clock.System.now().toLocalDateTime(timeZone).date
    val todayWeekDay = today.dayOfWeek.isoDayNumber
    val weekDays = (1..7).map { today.plus(it - todayWeekDay, DateTimeUnit.DAY) }

    var currentWeek by remember { mutableStateOf(weekDays) }

    var weeklyDateText by remember { mutableStateOf("") }

    LaunchedEffect(currentWeek) {
        currentWeekOnChange(currentWeek)
        weeklyDateText = "${currentWeek.first().month.name} ${currentWeek.first().dayOfMonth} - ${currentWeek.last().month.name} ${currentWeek.last().dayOfMonth}, ${today.year}"
    }

    CalenderHeaderLayout(
        modifier = Modifier
            .padding(start = 12.dp, end = 12.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        title = {
            Text(
                text = weeklyDateText,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        },
        onPrevious = {
            val previousWeekStart = currentWeek.first().minus(1, DateTimeUnit.WEEK)
            val previousWeekDays = (1..7).map {
                previousWeekStart.plus(
                    it - previousWeekStart.dayOfWeek.isoDayNumber,
                    DateTimeUnit.DAY
                )
            }
            currentWeek = previousWeekDays
        },
        onNext = {
            val nextWeekStart = currentWeek.last().plus(1, DateTimeUnit.WEEK)
            val nextWeekDays = (1..7).map {
                nextWeekStart.plus(
                    it - nextWeekStart.dayOfWeek.isoDayNumber,
                    DateTimeUnit.DAY
                )
            }
            currentWeek = nextWeekDays
        },
        onNextEnabled  = { currentWeek != weekDays }
    )

}

