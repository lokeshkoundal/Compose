package com.lokesh.composetutorial

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.lokesh.composetutorial.animation.AnimateColorAndShape
import com.lokesh.composetutorial.animation.AnimateVisibility
import com.lokesh.composetutorial.animation.AnimatedContent
import com.lokesh.composetutorial.animation.AnimationScreen
import com.lokesh.composetutorial.calculator.CalculatorScreen
import com.lokesh.composetutorial.tweetApp.screens.CategoryScreen
import com.lokesh.composetutorial.tweetApp.screens.DetailsScreen

@Composable
fun RootNavigation(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Routes.RootGraph.route
    ) {
        navigation(startDestination = "navigator_screen", route = Routes.RootGraph.route) {
            composable("navigator_screen") { NavigatorScreen(navController) }
        }

        navigation(startDestination = "animations_screen", route = Routes.AnimationGraph.route) {
            composable("animations_screen",
                enterTransition = { slideInHorizontally() + fadeIn() },
                exitTransition = { slideOutHorizontally()+ fadeOut() },
                popEnterTransition = { slideInHorizontally() + fadeIn() },
                popExitTransition = { slideOutHorizontally()+ fadeOut() }) {
                AnimationScreen(navController)
            }

            composable("animate_visibility") { AnimateVisibility() }
            composable("AnimateColorAndShape") { AnimateColorAndShape() }
            composable("AnimatedContent") { AnimatedContent() }
        }

        navigation(startDestination = "calculator_screen", route = Routes.CalculatorGraph.route) {
            composable("calculator_screen",
                enterTransition = { slideInHorizontally() + fadeIn() },
                exitTransition = { slideOutHorizontally()+ fadeOut() },
                popEnterTransition = { slideInHorizontally() + fadeIn() },
                popExitTransition = { slideOutHorizontally()+ fadeOut() }) {

                CalculatorScreen()

            }
        }

        navigation(startDestination = "category_screen", route = Routes.TweetsGraph.route) {
            val animateDuration = 300

            composable(route = "category_screen",
                enterTransition = { slideInHorizontally() + fadeIn() },
                exitTransition = { slideOutHorizontally()+ fadeOut() },
                popEnterTransition = { slideInHorizontally() + fadeIn() },
                popExitTransition = { slideOutHorizontally()+ fadeOut() }) {
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
}

sealed class Routes(val route : String){
    data object TweetsGraph : Routes(route = "tweets")
    data object AnimationGraph : Routes(route = "animation")
    data object CalculatorGraph : Routes(route = "calculator")
    data object RootGraph : Routes(route = "root")

}