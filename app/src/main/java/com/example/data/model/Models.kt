package com.example.data.model

enum class PrayerType(val displayName: String, val arabicName: String) {
    FAJR("Fajr", "الفجر"),
    SUNRISE("Sunrise", "الشروق"),
    DHUHR("Dhuhr", "الظهر"),
    ASR("Asr", "العصر"),
    MAGHRIB("Maghrib", "المغرب"),
    ISHA("Isha", "العشاء")
}

data class PrayerTimeItem(
    val type: PrayerType,
    val timeFormatted: String, // e.g. "05:12 AM"
    val timestampMillis: Long,
    val isCompleted: Boolean = false
)

data class CityLocation(
    val name: String,
    val country: String,
    val latitude: Double,
    val longitude: Double,
    val timezoneOffsetHours: Double
)

enum class CalculationMethod(val title: String, val fajrAngle: Double, val ishaAngle: Double) {
    MWL("Muslim World League", 18.0, 17.0),
    ISNA("Islamic Society of North America (ISNA)", 15.0, 15.0),
    EGYPT("Egyptian General Authority of Survey", 19.5, 17.5),
    MAKKAH("Umm al-Qura University, Makkah", 18.5, 19.0),
    KARACHI("University of Islamic Sciences, Karachi", 18.0, 18.0),
    TEHRAN("Institute of Geophysics, University of Tehran", 17.7, 14.0)
}

enum class AsrJuristic(val title: String, val shadowFactor: Double) {
    STANDARD("Shafi'i, Maliki, Hanbali (1x Shadow)", 1.0),
    HANAFI("Hanafi (2x Shadow)", 2.0)
}

data class Surah(
    val number: Int,
    val nameArabic: String,
    val nameTransliteration: String,
    val nameEnglishTranslation: String,
    val revelationType: String, // Meccan or Medinan
    val totalAyahs: Int,
    val juzNumber: Int
)

data class Ayah(
    val surahNumber: Int,
    val ayahNumber: Int,
    val textArabic: String,
    val textTransliteration: String,
    val textTranslation: String
)

data class JuzInfo(
    val juzNumber: Int,
    val nameArabic: String,
    val startSurahNumber: Int,
    val startAyahNumber: Int,
    val startSurahName: String
)

enum class DuaCategory(val id: String, val title: String, val arabicTitle: String) {
    MORNING("morning", "Morning Duas", "أذكار الصباح"),
    EVENING("evening", "Evening Duas", "أذكار المساء"),
    BEFORE_SLEEP("sleep", "Before Sleeping", "أذكار النوم"),
    AFTER_WAKING("waking", "After Waking", "الاستيقاظ من النوم"),
    ANXIETY("anxiety", "Anxiety & Difficulty", "الهم والحزن والكرب"),
    PROTECTION("protection", "Protection & Refuge", "الحفظ والتحصين"),
    FORGIVENESS("forgiveness", "Forgiveness & Tawbah", "طلب المغفرة والتوبة"),
    GRATITUDE("gratitude", "Gratitude & Praise", "شكر النعم والثناء"),
    TRAVEL("travel", "Travel & Journey", "دعاء السفر"),
    PARENTS("parents", "Parents & Family", "الدعاء للوالدين والأهل"),
    KNOWLEDGE("knowledge", "Knowledge & Wisdom", "طلب العلم والحكمة"),
    RIZQ("rizq", "Rizq & Provision", "طلب الرزق والبركة"),
    HEALTH("health", "Health & Healing", "الشفاء والعافية"),
    GENERAL("general", "General Daily Duas", "أدعية عامة وشاملة")
}

data class DuaItem(
    val id: String,
    val category: DuaCategory,
    val title: String,
    val textArabic: String,
    val textTransliteration: String,
    val textTranslation: String,
    val reference: String,
    val benefitOrContext: String = ""
)

enum class KnowledgeCategory(val id: String, val title: String, val arabicTitle: String) {
    AQEEDAH("aqeedah", "Aqeedah (Faith)", "العقيدة"),
    SEERAH("seerah", "Seerah (Prophetic Life)", "السيرة النبوية"),
    PROPHETS("prophets", "Stories of the Prophets", "قصص الأنبياء"),
    SAHABAH("sahabah", "Noble Companions", "الصحابة الكرام"),
    HISTORY("history", "Islamic History", "التاريخ الإسلامي"),
    AKHLAQ("akhlaq", "Akhlaq (Manners & Character)", "الأخلاق والآداب"),
    SALAH("salah", "Pillars of Salah", "فقه الصلاة وخشوعها"),
    FASTING("fasting", "Spiritual Fasting (Sawm)", "الصيام وفضائله"),
    ZAKAT("zakat", "Zakat & Generosity", "الزكاة والصدقة"),
    HAJJ("hajj", "Hajj & Umrah", "الحج والعمرة"),
    DAILY_MANNERS("manners", "Daily Islamic Etiquette", "آداب المسلم اليومية")
}

data class KnowledgeArticle(
    val id: String,
    val category: KnowledgeCategory,
    val title: String,
    val arabicSubtitle: String,
    val readTimeMinutes: Int,
    val summary: String,
    val keyLessons: List<String>,
    val bodyParagraphs: List<String>,
    val references: List<String>
)

data class DailyIslamicReminder(
    val id: String,
    val topic: String, // Tawakkul, Sabr, Shukr, Tawbah, Hope, Mercy, Good Character, etc.
    val title: String,
    val reflection: String,
    val actionItem: String,
    val quoteOrAyah: String,
    val reference: String
)

data class SpiritualScore(
    val score: Int, // 0 - 100
    val levelDescription: String,
    val prayersCompleted: Int,
    val totalPrayers: Int,
    val habitsCompleted: Int,
    val totalHabits: Int,
    val dhikrCompleted: Int,
    val hasReflectedToday: Boolean
)
