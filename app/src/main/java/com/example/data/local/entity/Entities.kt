package com.example.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_preferences")
data class UserPreferencesEntity(
    @PrimaryKey val id: Int = 1,
    val name: String = "Muslim Seeker",
    val isOnboarded: Boolean = false,
    val preferredLanguage: String = "English",
    val prayerMethod: String = "MWL", // MWL, ISNA, UMM_AL_QURA, EGYPT, KARACHI
    val asrJuristic: String = "STANDARD", // STANDARD (Shafi'i/Hanbali/Maliki), HANAFI
    val selectedCity: String = "Mecca",
    val latitude: Double = 21.4225,
    val longitude: Double = 39.8262,
    val quranDailyGoalAyahs: Int = 10,
    val dhikrDailyGoal: Int = 100,
    val quranFontSize: Float = 22f,
    val showTransliteration: Boolean = true,
    val enableHaptics: Boolean = true,
    val notifyPrayer: Boolean = true,
    val notifyAdhkar: Boolean = true,
    val notifyQuran: Boolean = true,
    val notifyReflection: Boolean = true,
    val notifyDailyMotivation: Boolean = true,
    val lastReadSurah: Int = 1,
    val lastReadAyah: Int = 1,
    val lastReadSurahName: String = "Al-Fatihah",
    val themeMode: String = "SYSTEM" // SYSTEM, LIGHT, DARK
)

@Entity(tableName = "prayer_records", primaryKeys = ["date", "prayerName"])
data class PrayerRecordEntity(
    val date: String, // YYYY-MM-DD
    val prayerName: String, // FAJR, DHUHR, ASR, MAGHRIB, ISHA
    val isCompleted: Boolean = false,
    val completedAt: Long = 0L,
    val prayedInCongregation: Boolean = false
)

@Entity(tableName = "dhikr_records")
data class DhikrCountEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val dhikrKey: String,
    val arabicText: String,
    val transliteration: String,
    val translation: String,
    val count: Int,
    val target: Int,
    val date: String, // YYYY-MM-DD
    val timestamp: Long = System.currentTimeMillis()
)

@Entity(tableName = "habits")
data class HabitEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val title: String,
    val category: String, // WORSHIP, KNOWLEDGE, CHARACTER, HEALTH
    val streak: Int = 0,
    val lastCompletedDate: String? = null,
    val isArchived: Boolean = false
)

@Entity(tableName = "journal_entries")
data class JournalEntryEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val date: String, // YYYY-MM-DD
    val timestamp: Long = System.currentTimeMillis(),
    val mood: String = "Peaceful", // Peaceful, Grateful, Reflective, Anxious, Hopeful, Struggling
    val gratitude: String = "",
    val goodDeed: String = "",
    val improvement: String = "",
    val rememberedAllah: Boolean = true,
    val troubledHeart: String = "",
    val freeNotes: String = ""
)

@Entity(tableName = "quran_bookmarks", primaryKeys = ["surahNumber", "ayahNumber"])
data class QuranBookmarkEntity(
    val surahNumber: Int,
    val ayahNumber: Int,
    val surahName: String,
    val note: String = "",
    val timestamp: Long = System.currentTimeMillis()
)

@Entity(tableName = "dua_bookmarks")
data class DuaBookmarkEntity(
    @PrimaryKey val duaId: String,
    val timestamp: Long = System.currentTimeMillis()
)
