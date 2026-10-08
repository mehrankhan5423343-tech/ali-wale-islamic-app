package com.example.ui.screens.duas

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.data.content.DuaData
import com.example.data.model.DuaCategory
import com.example.data.model.DuaItem
import com.example.ui.theme.IslamicGold
import com.example.ui.viewmodel.AliWaleViewModel

@Composable
fun DuasScreen(viewModel: AliWaleViewModel) {
    val selectedCategory by viewModel.selectedDuaCategory.collectAsStateWithLifecycle()
    val searchQuery by viewModel.duaSearchQuery.collectAsStateWithLifecycle()
    val bookmarks by viewModel.duaBookmarks.collectAsStateWithLifecycle()
    val context = LocalContext.current

    val displayedDuas = remember(selectedCategory, searchQuery, bookmarks) {
        if (searchQuery.isNotBlank()) {
            DuaData.allDuas.filter {
                it.title.contains(searchQuery, ignoreCase = true) ||
                it.textTranslation.contains(searchQuery, ignoreCase = true) ||
                it.textTransliteration.contains(searchQuery, ignoreCase = true) ||
                it.textArabic.contains(searchQuery)
            }
        } else {
            DuaData.allDuas.filter { it.category == selectedCategory }
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
        Text(
            text = "Supplications & Adhkar",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = "Authentic Adhkar from the Quran & Sunnah",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(14.dp))

        // Search Bar
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { viewModel.setDuaSearchQuery(it) },
            placeholder = { Text("Search supplications or keywords...") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .testTag("dua_search_input"),
            shape = RoundedCornerShape(16.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Category Chips (Horizontal Scroll)
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(DuaCategory.values()) { category ->
                val isSelected = selectedCategory == category && searchQuery.isBlank()
                FilterChip(
                    selected = isSelected,
                    onClick = {
                        viewModel.setDuaSearchQuery("")
                        viewModel.selectDuaCategory(category)
                    },
                    label = { Text(category.title) }
                )
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Duas List
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(displayedDuas, key = { it.id }) { dua ->
                val isBookmarked = bookmarks.any { it.duaId == dua.id }
                DuaCard(
                    dua = dua,
                    isBookmarked = isBookmarked,
                    onToggleBookmark = { viewModel.toggleDuaBookmark(dua.id) },
                    onShare = {
                        val shareText = "${dua.title}\n\n${dua.textArabic}\n\n${dua.textTranslation}\n\nReference: ${dua.reference}\n[Ali wale app]"
                        val sendIntent = Intent().apply {
                            action = Intent.ACTION_SEND
                            putExtra(Intent.EXTRA_TEXT, shareText)
                            type = "text/plain"
                        }
                        context.startActivity(Intent.createChooser(sendIntent, "Share Dua"))
                    }
                )
            }

            item { Spacer(modifier = Modifier.height(80.dp)) }
        }
    }
}

@Composable
fun DuaCard(
    dua: DuaItem,
    isBookmarked: Boolean,
    onToggleBookmark: () -> Unit,
    onShare: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("dua_${dua.id}"),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f)
                ) {
                    Text(
                        text = dua.category.title,
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
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
                        Icon(Icons.Default.Share, contentDescription = "Share", tint = MaterialTheme.colorScheme.outline)
                    }
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = dua.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Arabic Text
            Text(
                text = dua.textArabic,
                style = MaterialTheme.typography.titleLarge.copy(fontSize = 22.sp),
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.End,
                lineHeight = 36.sp,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.fillMaxWidth()
            )

            // Transliteration
            if (dua.textTransliteration.isNotBlank()) {
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = dua.textTransliteration,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.secondary,
                    fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                    lineHeight = 22.sp
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Translation
            Text(
                text = "\"${dua.textTranslation}\"",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                lineHeight = 22.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Reference & context
            Surface(
                shape = RoundedCornerShape(10.dp),
                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(10.dp)) {
                    Text(
                        text = "📖 Reference: ${dua.reference}",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    if (dua.benefitOrContext.isNotBlank()) {
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = dua.benefitOrContext,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
    }
}
