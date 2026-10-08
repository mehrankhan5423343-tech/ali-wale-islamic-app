package com.example.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.data.local.entity.DhikrCountEntity
import com.example.data.local.entity.DuaBookmarkEntity
import com.example.data.local.entity.HabitEntity
import com.example.data.local.entity.JournalEntryEntity
import com.example.data.local.entity.PrayerRecordEntity
import com.example.data.local.entity.QuranBookmarkEntity
import com.example.data.local.entity.UserPreferencesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserPreferencesDao {
    @Query("SELECT * FROM user_preferences WHERE id = 1")
    fun getUserPreferences(): Flow<UserPreferencesEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePreferences(preferences: UserPreferencesEntity)

    @Query("UPDATE user_preferences SET lastReadSurah = :surah, lastReadAyah = :ayah, lastReadSurahName = :name WHERE id = 1")
    suspend fun updateLastRead(surah: Int, ayah: Int, name: String)
}

@Dao
interface PrayerDao {
    @Query("SELECT * FROM prayer_records WHERE date = :date")
    fun getPrayersForDate(date: String): Flow<List<PrayerRecordEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdatePrayer(record: PrayerRecordEntity)

    @Query("SELECT * FROM prayer_records ORDER BY date DESC LIMIT 150")
    fun getRecentPrayerHistory(): Flow<List<PrayerRecordEntity>>
}

@Dao
interface DhikrDao {
    @Query("SELECT * FROM dhikr_records WHERE date = :date")
    fun getDhikrForDate(date: String): Flow<List<DhikrCountEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDhikr(record: DhikrCountEntity)

    @Query("SELECT COALESCE(SUM(count), 0) FROM dhikr_records WHERE date = :date")
    fun getTotalDhikrCountForDate(date: String): Flow<Int>

    @Query("SELECT COALESCE(SUM(count), 0) FROM dhikr_records")
    fun getTotalLifetimeDhikr(): Flow<Int>
}

@Dao
interface HabitDao {
    @Query("SELECT * FROM habits WHERE isArchived = 0 ORDER BY id ASC")
    fun getAllHabits(): Flow<List<HabitEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHabit(habit: HabitEntity): Long

    @Update
    suspend fun updateHabit(habit: HabitEntity)

    @Query("DELETE FROM habits WHERE id = :id")
    suspend fun deleteHabit(id: Long)
}

@Dao
interface JournalDao {
    @Query("SELECT * FROM journal_entries ORDER BY timestamp DESC")
    fun getAllEntries(): Flow<List<JournalEntryEntity>>

    @Query("SELECT * FROM journal_entries WHERE date = :date LIMIT 1")
    suspend fun getEntryForDate(date: String): JournalEntryEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(entry: JournalEntryEntity)

    @Query("DELETE FROM journal_entries WHERE id = :id")
    suspend fun deleteEntry(id: Long)

    @Query("SELECT * FROM journal_entries WHERE gratitude LIKE '%' || :query || '%' OR goodDeed LIKE '%' || :query || '%' OR freeNotes LIKE '%' || :query || '%' ORDER BY timestamp DESC")
    fun searchEntries(query: String): Flow<List<JournalEntryEntity>>
}

@Dao
interface BookmarkDao {
    @Query("SELECT * FROM quran_bookmarks ORDER BY timestamp DESC")
    fun getQuranBookmarks(): Flow<List<QuranBookmarkEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addQuranBookmark(bookmark: QuranBookmarkEntity)

    @Query("DELETE FROM quran_bookmarks WHERE surahNumber = :surah AND ayahNumber = :ayah")
    suspend fun removeQuranBookmark(surah: Int, ayah: Int)

    @Query("SELECT EXISTS(SELECT 1 FROM quran_bookmarks WHERE surahNumber = :surah AND ayahNumber = :ayah)")
    suspend fun isQuranBookmarked(surah: Int, ayah: Int): Boolean

    @Query("SELECT * FROM dua_bookmarks ORDER BY timestamp DESC")
    fun getDuaBookmarks(): Flow<List<DuaBookmarkEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addDuaBookmark(bookmark: DuaBookmarkEntity)

    @Query("DELETE FROM dua_bookmarks WHERE duaId = :duaId")
    suspend fun removeDuaBookmark(duaId: String)

    @Query("SELECT EXISTS(SELECT 1 FROM dua_bookmarks WHERE duaId = :duaId)")
    suspend fun isDuaBookmarked(duaId: String): Boolean
}
