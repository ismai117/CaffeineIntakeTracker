package main.screens.input.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import main.screens.input.data.local.model.IntakeEntity

@Dao
interface IntakeDao {

    @Query("SELECT * FROM intake_table")
    fun selectCaffeineIntake(): Flow<List<IntakeEntity>>

    @Upsert
    suspend fun insertCaffeineIntake(caffeineIntakeEntity: IntakeEntity)

}
