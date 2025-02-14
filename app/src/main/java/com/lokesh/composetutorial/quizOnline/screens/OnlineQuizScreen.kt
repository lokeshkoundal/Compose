package com.lokesh.composetutorial.quizOnline.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.HtmlCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.lokesh.composetutorial.R
import com.lokesh.composetutorial.quiz.composeElements.AnswerUI
import com.lokesh.composetutorial.quiz.model.Answer
import com.lokesh.composetutorial.quizOnline.viewModel.OnlineQuizVM

@Composable
fun OnlineQuizScreen(categoryId: Int,navController: NavController) {
    val onlineQuizVM: OnlineQuizVM = hiltViewModel()
    val isLoading by onlineQuizVM.isLoading.collectAsState()
    val quizQuestions by onlineQuizVM.quizQuestions.collectAsState()

    LaunchedEffect(categoryId) {
        onlineQuizVM.getQuestions(categoryId)
    }

    if(isLoading){
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            CircularProgressIndicator()
        }
    }else if(quizQuestions.isEmpty()){
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            Text("Sorry, No Questions Available", fontFamily = FontFamily(Font(R.font.nunito_bold)))
        }
    }
    else{
       OnlineQuizScreen2(navController,onlineQuizVM)
    }

}


@Composable
fun OnlineQuizScreen2(
    navController: NavController,
    onlineQuizVM: OnlineQuizVM
) {
    val currentQuestionIndex by onlineQuizVM.currentQuestionIndex.collectAsState()
    val quizQuestions by onlineQuizVM.quizQuestions.collectAsState()
    val isQuizFinished by onlineQuizVM.isQuizFinished.collectAsState()
    val score by onlineQuizVM.scored.collectAsState()


    Scaffold(
        topBar = {TopBar(navController,quizQuestions.size,currentQuestionIndex,quizQuestions[currentQuestionIndex].difficulty)},
        bottomBar = {BottomBar(onlineQuizVM)}

    ) {paddingValues ->

        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center){

            if(isQuizFinished){
                QuizResultDialog(
                    score = score,
                    onDismiss = {
                        onlineQuizVM.dismissDialog()
                        navController.popBackStack() },
                )
            }
            Column (Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 12.dp)
                .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween){

                Spacer(Modifier.height(12.dp))

                Card(modifier = Modifier
                    .padding(bottom = 80.dp)
                    .fillMaxWidth(),
                    shape = RoundedCornerShape(6.dp),
                    elevation = CardDefaults.cardElevation(8.dp),
                    colors = CardColors(
                        containerColor = Color(0xFF667BEC),
                        contentColor = Color.Unspecified,
                        disabledContainerColor = Color.Unspecified,
                        disabledContentColor = Color.Unspecified
                    )
                ) {
                    Box(Modifier.align(Alignment.CenterHorizontally).padding(horizontal = 16.dp, vertical = 40.dp), contentAlignment = Alignment.Center){

                        AnimatedContent(
                            targetState = currentQuestionIndex,
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


                            Text(text = parseHtmlToText(quizQuestions[it].question), color = Color.White,
                                fontFamily = FontFamily.SansSerif,
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center)}

                    }
                }

                Spacer(Modifier.height(12.dp))


                Column (horizontalAlignment = Alignment.CenterHorizontally){
                    Text("Select One", color = Color.LightGray, fontSize = 12.sp, fontWeight = FontWeight.Medium)
                    Spacer(Modifier.height(8.dp))
                    quizQuestions[currentQuestionIndex].allAnswer.forEach {
                        AnswerUI(
                            answer = Answer(it),
                            isSelected = it == quizQuestions[currentQuestionIndex].selectedAnswer,
                            isCorrect = it == quizQuestions[currentQuestionIndex].correct_answer
                        ) {selected->
                            onlineQuizVM.answerSelected(currentQuestionIndex, selected.text)
                        }
                        Spacer(Modifier.height(8.dp))

                    }
                }

            }
        }



    }

}

@Composable
fun QuizResultDialog(score: Int, onDismiss: () -> Unit) {
    AlertDialog(
        shape = RoundedCornerShape(12.dp),
        onDismissRequest = onDismiss,
        title = { Text("Quiz Completed!",
            fontFamily = FontFamily(Font(R.font.nunito_bold)),
            color = Color.White) },

        text = { Text("Your Score: $score",
            fontFamily = FontFamily(Font(R.font.nunito_bold)),
            color = Color.White) },

        confirmButton = {
            Button(onClick = onDismiss) {
                Text("Play Another Quiz",
                    fontFamily = FontFamily(Font(R.font.nunito_bold))
                )
            }
        },
        modifier = Modifier.fillMaxWidth(),
        containerColor = Color(0xFF6D83F5)
    )
}


@Composable
fun BottomBar(onlineQuizVM: OnlineQuizVM) {

    val lastQState by onlineQuizVM.isLastQ.collectAsState()

    HorizontalDivider()

    Row(Modifier.fillMaxWidth()
        .padding(horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center) {

        OutlinedButton(onClick = {onlineQuizVM.previousQuestion()},
            modifier = Modifier.weight(0.5f).padding(4.dp)) {
            Text("Previous", color = Color(0xFF3F51B5))
        }

        Button(onClick = { if(!lastQState){
            onlineQuizVM.nextQuestion()
        } else {
            onlineQuizVM.calculateScore()
        } },
            modifier = Modifier.weight(0.5f).padding(4.dp),
            colors = ButtonDefaults.buttonColors(if(lastQState) Color(  0xFF4CAF50) else Color(
                0xFF667BEC
            )
            ),) {
            Text(text = if(lastQState) "Submit" else "Next", color = Color.White)
        }

    }
}


@Composable
fun TopBar(navController: NavController, size: Int, currentQuestionIndex: Int,difficulty: String) {
    Column(Modifier.fillMaxWidth().padding(6.dp)) {

        Box(Modifier.fillMaxWidth()){

            Text(text = difficulty,
                fontFamily = FontFamily(Font(R.font.nunito_bold)),
                fontWeight = FontWeight.Light,
                modifier = Modifier.align(Alignment.CenterStart)
                    .clip(RoundedCornerShape(4.dp))
                    .background(color = if(difficulty == "easy") Color(0xFF4CAF50) else if(difficulty == "medium") Color(0xFFFFC107) else Color(
                        0xFFEC4E43
                    ))
                    .padding(horizontal = 6.dp, vertical = 2.dp))

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
                .fillMaxWidth()
                .height(6.dp)
                .clip(RoundedCornerShape(12.dp)),
            color = Color(0xFF3F51B5),
        )

    }
}

fun parseHtmlToText(html: String): String {
    return HtmlCompat.fromHtml(html, HtmlCompat.FROM_HTML_MODE_LEGACY).toString()
}