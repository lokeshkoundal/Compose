package com.lokesh.composetutorial.tweetApp.screens

import android.content.Intent
import android.content.IntentFilter
import androidx.compose.runtime.Composable
import androidx.core.content.ContextCompat.registerReceiver
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.lokesh.composetutorial.AirplaneModeReceiver


@Composable
fun App(){

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "category") {

        composable(route = "category") {
           CategoryScreen{ category->
               navController.navigate("details/${category}")
           }
        }

        composable(route = "details/{category}",
            arguments = listOf(
                navArgument("category"){
                    type = NavType.StringType
                }
            )
        ) {
            DetailsScreen()
        }
        
    }
}