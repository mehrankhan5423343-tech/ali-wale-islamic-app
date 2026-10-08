package com.example.data.repository

import com.example.data.content.DailyContentData
import com.example.data.local.AliWaleDatabase
import com.example.data.local.entity.DhikrCountEntity
import com.example.data.local.entity.DuaBookmarkEntity
import com.example.data.local.entity.HabitEntity
import com.example.data.local.entity.JournalEntryEntity
import com.example.data.local.entity.PrayerRecordEntity
import com.example.data.local.entity.QuranBookmarkEntity
import com.example.data.local.entity.UserPreferencesEntity
import com.example.data.model.AsrJuristic
import com.example.data.model.CalculationMethod
import com.example.data.model.PrayerTimeItem
import com.example.data.model.PrayerType
import com.example.data.model.SpiritualScore
import com.example.util.PrayerTimeCalculator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class AliWaleRepository(private val database: AliWaleDatabase) {

    private val userPrefsDao = database.userPreferencesDao()
    private val prayerDao = database.prayerDao()
    private val dhikrDao = database.dhikrDao()
    private val habitDao = database.habitDao()
    private val journalDao = database.journalDao()
    private val bookmarkDao = database.bookmarkDao()

    val userPreferences: Flow<UserPreferencesEntity?> = userPrefsDao.getUserPreferences()
    val allHabits: Flow<List<HabitEntity>> = habitDao.getAllHabits()
    val allJournalEntries: Flow<List<JournalEntryEntity>> = journalDao.getAllEntries()
    val quranBookmarks: Flow<List<QuranBookmarkEntity>> = bookmarkDao.getQuranBookmarks()
    val duaBookmarks: Flow<List<DuaBookmarkEntity>> = bookmarkDao.getDuaBookmarks()

    fun getTodayDateString(): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return sdf.format(Date())
    }

    suspend fun initializeDefaultsIfNeeded() {
        val existing = userPrefsDao.getUserPreferences().firstOrNull()
        if (existing == null) {
            userPrefsDao.savePreferences(
                UserPreferencesEntity(
                    id = 1,
                    name = "Traveler of Faith",
                    isOnboarded = false,
                    selectedCity = "Mecca",
                    latitude = 21.4225,
                    longitude = 39.8262
                )
            )
        }

        val habits = habitDao.getAllHabits().first()
        if (habits.isEmpty()) {
            DailyContentData.defaultHabits.forEach { (title, category) ->
                habitDao.insertHabit(
                    HabitEntity(
                        title = title,
                        category = category,
                        streak = 0
                    )
                )
            }
        }
    }

    suspend fun savePreferences(prefs: UserPreferencesEntity) {
        userPrefsDao.savePreferences(prefs)
    }

    suspend fun updateLastRead(surah: Int, ayah: Int, surahName: String) {
        userPrefsDao.updateLastRead(surah, ayah, surahName)
    }

    // --- Prayer Tracking ---
    fun getPrayersForDate(date: String = getTodayDateString()): Flow<List<PrayerRecordEntity>> {
        return prayerDao.getPrayersForDate(date)
    }

    suspend fun togglePrayerCompleted(prayerName: String, isCompleted: Boolean, date: String = getTodayDateString()) {
        prayerDao.insertOrUpdatePrayer(
            PrayerRecordEntity(
                date = date,
                prayerName = prayerName,
                isCompleted = isCompleted,
                completedAt = if (isCompleted) System.currentTimeMillis() else 0L
            )
        )
    }

    fun getRecentPrayerHistory(): Flow<List<PrayerRecordEntity>> = prayerDao.getRecentPrayerHistory()

    fun calculateTodayPrayerTimes(
        latitude: Double,
        longitude: Double,
        methodStr: String,
        asrStr: String
    ): List<PrayerTimeItem> {
        val method = try {
            CalculationMethod.valueOf(methodStr)
        } catch (e: Exception) {
            CalculationMethod.MWL
        }

        val asrJuristic = try {
            AsrJuristic.valueOf(asrStr)
        } catch (e: Exception) {
            AsrJuristic.STANDARD
        }

        return PrayerTimeCalculator.calculateTimes(
            calendar = Calendar.getInstance(),
            latitude = latitude,
            longitude = longitude,
            method = method,
            asrJuristic = asrJuristic
        )
    }

    // --- Dhikr Tracking ---
    fun getTodayDhikr(date: String = getTodayDateString()): Flow<List<DhikrCountEntity>> {
        return dhikrDao.getDhikrForDate(date)
    }

    fun getTodayTotalDhikr(date: String = getTodayDateString()): Flow<Int> {
        return dhikrDao.getTotalDhikrCountForDate(date)
    }

    fun getTotalLifetimeDhikr(): Flow<Int> {
        return dhikrDao.getTotalLifetimeDhikr()
    }

    suspend fun logDhikrSession(
        dhikrKey: String,
        arabicText: String,
        transliteration: String,
        translation: String,
        count: Int,
        target: Int
    ) {
        dhikrDao.insertDhikr(
            DhikrCountEntity(
                dhikrKey = dhikrKey,
                arabicText = arabicText,
                transliteration = transliteration,
                translation = translation,
                count = count,
                target = target,
                date = getTodayDateString(),
                timestamp = System.currentTimeMillis()
            )
        )
    }

    // --- Habits Tracking ---
    suspend fun toggleHabitCompleted(habit: HabitEntity) {
        val today = getTodayDateString()
        val isAlreadyDoneToday = habit.lastCompletedDate == today

        val newStreak = if (isAlreadyDoneToday) {
            (habit.streak - 1).coerceAtLeast(0)
        } else {
            habit.streak + 1
        }

        val updated = habit.copy(
            streak = newStreak,
            lastCompletedDate = if (isAlreadyDoneToday) null else today
        )
        habitDao.updateHabit(updated)
    }

    suspend fun addCustomHabit(title: String, category: String) {
        habitDao.insertHabit(
            HabitEntity(
                title = title.trim(),
                category = category,
                streak = 0
            )
        )
    }

    suspend fun deleteHabit(habitId: Long) {
        habitDao.deleteHabit(habitId)
    }

    // --- Journal / Muhasabah ---
    suspend fun saveJournalEntry(entry: JournalEntryEntity) {
        journalDao.insertOrUpdate(entry)
    }

    suspend fun deleteJournalEntry(id: Long) {
        journalDao.deleteEntry(id)
    }

    fun searchJournal(query: String): Flow<List<JournalEntryEntity>> {
        return journalDao.searchEntries(query)
    }

    // --- Bookmarks ---
    suspend fun toggleQuranBookmark(surahNumber: Int, ayahNumber: Int, surahName: String) {
        val exists = bookmarkDao.isQuranBookmarked(surahNumber, ayahNumber)
        if (exists) {
            bookmarkDao.removeQuranBookmark(surahNumber, ayahNumber)
        } else {
            bookmarkDao.addQuranBookmark(
                QuranBookmarkEntity(
                    surahNumber = surahNumber,
                    ayahNumber = ayahNumber,
                    surahName = surahName
                )
            )
        }
    }

    suspend fun toggleDuaBookmark(duaId: String) {
        val exists = bookmarkDao.isDuaBookmarked(duaId)
        if (exists) {
            bookmarkDao.removeDuaBookmark(duaId)
        } else {
            bookmarkDao.addDuaBookmark(
                DuaBookmarkEntity(
                    duaId = duaId
                )
            )
        }
    }

    suspend fun clearAllUserData() {
        // Safe clear for privacy request
        val habits = habitDao.getAllHabits().first()
        habits.forEach { habitDao.deleteHabit(it.id) }
        val journal = journalDao.getAllEntries().first()
        journal.forEach { journalDao.deleteEntry(it.id) }
        initializeDefaultsIfNeeded()
    }

    // --- Spiritual Score Computation ---
    fun calculateSpiritualScore(
        prayerRecords: List<PrayerRecordEntity>,
        habits: List<HabitEntity>,
        todayDhikrCount: Int,
        journalEntries: List<JournalEntryEntity>
    ): SpiritualScore {
        val today = getTodayDateString()

        // 1. Prayers (5 main prayers): 40 points total (8 points each)
        val dailyPrayerNames = listOf("FAJR", "DHUHR", "ASR", "MAGHRIB", "ISHA")
        val completedPrayersCount = prayerRecords.count { it.isCompleted && dailyPrayerNames.contains(it.prayerName) }
        val prayerScore = (completedPrayersCount * 8).coerceAtMost(40)

        // 2. Habits completed today: 30 points total
        val completedHabitsCount = habits.count { it.lastCompletedDate == today }
        val habitScore = if (habits.isNotEmpty()) {
            ((completedHabitsCount.toDouble() / habits.size.toDouble()) * 30.0).toInt().coerceAtMost(30)
        } else 0

        // 3. Dhikr: 15 points total (scaled up to goal of 100)
        val dhikrScore = ((todayDhikrCount / 100.0) * 15.0).toInt().coerceIn(0, 15)

        // 4. Muhasabah journal reflection today: 15 points
        val hasReflectedToday = journalEntries.any { it.date == today }
        val reflectionScore = if (hasReflectedToday) 15 else 0

        val totalScore = (prayerScore + habitScore + dhikrScore + reflectionScore).coerceIn(0, 100)

        val description = when {
            totalScore >= 85 -> "Tranquil Soul (Nafs Mutma'innah) - Radiant heart"
            totalScore >= 60 -> "Steadfast Devotion - Gaining beautiful momentum"
            totalScore >= 35 -> "Gentle Awakening - Every step toward Allah is blessed"
            else -> "Bismillah - A gentle step forward today brings immense mercy"
        }

        return SpiritualScore(
            score = totalScore,
            levelDescription = description,
            prayersCompleted = completedPrayersCount,
            totalPrayers = 5,
            habitsCompleted = completedHabitsCount,
            totalHabits = habits.size,
            dhikrCompleted = todayDhikrCount,
            hasReflectedToday = hasReflectedToday
        )
    }
}
