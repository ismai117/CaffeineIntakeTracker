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
fun YearlyDateLayout(
    currentYearOnChange: (LocalDate) -> Unit
){

    val timezone = TimeZone.currentSystemDefault()
    val today = Clock.System.now().toLocalDateTime(timezone).date
    var currentYear by remember { mutableStateOf(today) }

    var yearTitle by remember { mutableStateOf("") }

    LaunchedEffect(currentYear){
        currentYearOnChange(currentYear)
        yearTitle = "${currentYear.year}"
    }

    CalenderHeaderLayout(
        modifier = Modifier
            .padding(start = 12.dp, end = 12.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        title = {
            Text(
                text = yearTitle,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        },
        onPrevious = {
             currentYear = currentYear.minus(1, DateTimeUnit.YEAR)
        },
        onNext = {
            currentYear = currentYear.plus(1, DateTimeUnit.YEAR)
        },
        onNextEnabled = {
            currentYear.year < today.year
        }
    )

}