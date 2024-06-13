package main.screens.input.presentation

import main.screens.input.domain.model.Intake

sealed interface IntakeEvent {
    data class INSERT(val caffeineIntake: Intake) : IntakeEvent
}