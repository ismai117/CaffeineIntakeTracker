package main.screens.input.domain.model

import kotlinx.datetime.LocalDate

data class Intake(
    val id: Int?,
    val name: String,
    val mg: Double,
    val date: String,
    val time: Long
)
