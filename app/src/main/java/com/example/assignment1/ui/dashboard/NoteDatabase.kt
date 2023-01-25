package com.example.assignment1.ui.dashboard
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase


@Database(
    entities = [Record::class],
//    autoMigrations = [
//        AutoMigration (from = 1, to = 2)
//    ],
    version = 1,
    exportSchema = true
)

abstract class RecordDatabase : RoomDatabase() {

    abstract fun RecordDao(): RecordDao

    companion object {
        @Volatile
        private var INSTANCE: RecordDatabase? = null

        fun getDatabase(context: Context): RecordDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            if (INSTANCE == null) {
                synchronized(this) {
                    // Pass the database to the INSTANCE
                    INSTANCE = buildDatabase(context)
                }
            }
            // Return database.
            return INSTANCE!!
        }

        private val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // The following query will add a new column called lastUpdate to the notes database
                database.execSQL("ALTER TABLE RECORD ADD COLUMN lastUpdate INTEGER NOT NULL DEFAULT 0")
            }
        }

        private fun buildDatabase(context: Context): RecordDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                RecordDatabase::class.java,
                "RECORD_database"
            )
                //.addMigrations(MIGRATION_1_2)
                .build()
        }
    }
}
