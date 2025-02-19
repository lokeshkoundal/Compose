package com.lokesh.composetutorial

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun NavigatorScreen(navController: NavController) {

    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center) {

        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 12.dp,
                vertical = 20.dp
            )
            .verticalScroll(rememberScrollState())) {

            NavButton(
                onClick = { navController.navigate(Graphs.AnimationGraph.route) },
                name = "Animations",
                id = R.drawable.ic_animation,
            )

            NavButton(
                onClick = { navController.navigate(Graphs.CalculatorGraph.route) },
                name = "Calculator",
                id = R.drawable.ic_calculator,
            )

            NavButton(
                onClick = { navController.navigate(Graphs.TweetsGraph.route) },
                name = "Tweets",
                id = R.drawable.ic_tweet,
            )

            NavButton(
                onClick = { navController.navigate(Screens.DeeplinkScreen.route) },
                name = "Deeplink",
                id = R.drawable.ic_link,
            )

            NavButton(
                onClick = { navController.navigate(Screens.QuizScreen.route) },
                name = "Quiz",
                id = R.drawable.ic_quiz,
            )

            NavButton(
                onClick = { navController.navigate(Graphs.OnlineQuiz.route) },
                name = "Online Quiz",
                id = R.drawable.ic_quiz,
            )

            NavButton(
                onClick = { navController.navigate(Screens.MediaScreen.route) },
                name = "Media player",
                id = R.drawable.ic_movie,
            )

        }
    }
}

@Composable
fun NavButton(name:String,id:Int, onClick: () -> Unit) {
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
                tint = Color.White,
            )

            Text(
                text = name,
                fontSize = 22.sp,
                modifier = Modifier.align(Alignment.Center).padding(6.dp),
            )
        }
    }

}