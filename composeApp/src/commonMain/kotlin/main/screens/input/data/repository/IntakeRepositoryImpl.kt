package main.screens.input.data.repository

import main.screens.input.data.local.service.IntakeLocalService
import main.screens.input.domain.model.Intake
import main.screens.input.domain.repository.IntakeRepository
import kotlinx.coroutines.flow.Flow

class IntakeRepositoryImpl(
    private val local: IntakeLocalService
) : IntakeRepository {

    override fun getTotalCaffeineIntake(): Flow<List<Intake>> {
        return local.getTotalCaffeineIntake()
    }

    override suspend fun insertCaffeineIntake(caffeineIntake: Intake) {
        local.insertCaffeineIntake(caffeineIntake)
    }

}