package com.example.ui.screens.profile

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Mosque
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import com.example.data.content.DailyContentData
import com.example.data.model.AsrJuristic
import com.example.data.model.CalculationMethod
import com.example.ui.theme.IslamicGold
import com.example.ui.viewmodel.AliWaleViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    viewModel: AliWaleViewModel,
    onNavigateToKnowledge: () -> Unit
) {
    val prefs by viewModel.userPreferences.collectAsStateWithLifecycle()

    var showEditNameDialog by remember { mutableStateOf(false) }
    var editedName by remember { mutableStateOf(prefs?.name ?: "") }
    var showDeleteConfirmDialog by remember { mutableStateOf(false) }
    var cityDropdownExpanded by remember { mutableStateOf(false) }
    var methodDropdownExpanded by remember { mutableStateOf(false) }

    if (showEditNameDialog) {
        AlertDialog(
            onDismissRequest = { showEditNameDialog = false },
            title = { Text("Update Name") },
            text = {
                OutlinedTextField(
                    value = editedName,
                    onValueChange = { editedName = it },
                    label = { Text("Name or Kunya") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        viewModel.updatePreferences { it.copy(name = editedName.ifBlank { "Muslim Seeker" }) }
                        showEditNameDialog = false
                    }
                ) { Text("Save") }
            },
            dismissButton = {
                TextButton(onClick = { showEditNameDialog = false }) { Text("Cancel") }
            }
        )
    }

    if (showDeleteConfirmDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteConfirmDialog = false },
            title = { Text("Reset & Clear All Local Data?") },
            text = {
                Text("This will delete all saved habit progress, private Muhasabah reflections, and custom dhikr sessions stored on this device. This cannot be undone.")
            },
            confirmButton = {
                Button(
                    onClick = {
                        viewModel.clearUserData()
                        showDeleteConfirmDialog = false
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                ) { Text("Clear All Data") }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteConfirmDialog = false }) { Text("Cancel") }
            }
        )
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Profile & Settings",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = "Personalize your sanctuary and calculation preferences",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        // Profile Identity Card
        item {
            Card(
                onClick = {
                    editedName = prefs?.name ?: ""
                    showEditNameDialog = true
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("profile_identity_card"),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(50.dp)
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.primaryContainer),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(28.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(14.dp))

                        Column {
                            Text(
                                text = prefs?.name ?: "Traveler of Faith",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "City: ${prefs?.selectedCity ?: "Mecca"} • Tap to rename",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }

                    Text(
                        text = "Edit",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        // Prayer Calculation Settings
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Text(
                        text = "Prayer Calculation Settings",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    // City Selection
                    ExposedDropdownMenuBox(
                        expanded = cityDropdownExpanded,
                        onExpandedChange = { cityDropdownExpanded = !cityDropdownExpanded },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        OutlinedTextField(
                            value = "${prefs?.selectedCity ?: "Mecca"}",
                            onValueChange = {},
                            readOnly = true,
                            label = { Text("Location") },
                            leadingIcon = { Icon(Icons.Default.LocationOn, contentDescription = null) },
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = cityDropdownExpanded) },
                            modifier = Modifier
                                .menuAnchor()
                                .fillMaxWidth()
                        )
                        ExposedDropdownMenu(
                            expanded = cityDropdownExpanded,
                            onDismissRequest = { cityDropdownExpanded = false }
                        ) {
                            DailyContentData.cities.forEach { city ->
                                DropdownMenuItem(
                                    text = { Text("${city.name}, ${city.country}") },
                                    onClick = {
                                        viewModel.updateCity(city)
                                        cityDropdownExpanded = false
                                    }
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Method Selection
                    ExposedDropdownMenuBox(
                        expanded = methodDropdownExpanded,
                        onExpandedChange = { methodDropdownExpanded = !methodDropdownExpanded },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        OutlinedTextField(
                            value = "${prefs?.prayerMethod ?: "MWL"}",
                            onValueChange = {},
                            readOnly = true,
                            label = { Text("Fajr & Isha Angle Method") },
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = methodDropdownExpanded) },
                            modifier = Modifier
                                .menuAnchor()
                                .fillMaxWidth()
                        )
                        ExposedDropdownMenu(
                            expanded = methodDropdownExpanded,
                            onDismissRequest = { methodDropdownExpanded = false }
                        ) {
                            CalculationMethod.values().forEach { method ->
                                DropdownMenuItem(
                                    text = { Text(method.title) },
                                    onClick = {
                                        viewModel.updatePreferences { it.copy(prayerMethod = method.name) }
                                        methodDropdownExpanded = false
                                    }
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Asr Method
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Asr Shadow: ${if (prefs?.asrJuristic == "HANAFI") "Hanafi (2x)" else "Standard (1x)"}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        TextButton(
                            onClick = {
                                val newAsr = if (prefs?.asrJuristic == "HANAFI") "STANDARD" else "HANAFI"
                                viewModel.updatePreferences { it.copy(asrJuristic = newAsr) }
                            }
                        ) {
                            Text("Switch Method")
                        }
                    }
                }
            }
        }

        // Notification Preferences
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(modifier = Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = "Notification Preferences",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )

                    NotificationToggleRow(
                        title = "Salah Reminders",
                        checked = prefs?.notifyPrayer != false,
                        onCheckedChange = { checked -> viewModel.updatePreferences { it.copy(notifyPrayer = checked) } }
                    )
                    NotificationToggleRow(
                        title = "Morning & Evening Adhkar",
                        checked = prefs?.notifyAdhkar != false,
                        onCheckedChange = { checked -> viewModel.updatePreferences { it.copy(notifyAdhkar = checked) } }
                    )
                    NotificationToggleRow(
                        title = "Quran Reading Reminder",
                        checked = prefs?.notifyQuran != false,
                        onCheckedChange = { checked -> viewModel.updatePreferences { it.copy(notifyQuran = checked) } }
                    )
                    NotificationToggleRow(
                        title = "Daily Muhasabah Reflection",
                        checked = prefs?.notifyReflection != false,
                        onCheckedChange = { checked -> viewModel.updatePreferences { it.copy(notifyReflection = checked) } }
                    )
                    NotificationToggleRow(
                        title = "Daily Islamic Motivation",
                        checked = prefs?.notifyDailyMotivation != false,
                        onCheckedChange = { checked -> viewModel.updatePreferences { it.copy(notifyDailyMotivation = checked) } }
                    )
                }
            }
        }

        // Privacy & Local Storage
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Security, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Privacy & Offline Architecture",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Ali wale stores all your personal reflections, prayer marks, and habit records exclusively on your device. We do not sell, track, or share personal spiritual data.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        lineHeight = 18.sp
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    Button(
                        onClick = { showDeleteConfirmDialog = true },
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.errorContainer, contentColor = MaterialTheme.colorScheme.onErrorContainer),
                        modifier = Modifier.fillMaxWidth().testTag("clear_data_btn")
                    ) {
                        Icon(Icons.Default.DeleteForever, contentDescription = null, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Clear & Reset Local Data")
                    }
                }
            }
        }

        // Scholarly Disclaimer
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
            ) {
                Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.Top) {
                    Icon(Icons.Default.Info, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(20.dp))
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "Scholarly Disclaimer: Ali wale provides reliable Quranic texts and authentic Hadith references for personal inspiration. It is not a substitute for formal Islamic scholarship or binding legal rulings (fatwa). For complex fiqh questions, consult qualified local scholars.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        lineHeight = 18.sp
                    )
                }
            }
        }

        item { Spacer(modifier = Modifier.height(80.dp)) }
    }
}

@Composable
fun NotificationToggleRow(
    title: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = title, style = MaterialTheme.typography.bodyMedium)
        Switch(checked = checked, onCheckedChange = onCheckedChange)
    }
}
