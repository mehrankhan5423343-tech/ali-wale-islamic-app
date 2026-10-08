package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Mosque
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.VolunteerActivism
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.material.icons.outlined.Mosque
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.VolunteerActivism
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.ui.screens.duas.DuasScreen
import com.example.ui.screens.home.HomeScreen
import com.example.ui.screens.knowledge.KnowledgeScreen
import com.example.ui.screens.onboarding.OnboardingScreen
import com.example.ui.screens.quran.QuranReaderScreen
import com.example.ui.screens.quran.QuranScreen
import com.example.ui.screens.profile.ProfileScreen
import com.example.ui.screens.worship.WorshipScreen
import com.example.ui.theme.AliWaleTheme
import com.example.ui.viewmodel.AliWaleViewModel

enum class NavTab(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val testTag: String
) {
    HOME("Home", Icons.Filled.Home, Icons.Outlined.Home, "tab_home"),
    QURAN("Quran", Icons.Filled.MenuBook, Icons.Outlined.MenuBook, "tab_quran"),
    WORSHIP("Worship", Icons.Filled.Mosque, Icons.Outlined.Mosque, "tab_worship"),
    DUAS("Duas", Icons.Filled.VolunteerActivism, Icons.Outlined.VolunteerActivism, "tab_duas"),
    PROFILE("Profile", Icons.Filled.Person, Icons.Outlined.Person, "tab_profile")
}

sealed class SubScreen {
    object None : SubScreen()
    object QuranReader : SubScreen()
    object Knowledge : SubScreen()
}

class MainActivity : ComponentActivity() {

    private val viewModel: AliWaleViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            AliWaleTheme {
                val userPrefs by viewModel.userPreferences.collectAsStateWithLifecycle()

                // State
                var currentTab by remember { mutableStateOf(NavTab.HOME) }
                var worshipInitialTab by remember { mutableIntStateOf(0) }
                var activeSubScreen by remember { mutableStateOf<SubScreen>(SubScreen.None) }

                when {
                    userPrefs == null -> {
                        // Loading initial database state
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                        }
                    }
                    userPrefs?.isOnboarded != true -> {
                        // First-launch Onboarding Experience
                        OnboardingScreen(
                            onFinished = { name, city, method, asr, quranGoal, dhikrGoal ->
                                viewModel.completeOnboarding(name, city, method, asr, quranGoal, dhikrGoal)
                            }
                        )
                    }
                    else -> {
                        // Main Sanctuary App
                        Scaffold(
                            contentWindowInsets = WindowInsets.safeDrawing,
                            bottomBar = {
                                if (activeSubScreen == SubScreen.None) {
                                    NavigationBar {
                                        NavTab.values().forEach { tab ->
                                            val isSelected = currentTab == tab
                                            NavigationBarItem(
                                                selected = isSelected,
                                                onClick = {
                                                    if (tab == NavTab.WORSHIP) {
                                                        worshipInitialTab = 0
                                                    }
                                                    currentTab = tab
                                                },
                                                icon = {
                                                    Icon(
                                                        imageVector = if (isSelected) tab.selectedIcon else tab.unselectedIcon,
                                                        contentDescription = tab.title
                                                    )
                                                },
                                                label = { Text(tab.title) },
                                                modifier = Modifier.testTag(tab.testTag)
                                            )
                                        }
                                    }
                                }
                            }
                        ) { innerPadding ->
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(innerPadding)
                            ) {
                                when (activeSubScreen) {
                                    is SubScreen.QuranReader -> {
                                        BackHandler { activeSubScreen = SubScreen.None }
                                        QuranReaderScreen(
                                            viewModel = viewModel,
                                            onBack = { activeSubScreen = SubScreen.None }
                                        )
                                    }
                                    is SubScreen.Knowledge -> {
                                        BackHandler { activeSubScreen = SubScreen.None }
                                        KnowledgeScreen(
                                            viewModel = viewModel,
                                            onBack = { activeSubScreen = SubScreen.None }
                                        )
                                    }
                                    is SubScreen.None -> {
                                        when (currentTab) {
                                            NavTab.HOME -> HomeScreen(
                                                viewModel = viewModel,
                                                onNavigateToQuran = { currentTab = NavTab.QURAN },
                                                onNavigateToWorship = { tabIndex ->
                                                    worshipInitialTab = tabIndex
                                                    currentTab = NavTab.WORSHIP
                                                },
                                                onNavigateToDuas = { currentTab = NavTab.DUAS },
                                                onNavigateToKnowledge = { activeSubScreen = SubScreen.Knowledge }
                                            )
                                            NavTab.QURAN -> QuranScreen(
                                                viewModel = viewModel,
                                                onOpenSurah = { surahNumber ->
                                                    viewModel.openSurah(surahNumber)
                                                    activeSubScreen = SubScreen.QuranReader
                                                }
                                            )
                                            NavTab.WORSHIP -> WorshipScreen(
                                                viewModel = viewModel,
                                                initialTab = worshipInitialTab
                                            )
                                            NavTab.DUAS -> DuasScreen(
                                                viewModel = viewModel
                                            )
                                            NavTab.PROFILE -> ProfileScreen(
                                                viewModel = viewModel,
                                                onNavigateToKnowledge = { activeSubScreen = SubScreen.Knowledge }
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
