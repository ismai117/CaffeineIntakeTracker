package database

import androidx.room.Database
import androidx.room.RoomDatabase
import main.screens.input.data.local.dao.IntakeDao
import main.screens.input.data.local.model.IntakeEntity


@Database(
    entities = [
        IntakeEntity::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase(), DB {

    abstract fun getIntakeDao(): IntakeDao

    override fun clearAllTables() {
        super.clearAllTables()
    }

    companion object {
        const val DB_NAME = "caffeineIntake.db"
    }
}

expect fun getAppDatabase(): AppDatabase

// FIXME: Added a hack to resolve below issue:
// Class 'AppDatabase_Impl' is not abstract and does not implement abstract base class member 'clearAllTables'.
interface DB {
    fun clearAllTables(): Unit {}
}