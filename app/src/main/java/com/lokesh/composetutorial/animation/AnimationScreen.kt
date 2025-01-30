package com.lokesh.composetutorial.animation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AnimationScreen(){

//    AnimateVisibility()
//    AnimateColorAndShape()
    AnimatedContent()
}


@Composable
fun AnimateVisibility(){
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        var isVisible by remember {
            mutableStateOf(false)
        }
        Button(onClick = {
            isVisible = !isVisible

        }) {
            Text(text = "Toggle")
        }

        AnimatedVisibility(
            visible = isVisible,
            enter = slideInHorizontally(),
            exit = slideOutHorizontally(),
            modifier = Modifier.fillMaxWidth().weight(1f)) {
            Box(Modifier.background(Color.Red))
        }
    }
}


@Composable
fun AnimateColorAndShape(){

    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        var isRound by remember {
            mutableStateOf(false)
        }
        val transition = rememberInfiniteTransition()
        val color by transition.animateColor(
            initialValue = Color.Red,
            targetValue = Color.Green,
            animationSpec = infiniteRepeatable(
                tween(1000),
                repeatMode = RepeatMode.Reverse
            )
        )

        Button(onClick = { isRound = !isRound }) {
            Text(text = "Toggle")
        }

        Spacer(Modifier.height(60.dp))

        val borderRadiusState by animateIntAsState(
            targetValue =  if(isRound) 100 else 0,
            animationSpec = tween(
                durationMillis = 2000,
                easing = LinearEasing
            )
           /* animationSpec = spring(
                dampingRatio = Spring.DampingRatioHighBouncy,
                stiffness = Spring.StiffnessLow
            )*/

        )
        Box(modifier =  Modifier.size(200.dp)
            .clip(RoundedCornerShape(borderRadiusState))
            .background(color))

    }
}

@Composable
fun AnimatedContent(){
    var count by remember {
        mutableIntStateOf(0)
    }
    Column(modifier =  Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        Button(onClick = {count--}) {
            Text("Decrease")
        }

        Spacer(modifier = Modifier.height(20.dp))

        AnimatedContent(targetState = count,
            transitionSpec = {
                if(targetState>initialState){
                    (slideInVertically { fullHeight -> fullHeight  } + fadeIn()).togetherWith(
                        slideOutVertically { fullHeight -> -fullHeight } + fadeOut())
                }else{
                    (slideInVertically { fullHeight -> -fullHeight } + fadeIn()).togetherWith(
                        slideOutVertically { fullHeight -> fullHeight } + fadeOut())
                }
            }){
            Box(modifier = Modifier.size(180.dp), contentAlignment = Alignment.Center){
                Text(text = it.toString(), style = MaterialTheme.typography.headlineLarge)
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {count++}) {
            Text("Increase")
        }

    }
}