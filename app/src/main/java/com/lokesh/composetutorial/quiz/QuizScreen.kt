package com.lokesh.composetutorial.quiz

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import com.lokesh.composetutorial.R
import com.lokesh.composetutorial.quiz.composeElements.AnswerUI
import com.lokesh.composetutorial.quiz.model.Answer
import kotlinx.coroutines.delay

@Composable
fun QuizScreen(modifier: Modifier = Modifier) {

    val answer  = Answer(R.drawable.ic_link, "answer", false)
    val answer2  = Answer(R.drawable.ic_tweet, "answer", false)
    val answer3  = Answer(R.drawable.ic_link, "answer", false)
    val answer4  = Answer(R.drawable.ic_tweet, "answer", false)

    val answers = listOf(answer, answer2, answer3, answer4)

    Scaffold(
        topBar = {TopBar()}
    ) {paddingValues ->

        Column(Modifier
            .fillMaxSize()
            .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,){
            answers.forEach{
                AnswerUI(answer = it){}
                Spacer(Modifier.height(8.dp))

            }
        }

    }

}

@Composable
fun TopBar() {
    Column(Modifier.fillMaxWidth().padding(6.dp)) {

        Box(Modifier.fillMaxWidth()){
            Text("2 of 10", Modifier.align(Alignment.Center))

            IconButton(onClick = {},
                Modifier.align(Alignment.CenterEnd),
                ) {
                Icon(imageVector = Icons.Default.Clear,
                    contentDescription = "Clear Icon",
                    modifier = Modifier.align(Alignment.Center))

            }
        }

        // Animate progress change
        var progress by remember { mutableFloatStateOf(0f) }

        // Animate the progress
        val animatedProgress by animateFloatAsState(
            targetValue = progress,
            animationSpec = tween(durationMillis = 1000),
            label = "progressAnimation"
        )

        LinearProgressIndicator(
            progress = { animatedProgress }, // ✅ Use lambda instead of direct float
            trackColor = Color(0xFFE3E3E3),
            strokeCap = StrokeCap.Square,

            gapSize = 0.dp,
            modifier = Modifier
                .fillMaxWidth()
                .height(6.dp)
                .clip(RoundedCornerShape(12.dp)),
            color = Color(0xFF3F51B5),
        )

        // Simulate progress update after a delay
        LaunchedEffect(Unit) {
            delay(100) // Delay before animation starts
            progress = .6f // Change progress to trigger animation
        }

    }
}
