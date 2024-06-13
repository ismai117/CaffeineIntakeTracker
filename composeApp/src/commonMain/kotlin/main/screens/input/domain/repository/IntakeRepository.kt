package main.screens.input.domain.repository

import main.screens.input.domain.model.Intake
import kotlinx.coroutines.flow.Flow

interface IntakeRepository {
    fun getTotalCaffeineIntake(): Flow<List<Intake>>
    suspend fun insertCaffeineIntake(caffeineIntake: Intake)
}