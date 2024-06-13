package main.screens.input.data.local.service

import main.screens.input.data.local.mapper.mapFromDomainModel
import main.screens.input.data.local.mapper.mapToDomainModelList
import main.screens.input.domain.model.Intake
import database.AppDatabase
import kotlinx.coroutines.flow.Flow

class IntakeLocalService(
    private val db: AppDatabase
){

    fun getTotalCaffeineIntake(): Flow<List<Intake>> {
        return db.getIntakeDao().selectCaffeineIntake().mapToDomainModelList()
    }

    suspend fun insertCaffeineIntake(caffeineIntake: Intake) {
        return db.getIntakeDao().insertCaffeineIntake(caffeineIntake.mapFromDomainModel())
    }


}