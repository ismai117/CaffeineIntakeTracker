package calender.utils

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun currentDate(): String {
    val currentDateTime = Clock.System.now()
        .toLocalDateTime(TimeZone.currentSystemDefault())
    val day = if (currentDateTime.dayOfMonth < 10)  "0${currentDateTime.dayOfMonth}" else "${currentDateTime.dayOfMonth}"
    val month = if (currentDateTime.monthNumber < 10)  "0${currentDateTime.monthNumber}" else "${currentDateTime.monthNumber}"
    return "$day/$month/${currentDateTime.year}"
}

fun LocalDate.formatDate(): String {
    val day = if (this.dayOfMonth < 10)  "0${this.dayOfMonth}" else "${this.dayOfMonth}"
    val month = if (this.monthNumber < 10)  "0${this.monthNumber}" else "${this.monthNumber}"
    return "$day/$month/${this.year}"
}