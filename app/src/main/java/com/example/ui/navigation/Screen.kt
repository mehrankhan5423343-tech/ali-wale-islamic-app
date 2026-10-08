package com.example.ui.navigation

sealed class Screen(val route: String, val title: String) {
    object Onboarding : Screen("onboarding", "Welcome")
    object Home : Screen("home", "Home")
    object Quran : Screen("quran", "Quran")
    object Worship : Screen("worship", "Worship")
    object Duas : Screen("duas", "Duas")
    object Profile : Screen("profile", "Profile")
    object QuranReader : Screen("quran_reader", "Quran Reader")
    object Knowledge : Screen("knowledge", "Knowledge")
}
