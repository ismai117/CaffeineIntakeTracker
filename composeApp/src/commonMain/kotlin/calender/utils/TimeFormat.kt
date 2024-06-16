package calender.utils

import kotlinx.datetime.Clock

fun currentTime(): Long {
    return Clock.System.now().toEpochMilliseconds()
}