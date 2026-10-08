package com.example.util

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import kotlin.math.floor

data class HijriDate(
    val day: Int,
    val monthIndex: Int, // 1 to 12
    val monthNameArabic: String,
    val monthNameEnglish: String,
    val year: Int,
    val formattedEnglish: String,
    val formattedArabic: String
)

object HijriCalendarUtil {

    private val hijriMonthsEnglish = listOf(
        "Muharram", "Safar", "Rabi' al-Awwal", "Rabi' al-Thani",
        "Jumada al-Awwal", "Jumada al-Thani", "Rajab", "Sha'ban",
        "Ramadan", "Shawwal", "Dhu al-Qi'dah", "Dhu al-Hijjah"
    )

    private val hijriMonthsArabic = listOf(
        "المحرّم", "صفر", "ربيع الأول", "ربيع الثاني",
        "جمادى الأولى", "جمادى الآخرة", "رجب", "شعبان",
        "رمضان", "شوّال", "ذو القعدة", "ذو الحجة"
    )

    fun getTodayHijri(calendar: Calendar = Calendar.getInstance(), dayAdjustment: Int = 0): HijriDate {
        val y = calendar.get(Calendar.YEAR)
        val m = calendar.get(Calendar.MONTH) + 1
        val d = calendar.get(Calendar.DAY_OF_MONTH)

        // Julian day number from Gregorian date
        var jd = getJulianDay(y, m, d) + dayAdjustment

        // Kuwaiti Algorithm for Hijri conversion
        val l = jd - 1948440 + 10632
        val n = floor((l - 1) / 10631.0).toInt()
        val lRem = l - 10631 * n + 354
        val j = (floor((10985 - lRem) / 5316.0) * floor((50 * lRem) / 17719.0) +
                floor(lRem / 5670.0) * floor((43 * lRem) / 15238.0)).toInt()
        val lFinal = lRem - floor((30 - j) / 15.0).toInt() * floor((17719 * j) / 50.0).toInt() -
                floor(j / 16.0).toInt() * floor((15238 * j) / 43.0).toInt() + 29
        val mHijri = floor((24 * lFinal) / 709.0).toInt()
        val dHijri = lFinal - floor((709 * mHijri) / 24.0).toInt()
        val yHijri = 30 * n + j - 30

        val monthClamped = (mHijri - 1).coerceIn(0, 11)
        val monthEn = hijriMonthsEnglish[monthClamped]
        val monthAr = hijriMonthsArabic[monthClamped]

        val formattedEn = "$dHijri $monthEn $yHijri AH"
        val formattedAr = "$dHijri $monthAr $yHijri هـ"

        return HijriDate(
            day = dHijri,
            monthIndex = monthClamped + 1,
            monthNameArabic = monthAr,
            monthNameEnglish = monthEn,
            year = yHijri,
            formattedEnglish = formattedEn,
            formattedArabic = formattedAr
        )
    }

    fun getTodayGregorianFormatted(calendar: Calendar = Calendar.getInstance()): String {
        val sdf = SimpleDateFormat("EEEE, d MMMM yyyy", Locale.getDefault())
        return sdf.format(calendar.time)
    }

    private fun getJulianDay(year: Int, month: Int, day: Int): Int {
        var y = year
        var m = month
        if (m <= 2) {
            y -= 1
            m += 12
        }
        val a = floor(y / 100.0).toInt()
        val b = 2 - a + floor(a / 4.0).toInt()
        return (floor(365.25 * (y + 4716)) + floor(30.6001 * (m + 1)) + day + b - 1524).toInt()
    }
}
