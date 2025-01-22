package com.lokesh.composetutorial.instagram.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.lokesh.composetutorial.instagram.ui.ui.theme.ComposeTutorialTheme
import kotlinx.serialization.Serializable

@Serializable
object Home

@Serializable
object Search

@Serializable
object Add

@Serializable
object Reels

@Serializable
object Profile

class InstagramActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTutorialTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "home"){
                    navigation(startDestination = "login", route = "auth"){

                    }
                }
//                RootNavGraph(rememberNavController())
            }
        }
    }
}

@Composable
fun InstagramNavHost(navController: NavHostController, modifier: Modifier) {
    NavHost(navController = navController,
        startDestination = Home){
        composable<Home>{  }
        composable<Search>{}
        composable<Add>{}
        composable<Reels>{}
        composable<Profile>{}
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    ComposeTutorialTheme {
        Greeting("Android")
    }
}