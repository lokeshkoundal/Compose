package com.lokesh.composetutorial

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.lokesh.composetutorial.calculator.theme.Green

@Composable
fun NavigatorScreen(navController: NavController) {

    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center) {

        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 12.dp,
                vertical = 20.dp
            )) {

            NavButton(
                onClick = { navController.navigate(Routes.AnimationGraph.route) },
                name = "Animations",
                id = R.drawable.ic_animation,
                color = Green
            )

            NavButton(
                onClick = { navController.navigate(Routes.CalculatorGraph.route) },
                name = "Calculator",
                id = R.drawable.ic_calculator,
                color = Green
            )

            NavButton(
                onClick = { navController.navigate(Routes.TweetsGraph.route) },
                name = "Tweets",
                id = R.drawable.ic_tweet,
                color = Green
            )
        }
    }
}

@Composable
fun NavButton(name:String,id:Int,color: Color, onClick: () -> Unit) {
    Button(
        onClick = { onClick() },
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        colors = ButtonDefaults.buttonColors(color),
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