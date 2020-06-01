package xyz.xandsoft.mvvmproject.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Users::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    companion object {

        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room
            .databaseBuilder(
                context.applicationContext,  // Context
                AppDatabase::class.java,    // This Class which extends RoomDatabase
                "MVVMDatabase.db"   // Database Name
            ).build()
    }
}