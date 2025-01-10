package com.lokesh.composetutorial.tweetApp.screens

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument


@Composable
fun App(){

    val animateDuration = 300

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "category") {

        composable(route = "category",
            enterTransition = { fadeIn(tween(animateDuration)) },
            exitTransition = { fadeOut(tween(animateDuration)) },
            popEnterTransition = { fadeIn(tween(animateDuration)) },
            popExitTransition = { fadeOut(tween(animateDuration)) }) {
           CategoryScreen{ category->
               navController.navigate("details/${category}")
           }
        }

        composable(route = "details/{category}",
            arguments = listOf(
                navArgument("category"){
                    type = NavType.StringType
                }
            ),
            enterTransition = { fadeIn(tween(animateDuration)) },
            exitTransition = { fadeOut(tween(animateDuration)) },
            popEnterTransition = { fadeIn(tween(animateDuration)) },
            popExitTransition = { fadeOut(tween(animateDuration)) }
        ) {
            DetailsScreen()
        }
        
    }
}