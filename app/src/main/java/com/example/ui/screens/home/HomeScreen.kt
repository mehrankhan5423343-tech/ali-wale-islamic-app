package com.example.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.FormatQuote
import androidx.compose.material.icons.filled.Mosque
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.material.icons.filled.TouchApp
import androidx.compose.material.icons.filled.VolunteerActivism
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.data.model.PrayerType
import com.example.ui.components.HijriCalendarBanner
import com.example.ui.components.QuickAccessButton
import com.example.ui.components.SectionHeader
import com.example.ui.components.SpiritualScoreGauge
import com.example.ui.theme.IslamicGold
import com.example.ui.theme.QuranVerseBg
import com.example.ui.theme.QuranVerseBgDark
import com.example.ui.viewmodel.AliWaleViewModel

@Composable
fun HomeScreen(
    viewModel: AliWaleViewModel,
    onNavigateToQuran: () -> Unit,
    onNavigateToWorship: (tabIndex: Int) -> Unit,
    onNavigateToDuas: () -> Unit,
    onNavigateToKnowledge: () -> Unit
) {
    val prefs by viewModel.userPreferences.collectAsStateWithLifecycle()
    val spiritualScore by viewModel.spiritualScore.collectAsStateWithLifecycle()
    val prayerTimes by viewModel.todayPrayerTimes.collectAsStateWithLifecycle()
    val habits by viewModel.habits.collectAsStateWithLifecycle()

    val userName = prefs?.name ?: "Traveler of Faith"

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(12.dp))
            // Greeting & Subtitle
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Assalamu Alaikum,",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = userName,
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f),
                    modifier = Modifier.clickable { onNavigateToKnowledge() }
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.AutoAwesome,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Wisdom",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        }

        // Dual Calendar Banner
        item {
            HijriCalendarBanner(
                hijriDateEn = viewModel.todayHijri.formattedEnglish,
                hijriDateAr = viewModel.todayHijri.formattedArabic,
                gregorianDate = viewModel.todayGregorian
            )
        }

        // Daily Spiritual Score Card
        item {
            SpiritualScoreGauge(spiritualScore = spiritualScore)
        }

        // Today's Prayer Bar
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("home_prayer_card"),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Mosque,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Today's Prayers",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Text(
                            text = "${prefs?.selectedCity ?: "Mecca"}",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.clickable { onNavigateToWorship(0) }
                        )
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // 5 Daily Prayers Row
                    val mainPrayers = prayerTimes.filter { it.type != PrayerType.SUNRISE }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        mainPrayers.forEach { prayer ->
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .clip(RoundedCornerShape(12.dp))
                                    .clickable { viewModel.togglePrayer(prayer.type) }
                                    .padding(horizontal = 4.dp, vertical = 6.dp)
                            ) {
                                Text(
                                    text = prayer.type.displayName,
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight.Medium,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = prayer.timeFormatted.substringBefore(" "),
                                    style = MaterialTheme.typography.bodySmall,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.height(6.dp))
                                Icon(
                                    imageVector = if (prayer.isCompleted) Icons.Default.CheckCircle else Icons.Default.RadioButtonUnchecked,
                                    contentDescription = "${prayer.type.displayName} completion",
                                    tint = if (prayer.isCompleted) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline,
                                    modifier = Modifier.size(22.dp)
                                )
                            }
                        }
                    }
                }
            }
        }

        // Quick Access Navigation
        item {
            SectionHeader(title = "Daily Sanctuary", subtitle = "Quick paths to spiritual replenishment")
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                QuickAccessButton(
                    title = "Quran",
                    icon = Icons.Default.Book,
                    containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f),
                    contentColor = MaterialTheme.colorScheme.primary,
                    onClick = onNavigateToQuran,
                    modifier = Modifier.weight(1f)
                )
                QuickAccessButton(
                    title = "Tasbih",
                    icon = Icons.Default.TouchApp,
                    containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.6f),
                    contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                    onClick = { onNavigateToWorship(1) },
                    modifier = Modifier.weight(1f)
                )
                QuickAccessButton(
                    title = "Duas",
                    icon = Icons.Default.VolunteerActivism,
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.5f),
                    contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
                    onClick = onNavigateToDuas,
                    modifier = Modifier.weight(1f)
                )
                QuickAccessButton(
                    title = "Journal",
                    icon = Icons.Default.Edit,
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    onClick = { onNavigateToWorship(3) },
                    modifier = Modifier.weight(1f)
                )
            }
        }

        // Daily Islamic Motivation Card
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Surface(
                            shape = RoundedCornerShape(10.dp),
                            color = MaterialTheme.colorScheme.primaryContainer
                        ) {
                            Text(
                                text = viewModel.dailyReminder.topic,
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                            )
                        }
                        Icon(
                            imageVector = Icons.Default.AutoAwesome,
                            contentDescription = null,
                            tint = IslamicGold,
                            modifier = Modifier.size(18.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = viewModel.dailyReminder.title,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = viewModel.dailyReminder.reflection,
                        style = MaterialTheme.typography.bodyMedium,
                        lineHeight = 22.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "💡 Today's Step: ${viewModel.dailyReminder.actionItem}",
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.Medium,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }
            }
        }

        // Daily Quran Verse Card
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Daily Quran Reflection",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = "${viewModel.dailyVerse.surahName} (${viewModel.dailyVerse.surahNumber}:${viewModel.dailyVerse.ayahNumber})",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // Arabic Text
                    Text(
                        text = viewModel.dailyVerse.textArabic,
                        style = MaterialTheme.typography.titleLarge.copy(fontSize = 22.sp),
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.End,
                        lineHeight = 36.sp,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // Translation
                    Text(
                        text = "\"${viewModel.dailyVerse.textTranslation}\"",
                        style = MaterialTheme.typography.bodyMedium,
                        lineHeight = 22.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        // Daily Hadith Card
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Prophetic Guidance (Hadith)",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.secondary
                        )
                        Icon(
                            imageVector = Icons.Default.FormatQuote,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "\"${viewModel.dailyHadith.textEnglish}\"",
                        style = MaterialTheme.typography.bodyMedium,
                        lineHeight = 22.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "Narrated by ${viewModel.dailyHadith.narrator} • ${viewModel.dailyHadith.reference}",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        // Habits Progress Summary
        item {
            Card(
                onClick = { onNavigateToWorship(2) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Daily Good Habits",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "View All",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    habits.take(3).forEach { habit ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = if (habit.lastCompletedDate == viewModel.todayGregorian.take(10)) Icons.Default.CheckCircle else Icons.Default.RadioButtonUnchecked,
                                contentDescription = null,
                                tint = if (habit.lastCompletedDate == viewModel.todayGregorian.take(10)) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = habit.title,
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier.weight(1f)
                            )
                            if (habit.streak > 0) {
                                Text(
                                    text = "🔥 ${habit.streak}d",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = IslamicGold
                                )
                            }
                        }
                    }
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(80.dp)) // bottom navigation buffer
        }
    }
}
