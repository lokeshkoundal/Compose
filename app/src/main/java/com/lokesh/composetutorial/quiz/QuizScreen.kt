package com.lokesh.composetutorial.quiz

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.lokesh.composetutorial.quiz.composeElements.AnswerUI
import com.lokesh.composetutorial.quiz.model.QuizVM
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import androidx.compose.foundation.layout.fillMaxWidth as fillMaxWidth1

@Composable
fun QuizScreen(navController: NavController,snackbarHostState: SnackbarHostState,coroutineScope : CoroutineScope) {

    val quizVM : QuizVM = hiltViewModel()

    val questions = quizVM.questions.collectAsState()
    val currentQuestionIndex = quizVM.currentQuestionIndex.collectAsState()


    val scored = quizVM.score.collectAsState()


    Scaffold(
        topBar = {TopBar(navController,questions.value.size,currentQuestionIndex.value)},
        bottomBar = {BottomBar(quizVM)}

    ) {paddingValues ->

        val isFirstComposition = remember { mutableStateOf(true) }  // Track first composition


        LaunchedEffect(scored.value) {
            if (isFirstComposition.value)
                isFirstComposition.value = false
            else{
                coroutineScope.launch {
                    navController.popBackStack()

                    snackbarHostState.showSnackbar(message = "Your Score is ${quizVM.score.value}",
                        duration = SnackbarDuration.Long)

                }
            }

        }

        Column (Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(horizontal = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center){

            Card(modifier = Modifier
                .padding(bottom = 80.dp)
                .fillMaxWidth1(),
                shape = RoundedCornerShape(6.dp),
                elevation = CardDefaults.cardElevation(8.dp),
                colors = CardColors(
                    containerColor = Color(0xFF667BEC),
                    contentColor = Color.Unspecified,
                   disabledContainerColor =Color.Unspecified,
                   disabledContentColor = Color.Unspecified
            )
        ) {
                Box(Modifier.align(Alignment.CenterHorizontally).padding(horizontal = 16.dp, vertical = 40.dp), contentAlignment = Alignment.Center){

                    AnimatedContent(
                        targetState = currentQuestionIndex.value,
                        transitionSpec = {
                            if(targetState >  initialState) {
                                slideIntoContainer(towards = AnimatedContentTransitionScope.SlideDirection.Start, animationSpec = tween(100)).togetherWith(
                                    slideOutOfContainer(towards = AnimatedContentTransitionScope.SlideDirection.End, animationSpec = tween(100))
                                )
                            }
                            else{
                                slideIntoContainer(towards = AnimatedContentTransitionScope.SlideDirection.End, animationSpec = tween(100)).togetherWith(
                                    slideOutOfContainer(towards = AnimatedContentTransitionScope.SlideDirection.Start, animationSpec = tween(100))
                                )
                            }
                        }

                    ) {
                        Text(text = questions.value[it].question, color = Color.White,
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center)}

                }
        }

            Spacer(Modifier.height(12.dp))

            Text("Select One", color = Color.LightGray, fontSize = 12.sp)

            Spacer(Modifier.height(8.dp))

            questions.value[currentQuestionIndex.value].answers.forEach{
                AnswerUI(answer = it,isSelected = it == quizVM.questions.value[currentQuestionIndex.value].selectedAnswer,
                    isCorrect = it == quizVM.questions.value[currentQuestionIndex.value].correctAnswer ){ selected ->
                    quizVM.answerSelected(currentQuestionIndex.value,selected)
                }
                Spacer(Modifier.height(8.dp))

            }
        }

    }

}

@Composable
fun BottomBar(quizVM: QuizVM) {

    val lastQState = quizVM.isLastQ.collectAsState()

    HorizontalDivider()

        Row(Modifier.fillMaxWidth1()
            .padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center) {

            OutlinedButton(onClick = {quizVM.previousQuestion()},
                modifier = Modifier.weight(0.5f).padding(4.dp)) {
                Text("Previous", color = Color(0xFF3F51B5))
            }

            Button(onClick = { if(!lastQState.value){quizVM.nextQuestion()} else {quizVM.calculateScore()} }, modifier = Modifier.weight(0.5f).padding(4.dp),
                colors = ButtonDefaults.buttonColors(if(lastQState.value)Color(  0xFF4CAF50)else Color(
                    0xFF667BEC
                )
                ),) {
                Text(text = if(lastQState.value) "Submit" else "Next", color = Color.White)
            }

        }
    }


@Composable
fun TopBar(navController: NavController, size: Int, currentQuestionIndex: Int) {
    Column(Modifier.fillMaxWidth1().padding(6.dp)) {

        Box(Modifier.fillMaxWidth1()){
            Text( currentQuestionIndex.plus(1).toString() + " of "+ size, Modifier.align(Alignment.Center))

            IconButton(onClick = {navController.popBackStack()},
                Modifier.align(Alignment.CenterEnd),
                ) {
                Icon(imageVector = Icons.Default.Clear,
                    contentDescription = "Clear Icon",
                    modifier = Modifier.align(Alignment.Center))

            }
        }

        val animatedProgress by animateFloatAsState(
            targetValue = currentQuestionIndex.toFloat().plus(1)/size.toFloat(),
            animationSpec = tween(),
            label = "progressAnimation"
        )

        LinearProgressIndicator(
            progress = { animatedProgress },
            trackColor = Color(0xFFE3E3E3),
            strokeCap = StrokeCap.Round,

            gapSize = 0.dp,
            modifier = Modifier
                .fillMaxWidth1()
                .height(6.dp)
                .clip(RoundedCornerShape(12.dp)),
            color = Color(0xFF3F51B5),
        )

    }
}