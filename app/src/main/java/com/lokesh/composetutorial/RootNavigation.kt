package com.lokesh.composetutorial

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation

@Composable
fun RootNavigation() {
    val navController = rememberNavController() // Create NavController

    NavHost(
        navController = navController,
        startDestination = "main_graph" // Set Home as default screen
    ) {
        navigation(startDestination = "navigator_screen", route = "main_graph") {
            composable("navigator_screen") { NavigatorScreen(navController) }
        }

        // Child Navigation Graph (Opened when clicking the button)
//        navigation(startDestination = ChildScreen.Details.route, route = "child_graph") {
//            composable(ChildScreen.Details.route) { DetailsScreen(navController) }
//            composable(ChildScreen.MoreDetails.route) { MoreDetailsScreen(navController) }
//        }
    }
}