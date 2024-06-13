package main.screens.input.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "intake_table")
data class IntakeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val name: String,
    val mg: Double
)
