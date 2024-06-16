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
import calender.utils.formatDate
import com.aay.compose.barChart.BarChart
import com.aay.compose.barChart.model.BarParameters
import kotlinx.datetime.LocalDate
import main.screens.input.domain.model.Intake

@Composable
fun WeeklyChart(
    intakes: List<Intake>,
    currentWeek: List<LocalDate>,
) {


    if (currentWeek.isNotEmpty()) {

        val mg = currentWeek.map { date ->
            intakes.filter { intake ->
                intake.date == date.formatDate()
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
                xAxisData = currentWeek.map { "${it.dayOfMonth}" },
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

/**
 * val spaceBetweenBars = 10.dp
 * val spaceBetweenGroups = 40.dp
 */