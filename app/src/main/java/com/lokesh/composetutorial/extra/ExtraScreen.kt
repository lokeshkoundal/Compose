package com.lokesh.composetutorial.extra

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.lokesh.composetutorial.Graphs
import com.lokesh.composetutorial.R
import com.lokesh.composetutorial.Screens

@Composable
fun ExtraScreen(navController: NavController) {
    Column(Modifier.fillMaxSize().padding(10.dp), horizontalAlignment = CenterHorizontally,
        verticalArrangement = Arrangement.Center) {

        NavButtonSmall(
            onClick = { navController.navigate(Graphs.AnimationGraph.route) },
            name = "Animations",
            id = R.drawable.ic_animation)

        NavButtonSmall(
            onClick = { navController.navigate(Graphs.TweetsGraph.route) },
            name = "Tweets",
            id = R.drawable.ic_tweet,
        )

        NavButtonSmall(
            onClick = { navController.navigate(Screens.DeeplinkScreen.route) },
            name = "Deeplink",
            id = R.drawable.ic_link
        )

        NavButtonSmall(
            onClick = { navController.navigate(Screens.QuizScreen.route) },
            name = "Quiz",
            id = R.drawable.ic_quiz)

        NavButtonSmall(
            onClick = { navController.navigate(Screens.MediaScreen.route) },
            name = "Media player",
            id = R.drawable.ic_movie,
        )

    }
}

@Composable
fun NavButtonSmall(name:String,id:Int, onClick: () -> Unit) {
    Button(
        onClick = { onClick() },
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        shape =  RoundedCornerShape(12.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                painter = painterResource(id),
                contentDescription = "Icon",
                modifier = Modifier
                    .size(28.dp)
                    .align(Alignment.CenterStart),
            )

            Text(
                text = name,
                fontSize = 22.sp,
                modifier = Modifier.align(Alignment.Center).padding(6.dp),
            )
        }
    }

}