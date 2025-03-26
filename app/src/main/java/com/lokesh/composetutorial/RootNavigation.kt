package com.lokesh.composetutorial

import android.content.Intent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import androidx.navigation.navigation
import com.lokesh.composetutorial.calculator.CalculatorScreen
import com.lokesh.composetutorial.extra.DeeplinkScreen
import com.lokesh.composetutorial.extra.ExtraScreen
import com.lokesh.composetutorial.extra.animation.AnimateColorAndShape
import com.lokesh.composetutorial.extra.animation.AnimateVisibility
import com.lokesh.composetutorial.extra.animation.AnimatedContent
import com.lokesh.composetutorial.extra.animation.AnimationScreen
import com.lokesh.composetutorial.extra.mediaplayer.MediaScreen
import com.lokesh.composetutorial.extra.quiz.QuizScreen
import com.lokesh.composetutorial.extra.tweetApp.screens.CategoryScreen
import com.lokesh.composetutorial.extra.tweetApp.screens.DetailsScreen
import com.lokesh.composetutorial.quizOnline.screens.OnlineQuizScreen
import com.lokesh.composetutorial.quizOnline.screens.QuizCategoryScreen
import com.lokesh.composetutorial.quizOnline.screens.QuizHistoryScreen

@Composable
fun RootNavigation(navController: NavHostController,paddingValues: PaddingValues,snackbarHostState: SnackbarHostState) {

    NavHost(
        navController = navController,
        startDestination = Screens.NavigatorScreen.route
    ) {
        composable(Screens.NavigatorScreen.route){
            NavigatorScreen(navController)
        }

        //Calculator
        navigation(startDestination = Screens.CalculatorScreen.route, route = Graphs.CalculatorGraph.route) {
            composable(Screens.CalculatorScreen.route,
                enterTransition = {  fadeIn() },
                exitTransition = { fadeOut() },
                popEnterTransition = {  fadeIn() },
                popExitTransition = {  fadeOut() }) {

                CalculatorScreen()

            }
        }


        //Online Quiz
        navigation(startDestination = Screens.QuizCategoryScreen.route, route = Graphs.OnlineQuiz.route){
            composable(route = Screens.QuizCategoryScreen.route,
                enterTransition = { fadeIn()},
                exitTransition = { fadeOut() },) {
                QuizCategoryScreen(navController = navController){categoryId->
                    navController.navigate(Screens.OnlineQuizScreen.route + "/${categoryId}")
                }
            }

            composable(route = Screens.OnlineQuizScreen.route + "/{categoryId}",
                arguments = listOf(
                    navArgument("categoryId"){type = NavType.IntType}
                )
            ){ navBackStackEntry ->
                val categoryId = navBackStackEntry.arguments?.getInt("categoryId")?:-1
                OnlineQuizScreen(categoryId,navController)
            }

            composable(route = Screens.QuizHistoryScreen.route ){
                QuizHistoryScreen(navController)
            }
        }

        //See more
        navigation(startDestination = Screens.ExtraScreen.route,route = Graphs.ExtraGraph.route){
            composable(Screens.ExtraScreen.route,
                enterTransition = { slideInHorizontally() + fadeIn() },
                exitTransition = { slideOutHorizontally()+ fadeOut() },
                popEnterTransition = { slideInHorizontally() + fadeIn() },
                popExitTransition = { slideOutHorizontally()+ fadeOut() }){

                ExtraScreen(navController)
            }

            navigation(startDestination = Screens.AnimationScreen.route, route = Graphs.AnimationGraph.route) {
                composable(Screens.AnimationScreen.route,
                    enterTransition = { slideInHorizontally() + fadeIn() },
                    exitTransition = { slideOutHorizontally()+ fadeOut() },
                    popEnterTransition = { slideInHorizontally() + fadeIn() },
                    popExitTransition = { slideOutHorizontally()+ fadeOut() }) {
                    AnimationScreen(navController)
                }

                composable(Screens.AnimateVisibilityScreen.route) { AnimateVisibility() }
                composable(Screens.AnimateColorAndShapeScreen.route) { AnimateColorAndShape() }
                composable(Screens.AnimatedContentScreen.route) { AnimatedContent() }
            }

            navigation(startDestination = Screens.CategoryScreen.route, route = Graphs.TweetsGraph.route) {
                val animateDuration = 300

                composable(route = Screens.CategoryScreen.route,
                    enterTransition = { slideInHorizontally() + fadeIn() },
                    exitTransition = { slideOutHorizontally()+ fadeOut() },
                    popEnterTransition = { slideInHorizontally() + fadeIn() },
                    popExitTransition = { slideOutHorizontally()+ fadeOut() }) {
                    CategoryScreen{ category->
                        navController.navigate( Screens.DetailsScreen.route + "/${category}")
                    }
                }

                composable(route = Screens.DetailsScreen.route + "/{category}",
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

            composable(route = Screens.DeeplinkScreen.route,
                deepLinks = listOf(navDeepLink {
                    uriPattern =  "https://lokesh-compose.com/{id}"
                    action = Intent.ACTION_VIEW
                }),
                arguments = listOf(navArgument("id"){
                    type = NavType.IntType
                    defaultValue = -1
                }),
                enterTransition = { fadeIn(tween()) },
                exitTransition = { fadeOut(tween()) },
                popEnterTransition = { fadeIn(tween()) },
                popExitTransition = { fadeOut(tween()) }){entry ->

                val id = entry.arguments?.getInt("id")?:-1
                DeeplinkScreen(id)
            }


            navigation(startDestination = Screens.QuizScreen.route, route = Graphs.QuizGraph.route) {
                composable(route = Screens.QuizScreen.route,
                    enterTransition = {slideInHorizontally() + fadeIn()},
                    exitTransition = { slideOutHorizontally() + fadeOut() },
                    popEnterTransition = {slideInHorizontally() + fadeIn()},
                    popExitTransition = {slideOutHorizontally() + fadeOut()},
                ) {
                    QuizScreen(navController,snackbarHostState, rememberCoroutineScope())
                }
            }

            composable(route = Screens.MediaScreen.route,
                enterTransition = {slideInHorizontally() + fadeIn()},
                exitTransition = { slideOutHorizontally() + fadeOut() },
                popEnterTransition = {slideInHorizontally() + fadeIn()},
                popExitTransition = {slideOutHorizontally() + fadeOut()}){

                MediaScreen()
            }

        }







    }
}

sealed class Graphs(val route : String){
    data object TweetsGraph : Graphs(route = "tweets_graph")
    data object AnimationGraph : Graphs(route = "animation_graph")
    data object CalculatorGraph : Graphs(route = "calculator_graph")
    data object QuizGraph : Graphs(route = "quiz_graph")
    data object OnlineQuiz : Graphs(route = "online_quiz_graph")
    data object ExtraGraph : Graphs(route = "extra_graph")
}

sealed class Screens(val route: String){
    data object NavigatorScreen : Screens(route = "navigator_screen")
    data object AnimationScreen : Screens(route = "animations_screen")
    data object AnimateVisibilityScreen : Screens(route = "animate_visibility_screen")
    data object AnimateColorAndShapeScreen : Screens(route = "AnimateColorAndShape_screen")
    data object AnimatedContentScreen : Screens(route = "AnimatedContent_screen")
    data object CalculatorScreen : Screens(route = "calculator_screen")
    data object CategoryScreen : Screens(route = "category_screen")
    data object DetailsScreen : Screens(route = "details_screen")
    data object DeeplinkScreen : Screens(route = "deeplink_screen")
    data object QuizScreen : Screens(route = "quiz_screen")
    data object MediaScreen : Screens(route = "media_screen")
    data object QuizCategoryScreen : Screens(route = "quiz_category_screen")
    data object OnlineQuizScreen : Screens(route = "online_quiz_screen")
    data object ExtraScreen : Screens(route = "extraScreen")
    data object QuizHistoryScreen : Screens(route = "quizHistoryScreen")

}