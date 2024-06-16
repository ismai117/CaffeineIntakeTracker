package charts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aay.compose.barChart.BarChart
import com.aay.compose.barChart.model.BarParameters
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.until
import main.screens.input.domain.model.Intake

@Composable
fun MonthlyChart(
    intakes: List<Intake>,
    currentMonth: LocalDate?,
) {

    if (currentMonth != null) {

        val today = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
        val start = LocalDate(currentMonth.year, currentMonth.month, 1)
        val end = start.plus(1, DateTimeUnit.MONTH)

        val days = if(today.monthNumber != currentMonth.monthNumber){
            (1..start.until(end, DateTimeUnit.DAY)).map { it }
        } else {
            (1..start.until(end, DateTimeUnit.DAY)).map { it }.filter { it <= currentMonth.dayOfMonth }
        }

        val mg = days.map { day ->
            intakes.filter {
                it.date ==  if (day < 10) "0${day}/0${currentMonth.monthNumber}/${currentMonth.year}" else if (currentMonth.monthNumber < 10) "$day/0${currentMonth.monthNumber}/${currentMonth.year}" else "$day/${currentMonth.monthNumber}/${currentMonth.year}"
            }.sumOf { it.mg }
        }

        val testBarParameters: List<BarParameters> = listOf(
            BarParameters(
                dataName = "Intake",
                data = mg,
                barColor = MaterialTheme.colorScheme.primaryContainer
            )
        )

        Box(Modifier.padding(16.dp).fillMaxSize()) {
            BarChart(
                descriptionStyle = TextStyle(
                    color = MaterialTheme.colorScheme.primary
                ),
                chartParameters = testBarParameters,
                gridColor = Color.DarkGray,
                xAxisData = days.map { "$it" },
                isShowGrid = true,
                animateChart = true,
                showGridWithSpacer = true,
                yAxisStyle = TextStyle(
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W400,
                    textAlign = TextAlign.Center
                ),
                xAxisStyle = TextStyle(
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W400,
                    textAlign = TextAlign.Center
                ),
                yAxisRange = 10,
                barWidth = 20.dp,
                spaceBetweenGroups = 16.dp,
                horizontalArrangement = Arrangement.Center
            )
        }

    }

}