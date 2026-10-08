package com.example.util

import com.example.data.model.AsrJuristic
import com.example.data.model.CalculationMethod
import com.example.data.model.PrayerTimeItem
import com.example.data.model.PrayerType
import java.util.Calendar
import java.util.TimeZone
import kotlin.math.abs
import kotlin.math.acos
import kotlin.math.asin
import kotlin.math.atan
import kotlin.math.cos
import kotlin.math.floor
import kotlin.math.sin
import kotlin.math.tan

/**
 * Standard astronomical prayer time calculator based on solar declination and hour angles.
 */
object PrayerTimeCalculator {

    fun calculateTimes(
        calendar: Calendar,
        latitude: Double,
        longitude: Double,
        method: CalculationMethod,
        asrJuristic: AsrJuristic
    ): List<PrayerTimeItem> {
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH) + 1
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        // Julian Date
        val julianDate = getJulianDate(year, month, day) - (longitude / (15.0 * 24.0))

        val d = julianDate - 2451545.0
        val g = fixAngle(357.529 + 0.98560028 * d)
        val q = fixAngle(280.459 + 0.98564736 * d)
        val l = fixAngle(q + 1.915 * sin(Math.toRadians(g)) + 0.020 * sin(Math.toRadians(2 * g)))

        val e = 23.439 - 0.00000036 * d
        val ra = fixAngle(Math.toDegrees(atan(cos(Math.toRadians(e)) * tan(Math.toRadians(l))))) / 15.0

        val dhuhrTransit = 12.0 + (longitude / 15.0) - ra // solar transit in UTC approximation
        val solarDeclination = Math.toDegrees(asin(sin(Math.toRadians(e)) * sin(Math.toRadians(l))))

        // TimeZone offset in hours
        val tzOffset = calendar.timeZone.getOffset(calendar.timeInMillis).toDouble() / (1000.0 * 3600.0)

        // Midday (Dhuhr)
        val equationOfTime = (q / 15.0) - ra
        val noonUtc = 12.0 - equationOfTime - (longitude / 15.0)
        val noonLocal = noonUtc + tzOffset

        // Sun altitude angles
        val fajrAngle = method.fajrAngle
        val ishaAngle = method.ishaAngle

        val fajrHour = noonLocal - getSunHourAngle(-fajrAngle, latitude, solarDeclination)
        val sunriseHour = noonLocal - getSunHourAngle(-0.8333, latitude, solarDeclination)
        val sunsetHour = noonLocal + getSunHourAngle(-0.8333, latitude, solarDeclination)

        // Asr calculation
        val shadowFactor = asrJuristic.shadowFactor
        val asrAngle = Math.toDegrees(
            atan(1.0 / (shadowFactor + tan(Math.toRadians(abs(latitude - solarDeclination))))))
        val asrHour = noonLocal + getSunHourAngle(asrAngle, latitude, solarDeclination)

        val maghribHour = sunsetHour + (2.0 / 60.0) // 2 minutes buffer after sunset
        val ishaHour = if (method == CalculationMethod.MAKKAH) {
            maghribHour + 1.5 // 90 min after Maghrib
        } else {
            noonLocal + getSunHourAngle(-ishaAngle, latitude, solarDeclination)
        }

        fun toTimeItem(type: PrayerType, fractionalHours: Double): PrayerTimeItem {
            val clamped = fixHour(fractionalHours)
            val h = clamped.toInt()
            val m = ((clamped - h) * 60.0).toInt().coerceIn(0, 59)

            val prayerCal = calendar.clone() as Calendar
            prayerCal.set(Calendar.HOUR_OF_DAY, h)
            prayerCal.set(Calendar.MINUTE, m)
            prayerCal.set(Calendar.SECOND, 0)
            prayerCal.set(Calendar.MILLISECOND, 0)

            val amPm = if (h < 12) "AM" else "PM"
            val displayHour = if (h % 12 == 0) 12 else h % 12
            val formatted = String.format("%02d:%02d %s", displayHour, m, amPm)

            return PrayerTimeItem(
                type = type,
                timeFormatted = formatted,
                timestampMillis = prayerCal.timeInMillis
            )
        }

        return listOf(
            toTimeItem(PrayerType.FAJR, fajrHour),
            toTimeItem(PrayerType.SUNRISE, sunriseHour),
            toTimeItem(PrayerType.DHUHR, noonLocal + (1.0 / 60.0)),
            toTimeItem(PrayerType.ASR, asrHour),
            toTimeItem(PrayerType.MAGHRIB, maghribHour),
            toTimeItem(PrayerType.ISHA, ishaHour)
        )
    }

    private fun getSunHourAngle(alpha: Double, latitude: Double, declination: Double): Double {
        val latRad = Math.toRadians(latitude)
        val decRad = Math.toRadians(declination)
        val alphaRad = Math.toRadians(alpha)

        val cosH = (sin(alphaRad) - sin(latRad) * sin(decRad)) / (cos(latRad) * cos(decRad))
        val clamped = cosH.coerceIn(-1.0, 1.0)
        return Math.toDegrees(acos(clamped)) / 15.0
    }

    private fun getJulianDate(year: Int, month: Int, day: Int): Double {
        var y = year
        var m = month
        if (m <= 2) {
            y -= 1
            m += 12
        }
        val a = floor(y / 100.0)
        val b = 2 - a + floor(a / 4.0)
        return floor(365.25 * (y + 4716)) + floor(30.6001 * (m + 1)) + day + b - 1524.5
    }

    private fun fixAngle(a: Double): Double {
        var angle = a - 360.0 * floor(a / 360.0)
        if (angle < 0) angle += 360.0
        return angle
    }

    private fun fixHour(h: Double): Double {
        var hour = h - 24.0 * floor(h / 24.0)
        if (hour < 0) hour += 24.0
        return hour
    }
}
