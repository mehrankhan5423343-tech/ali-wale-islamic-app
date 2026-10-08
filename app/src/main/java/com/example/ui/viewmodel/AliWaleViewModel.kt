package com.example.ui.viewmodel

import android.app.Application
import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.content.DailyContentData
import com.example.data.content.DuaData
import com.example.data.content.PresetDhikr
import com.example.data.content.QuranData
import com.example.data.local.AliWaleDatabase
import com.example.data.local.entity.HabitEntity
import com.example.data.local.entity.JournalEntryEntity
import com.example.data.local.entity.PrayerRecordEntity
import com.example.data.local.entity.UserPreferencesEntity
import com.example.data.model.Ayah
import com.example.data.model.CityLocation
import com.example.data.model.DuaCategory
import com.example.data.model.DuaItem
import com.example.data.model.KnowledgeArticle
import com.example.data.model.PrayerTimeItem
import com.example.data.model.PrayerType
import com.example.data.model.SpiritualScore
import com.example.data.model.Surah
import com.example.data.repository.AliWaleRepository
import com.example.util.HijriCalendarUtil
import com.example.util.HijriDate
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.Calendar

data class TasbihState(
    val activeDhikr: PresetDhikr = DailyContentData.presetDhikrs.first(),
    val count: Int = 0,
    val target: Int = 33,
    val totalToday: Int = 0,
    val lapCompleted: Boolean = false
)

data class QuranReaderState(
    val currentSurah: Surah = QuranData.surahs.first(),
    val ayahs: List<Ayah> = QuranData.getAyahsForSurah(1),
    val fontSize: Float = 22f,
    val showTransliteration: Boolean = true
)

class AliWaleViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = AliWaleRepository(AliWaleDatabase.getDatabase(application))

    val userPreferences: StateFlow<UserPreferencesEntity?> = repository.userPreferences
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    val habits: StateFlow<List<HabitEntity>> = repository.allHabits
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val journalEntries: StateFlow<List<JournalEntryEntity>> = repository.allJournalEntries
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val todayPrayersRaw = repository.getPrayersForDate()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val prayerHistory = repository.getRecentPrayerHistory()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val todayDhikrTotal = repository.getTodayTotalDhikr()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    val quranBookmarks = repository.quranBookmarks
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val duaBookmarks = repository.duaBookmarks
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Active Tasbih State
    private val _tasbihState = MutableStateFlow(TasbihState())
    val tasbihState: StateFlow<TasbihState> = _tasbihState.asStateFlow()

    // Quran Reader State
    private val _quranReaderState = MutableStateFlow(QuranReaderState())
    val quranReaderState: StateFlow<QuranReaderState> = _quranReaderState.asStateFlow()

    // Active Knowledge Article
    private val _activeArticle = MutableStateFlow<KnowledgeArticle?>(null)
    val activeArticle: StateFlow<KnowledgeArticle?> = _activeArticle.asStateFlow()

    // Dua Screen Filter State
    private val _selectedDuaCategory = MutableStateFlow(DuaCategory.MORNING)
    val selectedDuaCategory: StateFlow<DuaCategory> = _selectedDuaCategory.asStateFlow()

    private val _duaSearchQuery = MutableStateFlow("")
    val duaSearchQuery: StateFlow<String> = _duaSearchQuery.asStateFlow()

    // Dates
    val todayHijri: HijriDate = HijriCalendarUtil.getTodayHijri()
    val todayGregorian: String = HijriCalendarUtil.getTodayGregorianFormatted()

    // Daily Rotating Inspiration, Verse, and Hadith
    private val dayOfYear = Calendar.getInstance().get(Calendar.DAY_OF_YEAR)
    val dailyVerse = DailyContentData.dailyVerses[dayOfYear % DailyContentData.dailyVerses.size]
    val dailyHadith = DailyContentData.dailyHadiths[dayOfYear % DailyContentData.dailyHadiths.size]
    val dailyReminder = DailyContentData.dailyInspirations[dayOfYear % DailyContentData.dailyInspirations.size]

    // Combined Prayer Times with Completion
    val todayPrayerTimes: StateFlow<List<PrayerTimeItem>> = combine(
        userPreferences,
        todayPrayersRaw
    ) { prefs, records ->
        val lat = prefs?.latitude ?: 21.4225
        val lon = prefs?.longitude ?: 39.8262
        val method = prefs?.prayerMethod ?: "MWL"
        val asr = prefs?.asrJuristic ?: "STANDARD"

        val calculated = repository.calculateTodayPrayerTimes(lat, lon, method, asr)
        calculated.map { item ->
            val record = records.find { it.prayerName.equals(item.type.name, ignoreCase = true) }
            item.copy(isCompleted = record?.isCompleted == true)
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Spiritual Score State
    val spiritualScore: StateFlow<SpiritualScore> = combine(
        todayPrayersRaw,
        habits,
        todayDhikrTotal,
        journalEntries
    ) { prayers, habitList, dhikrCount, journalList ->
        repository.calculateSpiritualScore(prayers, habitList, dhikrCount, journalList)
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        SpiritualScore(0, "Bismillah", 0, 5, 0, 0, 0, false)
    )

    init {
        viewModelScope.launch {
            repository.initializeDefaultsIfNeeded()
        }
    }

    // --- Onboarding & Preferences ---
    fun completeOnboarding(
        name: String,
        selectedCity: CityLocation,
        prayerMethod: String,
        asrJuristic: String,
        quranGoal: Int,
        dhikrGoal: Int
    ) {
        viewModelScope.launch {
            val current = userPreferences.value ?: UserPreferencesEntity()
            repository.savePreferences(
                current.copy(
                    name = name.ifBlank { "Muslim Seeker" },
                    isOnboarded = true,
                    selectedCity = selectedCity.name,
                    latitude = selectedCity.latitude,
                    longitude = selectedCity.longitude,
                    prayerMethod = prayerMethod,
                    asrJuristic = asrJuristic,
                    quranDailyGoalAyahs = quranGoal,
                    dhikrDailyGoal = dhikrGoal
                )
            )
        }
    }

    fun updatePreferences(transform: (UserPreferencesEntity) -> UserPreferencesEntity) {
        viewModelScope.launch {
            userPreferences.value?.let { current ->
                repository.savePreferences(transform(current))
            }
        }
    }

    fun updateCity(city: CityLocation) {
        updatePreferences {
            it.copy(
                selectedCity = city.name,
                latitude = city.latitude,
                longitude = city.longitude
            )
        }
    }

    fun updatePrayerSettings(method: String, asr: String) {
        updatePreferences {
            it.copy(
                prayerMethod = method,
                asrJuristic = asr
            )
        }
    }

    // --- Prayer Toggle ---
    fun togglePrayer(type: PrayerType) {
        viewModelScope.launch {
            val currentList = todayPrayerTimes.value
            val item = currentList.find { it.type == type }
            val newStatus = !(item?.isCompleted ?: false)
            repository.togglePrayerCompleted(type.name, newStatus)
        }
    }

    // --- Tasbih / Dhikr Actions ---
    fun selectDhikr(preset: PresetDhikr) {
        _tasbihState.value = _tasbihState.value.copy(
            activeDhikr = preset,
            count = 0,
            target = preset.defaultTarget,
            lapCompleted = false
        )
    }

    fun setTasbihTarget(newTarget: Int) {
        _tasbihState.value = _tasbihState.value.copy(target = newTarget)
    }

    fun incrementTasbih() {
        val current = _tasbihState.value
        val newCount = current.count + 1
        val isTarget = newCount >= current.target

        _tasbihState.value = current.copy(
            count = newCount,
            lapCompleted = isTarget
        )

        // Trigger gentle vibration if enabled
        if (userPreferences.value?.enableHaptics != false) {
            vibrateDevice(isLap = isTarget)
        }

        viewModelScope.launch {
            repository.logDhikrSession(
                dhikrKey = current.activeDhikr.key,
                arabicText = current.activeDhikr.arabicText,
                transliteration = current.activeDhikr.transliteration,
                translation = current.activeDhikr.translation,
                count = 1,
                target = current.target
            )
        }
    }

    fun resetTasbih() {
        _tasbihState.value = _tasbihState.value.copy(
            count = 0,
            lapCompleted = false
        )
    }

    private fun vibrateDevice(isLap: Boolean) {
        try {
            val context = getApplication<Application>()
            val duration = if (isLap) 80L else 30L
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                val vibratorManager = context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as? VibratorManager
                vibratorManager?.defaultVibrator?.vibrate(
                    VibrationEffect.createOneShot(duration, if (isLap) VibrationEffect.DEFAULT_AMPLITUDE else 120)
                )
            } else {
                @Suppress("DEPRECATION")
                val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as? Vibrator
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    vibrator?.vibrate(VibrationEffect.createOneShot(duration, VibrationEffect.DEFAULT_AMPLITUDE))
                } else {
                    @Suppress("DEPRECATION")
                    vibrator?.vibrate(duration)
                }
            }
        } catch (e: Exception) {
            // Ignore vibration error on unsupported targets
        }
    }

    // --- Habits Actions ---
    fun toggleHabit(habit: HabitEntity) {
        viewModelScope.launch {
            repository.toggleHabitCompleted(habit)
        }
    }

    fun addCustomHabit(title: String, category: String) {
        viewModelScope.launch {
            repository.addCustomHabit(title, category)
        }
    }

    fun deleteHabit(id: Long) {
        viewModelScope.launch {
            repository.deleteHabit(id)
        }
    }

    // --- Journal / Muhasabah Actions ---
    fun saveJournalEntry(
        mood: String,
        gratitude: String,
        goodDeed: String,
        improvement: String,
        rememberedAllah: Boolean,
        troubledHeart: String,
        freeNotes: String
    ) {
        viewModelScope.launch {
            val entry = JournalEntryEntity(
                date = repository.getTodayDateString(),
                mood = mood,
                gratitude = gratitude,
                goodDeed = goodDeed,
                improvement = improvement,
                rememberedAllah = rememberedAllah,
                troubledHeart = troubledHeart,
                freeNotes = freeNotes
            )
            repository.saveJournalEntry(entry)
        }
    }

    fun deleteJournalEntry(id: Long) {
        viewModelScope.launch {
            repository.deleteJournalEntry(id)
        }
    }

    // --- Quran Reader Actions ---
    fun openSurah(surahNumber: Int) {
        val surah = QuranData.getSurahByNumber(surahNumber) ?: QuranData.surahs.first()
        val ayahs = QuranData.getAyahsForSurah(surahNumber)
        _quranReaderState.value = _quranReaderState.value.copy(
            currentSurah = surah,
            ayahs = ayahs
        )
        viewModelScope.launch {
            repository.updateLastRead(surah.number, 1, surah.nameTransliteration)
        }
    }

    fun setQuranFontSize(size: Float) {
        _quranReaderState.value = _quranReaderState.value.copy(fontSize = size)
        updatePreferences { it.copy(quranFontSize = size) }
    }

    fun toggleQuranTransliteration() {
        val current = _quranReaderState.value.showTransliteration
        _quranReaderState.value = _quranReaderState.value.copy(showTransliteration = !current)
        updatePreferences { it.copy(showTransliteration = !current) }
    }

    fun toggleQuranBookmark(ayahNumber: Int) {
        val surah = _quranReaderState.value.currentSurah
        viewModelScope.launch {
            repository.toggleQuranBookmark(surah.number, ayahNumber, surah.nameTransliteration)
        }
    }

    // --- Duas Actions ---
    fun selectDuaCategory(category: DuaCategory) {
        _selectedDuaCategory.value = category
    }

    fun setDuaSearchQuery(query: String) {
        _duaSearchQuery.value = query
    }

    fun toggleDuaBookmark(duaId: String) {
        viewModelScope.launch {
            repository.toggleDuaBookmark(duaId)
        }
    }

    // --- Knowledge Reader Actions ---
    fun openArticle(article: KnowledgeArticle) {
        _activeArticle.value = article
    }

    fun clearActiveArticle() {
        _activeArticle.value = null
    }

    // --- Privacy & Reset ---
    fun clearUserData() {
        viewModelScope.launch {
            repository.clearAllUserData()
        }
    }
}
