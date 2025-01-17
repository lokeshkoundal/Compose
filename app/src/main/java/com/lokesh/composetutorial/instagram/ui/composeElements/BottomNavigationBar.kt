package com.lokesh.composetutorial.instagram.ui.composeElements

import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.lokesh.composetutorial.instagram.ui.model.Screen


@Composable
fun BottomNavigationBar(
    navController: NavHostController,
    currentRoute: String?,
    onItemSelected: (Screen) -> Unit
) {
    val items = listOf(Screen.Home, Screen.Search, Screen.Add, Screen.Reels, Screen.Profile)


}