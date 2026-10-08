package com.example.ui.screens.quran

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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.data.content.QuranData
import com.example.data.model.JuzInfo
import com.example.data.model.Surah
import com.example.ui.theme.IslamicGold
import com.example.ui.viewmodel.AliWaleViewModel

@Composable
fun QuranScreen(
    viewModel: AliWaleViewModel,
    onOpenSurah: (surahNumber: Int) -> Unit
) {
    val prefs by viewModel.userPreferences.collectAsStateWithLifecycle()
    val bookmarks by viewModel.quranBookmarks.collectAsStateWithLifecycle()

    var selectedTabIndex by remember { mutableIntStateOf(0) }
    var searchQuery by remember { mutableStateOf("") }

    val lastSurah = prefs?.lastReadSurah ?: 1
    val lastAyah = prefs?.lastReadAyah ?: 1
    val lastSurahName = prefs?.lastReadSurahName ?: "Al-Fatihah"

    val filteredSurahs = remember(searchQuery) {
        if (searchQuery.isBlank()) QuranData.surahs
        else QuranData.surahs.filter {
            it.nameTransliteration.contains(searchQuery, ignoreCase = true) ||
            it.nameEnglishTranslation.contains(searchQuery, ignoreCase = true) ||
            it.number.toString() == searchQuery.trim() ||
            it.nameArabic.contains(searchQuery)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 20.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        // Title
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "The Noble Quran",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "القرآن الكريم • Guidance for Mankind",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Icon(
                imageVector = Icons.Default.MenuBook,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(28.dp)
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Last Read Banner
        Card(
            onClick = { onOpenSurah(lastSurah) },
            modifier = Modifier
                .fillMaxWidth()
                .testTag("resume_reading_card"),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f)
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "Last Read Position",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "$lastSurahName • Ayah $lastAyah",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = "Resume",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Search Bar
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            placeholder = { Text("Search Surah by name, meaning or number...") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .testTag("quran_search_field"),
            shape = RoundedCornerShape(16.dp)
        )

        Spacer(modifier = Modifier.height(14.dp))

        // Tabs: Surahs, Juz, Bookmarks
        TabRow(
            selectedTabIndex = selectedTabIndex,
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.primary
        ) {
            Tab(
                selected = selectedTabIndex == 0,
                onClick = { selectedTabIndex = 0 },
                text = { Text("Surahs (${filteredSurahs.size})", fontWeight = FontWeight.SemiBold) }
            )
            Tab(
                selected = selectedTabIndex == 1,
                onClick = { selectedTabIndex = 1 },
                text = { Text("Juz (30)", fontWeight = FontWeight.SemiBold) }
            )
            Tab(
                selected = selectedTabIndex == 2,
                onClick = { selectedTabIndex = 2 },
                text = { Text("Saved (${bookmarks.size})", fontWeight = FontWeight.SemiBold) }
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Content
        when (selectedTabIndex) {
            0 -> {
                // Surah List
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(filteredSurahs, key = { it.number }) { surah ->
                        SurahRowItem(surah = surah, onClick = { onOpenSurah(surah.number) })
                    }
                    item { Spacer(modifier = Modifier.height(80.dp)) }
                }
            }
            1 -> {
                // Juz List
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(QuranData.juzList, key = { it.juzNumber }) { juz ->
                        JuzRowItem(juz = juz, onClick = { onOpenSurah(juz.startSurahNumber) })
                    }
                    item { Spacer(modifier = Modifier.height(80.dp)) }
                }
            }
            2 -> {
                // Bookmarks
                if (bookmarks.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 40.dp),
                        contentAlignment = Alignment.TopCenter
                    ) {
                        Text(
                            text = "No saved bookmarks yet.\nTap the bookmark icon in the Quran reader to save verses.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center
                        )
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(bookmarks, key = { "${it.surahNumber}_${it.ayahNumber}" }) { bookmark ->
                            Card(
                                onClick = { onOpenSurah(bookmark.surahNumber) },
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(16.dp),
                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Column {
                                        Text(
                                            text = "${bookmark.surahName} : Ayah ${bookmark.ayahNumber}",
                                            style = MaterialTheme.typography.titleMedium,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Text(
                                            text = "Surah #${bookmark.surahNumber}",
                                            style = MaterialTheme.typography.bodySmall,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                    Icon(
                                        imageVector = Icons.Default.Bookmark,
                                        contentDescription = null,
                                        tint = IslamicGold
                                    )
                                }
                            }
                        }
                        item { Spacer(modifier = Modifier.height(80.dp)) }
                    }
                }
            }
        }
    }
}

@Composable
fun SurahRowItem(
    surah: Surah,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .testTag("surah_${surah.number}"),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                // Number badge
                Box(
                    modifier = Modifier
                        .size(38.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.surfaceVariant),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "${surah.number}",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                Spacer(modifier = Modifier.width(14.dp))

                Column {
                    Text(
                        text = surah.nameTransliteration,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "${surah.nameEnglishTranslation} • ${surah.totalAyahs} Ayahs • ${surah.revelationType}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            // Arabic Name
            Text(
                text = surah.nameArabic,
                style = MaterialTheme.typography.titleLarge.copy(fontSize = 20.sp),
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
fun JuzRowItem(
    juz: JuzInfo,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(38.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.5f)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "${juz.juzNumber}",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }

                Spacer(modifier = Modifier.width(14.dp))

                Column {
                    Text(
                        text = "Juz ${juz.juzNumber}",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Starts at ${juz.startSurahName} (${juz.startSurahNumber}:${juz.startAyahNumber})",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Text(
                text = juz.nameArabic,
                style = MaterialTheme.typography.titleLarge.copy(fontSize = 18.sp),
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.secondary
            )
        }
    }
}
