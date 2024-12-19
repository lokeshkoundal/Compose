package com.lokesh.composetutorial.tweetApp.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument


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