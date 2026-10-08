package com.example

import com.example.data.content.DuaData
import com.example.data.content.KnowledgeData
import com.example.data.content.QuranData
import com.example.data.local.entity.HabitEntity
import com.example.data.local.entity.JournalEntryEntity
import com.example.data.local.entity.PrayerRecordEntity
import com.example.data.model.AsrJuristic
import com.example.data.model.CalculationMethod
import com.example.data.model.DuaCategory
import com.example.data.model.KnowledgeCategory
import com.example.data.model.PrayerType
import com.example.data.repository.AliWaleRepository
import com.example.util.HijriCalendarUtil
import com.example.util.PrayerTimeCalculator
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import java.util.Calendar

class ExampleUnitTest {

    @Test
    fun testQuranDataCompleteness() {
        assertEquals("Must contain 114 Surahs", 114, QuranData.surahs.size)
        assertEquals("Must contain 30 Juz", 30, QuranData.juzList.size)

        val alFatihah = QuranData.getSurahByNumber(1)
        assertNotNull(alFatihah)
        assertEquals("Al-Fatihah", alFatihah?.nameTransliteration)

        val ayahs = QuranData.getAyahsForSurah(1)
        assertEquals(7, ayahs.size)
    }

    @Test
    fun testDuaCategoriesCompleteness() {
        // Must contain all 14 categories
        assertEquals(14, DuaCategory.values().size)
        DuaCategory.values().forEach { category ->
            val duasInCat = DuaData.getDuasByCategory(category)
            assertTrue("Category ${category.name} should have duas", duasInCat.isNotEmpty())
        }
    }

    @Test
    fun testKnowledgeCategoriesCompleteness() {
        assertEquals(11, KnowledgeCategory.values().size)
        KnowledgeCategory.values().forEach { category ->
            val articlesInCat = KnowledgeData.getArticlesByCategory(category)
            assertTrue("Category ${category.name} should have articles", articlesInCat.isNotEmpty())
        }
    }

    @Test
    fun testPrayerCalculation() {
        val calendar = Calendar.getInstance()
        val times = PrayerTimeCalculator.calculateTimes(
            calendar = calendar,
            latitude = 21.4225, // Mecca
            longitude = 39.8262,
            method = CalculationMethod.MAKKAH,
            asrJuristic = AsrJuristic.STANDARD
        )

        assertEquals(6, times.size)
        assertTrue(times.any { it.type == PrayerType.FAJR })
        assertTrue(times.any { it.type == PrayerType.SUNRISE })
        assertTrue(times.any { it.type == PrayerType.DHUHR })
        assertTrue(times.any { it.type == PrayerType.ASR })
        assertTrue(times.any { it.type == PrayerType.MAGHRIB })
        assertTrue(times.any { it.type == PrayerType.ISHA })
    }

    @Test
    fun testHijriDateConversion() {
        val hijri = HijriCalendarUtil.getTodayHijri()
        assertTrue("Hijri day between 1 and 30", hijri.day in 1..30)
        assertTrue("Hijri month between 1 and 12", hijri.monthIndex in 1..12)
        assertTrue("Hijri year should be > 1440", hijri.year > 1440)
        assertTrue("Formatted English has AH", hijri.formattedEnglish.contains("AH"))
    }
}
