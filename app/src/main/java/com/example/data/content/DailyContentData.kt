package com.example.data.content

import com.example.data.model.CityLocation
import com.example.data.model.DailyIslamicReminder

data class PresetDhikr(
    val key: String,
    val arabicText: String,
    val transliteration: String,
    val translation: String,
    val defaultTarget: Int = 33,
    val virtues: String = ""
)

data class DailyVerse(
    val surahName: String,
    val surahNumber: Int,
    val ayahNumber: Int,
    val textArabic: String,
    val textTranslation: String,
    val theme: String
)

data class DailyHadith(
    val textEnglish: String,
    val narrator: String,
    val reference: String,
    val theme: String
)

object DailyContentData {

    val cities: List<CityLocation> = listOf(
        CityLocation("Mecca", "Saudi Arabia", 21.4225, 39.8262, 3.0),
        CityLocation("Medina", "Saudi Arabia", 24.5247, 39.5692, 3.0),
        CityLocation("Jerusalem", "Palestine", 31.7683, 35.2137, 2.0),
        CityLocation("Cairo", "Egypt", 30.0444, 31.2357, 2.0),
        CityLocation("Istanbul", "Turkey", 41.0082, 28.9784, 3.0),
        CityLocation("London", "United Kingdom", 51.5074, -0.1278, 0.0),
        CityLocation("New York", "United States", 40.7128, -74.0060, -5.0),
        CityLocation("Toronto", "Canada", 43.6532, -79.3832, -5.0),
        CityLocation("Dubai", "UAE", 25.2048, 55.2708, 4.0),
        CityLocation("Karachi", "Pakistan", 24.8607, 67.0011, 5.0),
        CityLocation("Jakarta", "Indonesia", -6.2088, 106.8456, 7.0),
        CityLocation("Kuala Lumpur", "Malaysia", 3.1390, 101.6869, 8.0),
        CityLocation("Paris", "France", 48.8566, 2.3522, 1.0),
        CityLocation("Sydney", "Australia", -33.8688, 151.2093, 10.0),
        CityLocation("Tokyo", "Japan", 35.6762, 139.6503, 9.0)
    )

    val presetDhikrs: List<PresetDhikr> = listOf(
        PresetDhikr(
            key = "subhanallah",
            arabicText = "سُبْحَانَ اللَّهِ",
            transliteration = "SubhanAllah",
            translation = "Glory be to Allah",
            defaultTarget = 33,
            virtues = "Plants a tree for you in Jannah and purifies the soul of heedlessness."
        ),
        PresetDhikr(
            key = "alhamdulillah",
            arabicText = "الْحَمْدُ لِلَّهِ",
            transliteration = "Alhamdulillah",
            translation = "All praise is due to Allah",
            defaultTarget = 33,
            virtues = "Fills the heavenly scales (Mizan) with light and endless barakah."
        ),
        PresetDhikr(
            key = "allahu_akbar",
            arabicText = "اللَّهُ أَكْبَرُ",
            transliteration = "Allahu Akbar",
            translation = "Allah is the Greatest",
            defaultTarget = 34,
            virtues = "Affirms that Allah is greater than every worldly fear, grief, and obstacle."
        ),
        PresetDhikr(
            key = "astaghfirullah",
            arabicText = "أَسْتَغْفِرُ اللَّهَ",
            transliteration = "Astaghfirullah",
            translation = "I seek forgiveness from Allah",
            defaultTarget = 100,
            virtues = "Opens doors of ease, unlocks sustenance (rizq), and brings peace of heart."
        ),
        PresetDhikr(
            key = "la_ilaha_illallah",
            arabicText = "لَا إِلٰهَ إِلَّا اللهُ",
            transliteration = "La ilaha illallah",
            translation = "There is no deity worthy of worship except Allah",
            defaultTarget = 100,
            virtues = "The highest branch of faith and the key to everlasting peace."
        ),
        PresetDhikr(
            key = "subhanallahi_wa_bihamdihi",
            arabicText = "سُبْحَانَ اللَّهِ وَبِحَمْدِهِ",
            transliteration = "SubhanAllahi wa bihamdihi",
            translation = "Glory and praise be to Allah",
            defaultTarget = 100,
            virtues = "Whoever says this 100 times, their minor sins are forgiven even if like the foam of the sea."
        ),
        PresetDhikr(
            key = "la_hawla",
            arabicText = "لَا حَوْلَ وَلَا قُوَّةَ إِلَّا بِاللَّهِ",
            transliteration = "La hawla wa la quwwata illa billah",
            translation = "There is no might nor power except through Allah",
            defaultTarget = 33,
            virtues = "A treasure from beneath the Throne of the Most Merciful."
        ),
        PresetDhikr(
            key = "salawat",
            arabicText = "اللَّهُمَّ صَلِّ عَلَىٰ مُحَمَّدٍ",
            transliteration = "Allahumma salli 'ala Muhammad",
            translation = "O Allah, send blessings upon Muhammad",
            defaultTarget = 100,
            virtues = "Whoever sends blessings upon the Prophet ﷺ once, Allah sends blessings upon them tenfold."
        )
    )

    val dailyVerses: List<DailyVerse> = listOf(
        DailyVerse(
            surahName = "Ash-Sharh",
            surahNumber = 94,
            ayahNumber = 5,
            textArabic = "فَإِنَّ مَعَ الْعُسْرِ يُسْرًا ۝ إِنَّ مَعَ الْعُسْرِ يُسْرًا",
            textTranslation = "For indeed, with hardship will be ease. Indeed, with hardship will be ease.",
            theme = "Ease & Hope"
        ),
        DailyVerse(
            surahName = "Al-Baqarah",
            surahNumber = 2,
            ayahNumber = 186,
            textArabic = "وَإِذَا سَأَلَكَ عِبَادِي عَنِّي فَإِنِّي قَرِيبٌ ۖ أُجِيبُ دَعْوَةَ الدَّاعِ إِذَا دَعَانِ",
            textTranslation = "And when My servants ask you concerning Me, indeed I am near. I respond to the invocation of the supplicant when he calls upon Me.",
            theme = "Proximity & Du'a"
        ),
        DailyVerse(
            surahName = "At-Talaq",
            surahNumber = 65,
            ayahNumber = 3,
            textArabic = "وَمَن يَتَوَكَّلْ عَلَى اللَّهِ فَهُوَ حَسْبُهُ",
            textTranslation = "And whoever relies upon Allah - then He is sufficient for him. Indeed, Allah will accomplish His purpose.",
            theme = "Tawakkul (Trust)"
        ),
        DailyVerse(
            surahName = "Ar-Ra'd",
            surahNumber = 13,
            ayahNumber = 28,
            textArabic = "أَلَا بِذِكْرِ اللَّهِ تَطْمَئِنُّ الْقُلُوبُ",
            textTranslation = "Unquestionably, by the remembrance of Allah hearts are assured.",
            theme = "Peace of Heart"
        ),
        DailyVerse(
            surahName = "Az-Zumar",
            surahNumber = 39,
            ayahNumber = 53,
            textArabic = "قُلْ يَا عِبَادِيَ الَّذِينَ أَسْرَفُوا عَلَىٰ أَنفُسِهِمْ لَا تَقْنَطُوا مِن رَّحْمَةِ اللَّهِ ۚ إِنَّ اللَّهَ يَغْفِرُ الذُّنُوبَ جَمِيعًا",
            textTranslation = "Say, 'O My servants who have transgressed against themselves, do not despair of the mercy of Allah. Indeed, Allah forgives all sins.'",
            theme = "Boundless Mercy"
        )
    )

    val dailyHadiths: List<DailyHadith> = listOf(
        DailyHadith(
            textEnglish = "How wonderful is the affair of the believer, for his affairs are all good, and this applies to no one except the believer. If something good happens to him, he is thankful for it and that is good for him; and if something bad happens to him, he bears it with patience and that is good for him.",
            narrator = "Suhaib (RA)",
            reference = "Sahih Muslim (No. 2999)",
            theme = "Patience & Gratitude"
        ),
        DailyHadith(
            textEnglish = "Make things easy and do not make them difficult, cheer people up and do not drive them away.",
            narrator = "Anas ibn Malik (RA)",
            reference = "Sahih al-Bukhari (No. 69)",
            theme = "Gentleness & Hope"
        ),
        DailyHadith(
            textEnglish = "The most beloved deeds to Allah are those that are most consistent, even if they are small.",
            narrator = "Aisha (RA)",
            reference = "Sahih al-Bukhari (No. 6464)",
            theme = "Consistency"
        ),
        DailyHadith(
            textEnglish = "The merciful will be shown mercy by the Most Merciful. Be merciful to those on the earth, and the One in the heavens will have mercy upon you.",
            narrator = "Abdullah ibn Amr (RA)",
            reference = "Jami' at-Tirmidhi (No. 1924)",
            theme = "Mercy & Compassion"
        ),
        DailyHadith(
            textEnglish = "Allah does not look at your outward appearances or your wealth, but He looks at your hearts and your deeds.",
            narrator = "Abu Hurairah (RA)",
            reference = "Sahih Muslim (No. 2564)",
            theme = "Sincerity (Ikhlas)"
        )
    )

    val dailyInspirations: List<DailyIslamicReminder> = listOf(
        DailyIslamicReminder(
            id = "insp_1",
            topic = "Tawakkul (Reliance)",
            title = "Let Go and Trust the Divine Plan",
            reflection = "Whatever is meant for you will never miss you, and whatever misses you was never meant for you. Breathe deeply, fulfill your responsibility, and entrust the outcome to Allah who loves you more than a mother loves her child.",
            actionItem = "Take a moment today to release one worry from your heart and say sincerely: 'Hasbiyallahu wa ni'mal wakeel'.",
            quoteOrAyah = "And put your trust in Allah; and sufficient is Allah as a Disposer of affairs. (Quran 33:3)",
            reference = "Surah Al-Ahzab (33:3)"
        ),
        DailyIslamicReminder(
            id = "insp_2",
            topic = "Sabr (Beautiful Patience)",
            title = "Patience Is Not Resignation, It Is Inner Strength",
            reflection = "Sabr does not mean waiting passively in grief. It is carrying yourself with dignified grace while planting seeds of goodness during a season of silence. Allah is preparing you for what you asked for.",
            actionItem = "When an inconvenience arises today, pause for three breaths before reacting and say 'Alhamdulillah 'ala kulli hal'.",
            quoteOrAyah = "Indeed, the patient will be given their reward without account. (Quran 39:10)",
            reference = "Surah Az-Zumar (39:10)"
        ),
        DailyIslamicReminder(
            id = "insp_3",
            topic = "Shukr (Gratitude)",
            title = "Gratitude Multiplies Every Blessing",
            reflection = "The air in your lungs, the safety of your home, and the heartbeat in your chest are gifts given without cost. When we count our blessings instead of our burdens, contentment floods the spirit.",
            actionItem = "Name three subtle blessings today that you usually take for granted, and whisper a heartfelt prayer of thanks.",
            quoteOrAyah = "If you are grateful, I will surely increase you [in favor]. (Quran 14:7)",
            reference = "Surah Ibrahim (14:7)"
        ),
        DailyIslamicReminder(
            id = "insp_4",
            topic = "Tawbah & Hope",
            title = "Every Sunset Brings a Clean Slate",
            reflection = "Never allow guilt to convince you that you are too far from Allah. The door of repentance is wider than the heavens and the earth. Sincere regret transforms yesterday's shortcomings into today's wisdom.",
            actionItem = "Offer two quiet units of prayer (Salah at-Tawbah) tonight and know that Allah rejoices over your return.",
            quoteOrAyah = "Indeed, Allah loves those who are constantly repentant and loves those who purify themselves. (Quran 2:222)",
            reference = "Surah Al-Baqarah (2:222)"
        )
    )

    val defaultHabits = listOf(
        Pair("5 Daily Prayers on Time", "WORSHIP"),
        Pair("Daily Quran Reading", "WORSHIP"),
        Pair("Morning & Evening Adhkar", "WORSHIP"),
        Pair("Daily Tasbih (100 Istighfar)", "WORSHIP"),
        Pair("Evening Muhasabah Reflection", "CHARACTER"),
        Pair("Giving Charity or a Helpful Act", "CHARACTER"),
        Pair("Kindness to Parents & Family", "CHARACTER"),
        Pair("Restraining Anger & Speaking Gently", "CHARACTER")
    )
}
