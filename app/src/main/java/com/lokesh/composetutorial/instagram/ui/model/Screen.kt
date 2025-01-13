package com.lokesh.composetutorial.instagram.ui.model

sealed class Screen() {
    object Home : Screen()
    object Search : Screen()
    object Add : Screen()
    object Reels : Screen()
    object Profile : Screen()

}