package database

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import org.ncgroup.caffeineintaketracker.AndroidApp

actual fun getAppDatabase(): AppDatabase {
    val dbFile = AndroidApp.INSTANCE.getDatabasePath(AppDatabase.DB_NAME)
    return Room.databaseBuilder<AppDatabase>(
        context =  AndroidApp.INSTANCE,
        name = dbFile.absolutePath
    )
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}