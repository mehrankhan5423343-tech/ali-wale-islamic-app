package com.example.ui.screens.quran

import android.content.Intent
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.FormatSize
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.data.model.Ayah
import com.example.ui.theme.IslamicGold
import com.example.ui.viewmodel.AliWaleViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuranReaderScreen(
    viewModel: AliWaleViewModel,
    onBack: () -> Unit
) {
    val readerState by viewModel.quranReaderState.collectAsStateWithLifecycle()
    val bookmarks by viewModel.quranBookmarks.collectAsStateWithLifecycle()
    val context = LocalContext.current

    var showControls by remember { mutableStateOf(false) }

    val surah = readerState.currentSurah

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = surah.nameTransliteration,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "${surah.nameEnglishTranslation} • ${surah.totalAyahs} Ayahs",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { viewModel.toggleQuranTransliteration() }) {
                        Icon(
                            imageVector = Icons.Default.Translate,
                            contentDescription = "Toggle Transliteration",
                            tint = if (readerState.showTransliteration) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline
                        )
                    }
                    IconButton(onClick = { showControls = !showControls }) {
                        Icon(
                            imageVector = Icons.Default.FormatSize,
                            contentDescription = "Font Controls",
                            tint = if (showControls) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.background)
        ) {
            // Optional Font Size Control Card
            if (showControls) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 6.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f))
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("Arabic Font Size", style = MaterialTheme.typography.labelMedium)
                            Text("${readerState.fontSize.toInt()} sp", fontWeight = FontWeight.Bold)
                        }
                        Slider(
                            value = readerState.fontSize,
                            onValueChange = { viewModel.setQuranFontSize(it) },
                            valueRange = 18f..36f,
                            steps = 9
                        )
                    }
                }
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Surah Header Banner
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp),
                        shape = RoundedCornerShape(22.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f)
                        )
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(24.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = surah.nameArabic,
                                style = MaterialTheme.typography.headlineLarge.copy(fontSize = 32.sp),
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = "${surah.nameTransliteration} (${surah.nameEnglishTranslation})",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.SemiBold
                            )
                            Text(
                                text = "Surah ${surah.number} • ${surah.revelationType} • ${surah.totalAyahs} Verses",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )

                            if (surah.number != 9) { // At-Tawbah does not start with Bismillah
                                Spacer(modifier = Modifier.height(18.dp))
                                Text(
                                    text = "بِسْمِ اللَّهِ الرَّحْمَٰنِ الرَّحِيمِ",
                                    style = MaterialTheme.typography.titleLarge.copy(fontSize = 24.sp),
                                    fontWeight = FontWeight.SemiBold,
                                    color = MaterialTheme.colorScheme.primary,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }

                // Ayahs List
                items(readerState.ayahs, key = { "${it.surahNumber}_${it.ayahNumber}" }) { ayah ->
                    val isBookmarked = bookmarks.any { it.surahNumber == ayah.surahNumber && it.ayahNumber == ayah.ayahNumber }
                    AyahCard(
                        ayah = ayah,
                        fontSize = readerState.fontSize,
                        showTransliteration = readerState.showTransliteration,
                        isBookmarked = isBookmarked,
                        onToggleBookmark = { viewModel.toggleQuranBookmark(ayah.ayahNumber) },
                        onShare = {
                            val shareText = "${ayah.textArabic}\n\n${ayah.textTranslation}\n\n— Surah ${surah.nameTransliteration} (${ayah.surahNumber}:${ayah.ayahNumber}) [Ali wale]"
                            val sendIntent = Intent().apply {
                                action = Intent.ACTION_SEND
                                putExtra(Intent.EXTRA_TEXT, shareText)
                                type = "text/plain"
                            }
                            context.startActivity(Intent.createChooser(sendIntent, "Share Verse"))
                        }
                    )
                }

                item { Spacer(modifier = Modifier.height(80.dp)) }
            }
        }
    }
}

@Composable
fun AyahCard(
    ayah: Ayah,
    fontSize: Float,
    showTransliteration: Boolean,
    isBookmarked: Boolean,
    onToggleBookmark: () -> Unit,
    onShare: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("ayah_${ayah.ayahNumber}"),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            // Header Row: Ayah badge and actions
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant
                ) {
                    Text(
                        text = "${ayah.surahNumber}:${ayah.ayahNumber}",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                    )
                }

                Row {
                    IconButton(onClick = onToggleBookmark, modifier = Modifier.size(36.dp)) {
                        Icon(
                            imageVector = if (isBookmarked) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                            contentDescription = "Bookmark",
                            tint = if (isBookmarked) IslamicGold else MaterialTheme.colorScheme.outline
                        )
                    }
                    IconButton(onClick = onShare, modifier = Modifier.size(36.dp)) {
                        Icon(
                            imageVector = Icons.Default.Share,
                            contentDescription = "Share",
                            tint = MaterialTheme.colorScheme.outline
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Arabic Text
            Text(
                text = ayah.textArabic,
                style = MaterialTheme.typography.titleLarge.copy(fontSize = fontSize.sp),
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.End,
                lineHeight = (fontSize * 1.7f).sp,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.fillMaxWidth()
            )

            // Transliteration
            if (showTransliteration && ayah.textTransliteration.isNotBlank()) {
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = ayah.textTransliteration,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.secondary,
                    fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                    lineHeight = 22.sp
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Translation
            Text(
                text = ayah.textTranslation,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                lineHeight = 22.sp
            )
        }
    }
}
