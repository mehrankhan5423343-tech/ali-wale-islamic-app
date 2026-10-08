package com.example.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.local.dao.BookmarkDao
import com.example.data.local.dao.DhikrDao
import com.example.data.local.dao.HabitDao
import com.example.data.local.dao.JournalDao
import com.example.data.local.dao.PrayerDao
import com.example.data.local.dao.UserPreferencesDao
import com.example.data.local.entity.DhikrCountEntity
import com.example.data.local.entity.DuaBookmarkEntity
import com.example.data.local.entity.HabitEntity
import com.example.data.local.entity.JournalEntryEntity
import com.example.data.local.entity.PrayerRecordEntity
import com.example.data.local.entity.QuranBookmarkEntity
import com.example.data.local.entity.UserPreferencesEntity

@Database(
    entities = [
        UserPreferencesEntity::class,
        PrayerRecordEntity::class,
        DhikrCountEntity::class,
        HabitEntity::class,
        JournalEntryEntity::class,
        QuranBookmarkEntity::class,
        DuaBookmarkEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AliWaleDatabase : RoomDatabase() {
    abstract fun userPreferencesDao(): UserPreferencesDao
    abstract fun prayerDao(): PrayerDao
    abstract fun dhikrDao(): DhikrDao
    abstract fun habitDao(): HabitDao
    abstract fun journalDao(): JournalDao
    abstract fun bookmarkDao(): BookmarkDao

    companion object {
        @Volatile
        private var INSTANCE: AliWaleDatabase? = null

        fun getDatabase(context: Context): AliWaleDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AliWaleDatabase::class.java,
                    "ali_wale_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
