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
import androidx.compose.ui.text.substring
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
fun YearlyChart(
    intakes: List<Intake>,
    currentYear: LocalDate?,
) {

    if (currentYear != null) {

        val today = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
        val months = if(currentYear.year == today.year){
            (1..currentYear.monthNumber).map {
                LocalDate(year = currentYear.year, monthNumber = it, 1)
            }
        } else {
            (1..12).map {
                LocalDate(year = currentYear.year, monthNumber = it, 1)
            }
        }

        val mg = months.map { month ->
            intakes.filter {
                val monthString = if (month.monthNumber < 10) "0${month.monthNumber}" else month.monthNumber.toString()
                val mn =  it.date.split("/").get(1)
                val yr = it.date.split("/").get(2)
                mn == monthString && yr == month.year.toString()
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
                xAxisData = months.map { it.month.name.substring(0..2) },
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