package com.lokesh.composetutorial.quizOnline.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.sharp.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.lokesh.composetutorial.Constants
import com.lokesh.composetutorial.R
import com.lokesh.composetutorial.quizOnline.viewModel.QuizHistoryVM


@Composable
fun QuizHistoryScreen(navController: NavController) {
    val quizHistoryVM : QuizHistoryVM = hiltViewModel()
    val quizHistoryList = quizHistoryVM.quizHistoryList.collectAsState()
    val isLoading = quizHistoryVM.isLoading.collectAsState()

    Column(Modifier.fillMaxSize().padding(6.dp)) {

        Box(Modifier.fillMaxWidth()
            .clip(RoundedCornerShape(6.dp))
            .padding(8.dp)) {
            Icon(imageVector = Icons.AutoMirrored.Sharp.ArrowBack,
                contentDescription = "back",
                modifier = Modifier.align(Alignment.CenterStart)
                    .padding(4.dp)
                    .clip(CircleShape)
                    .clickable { navController.popBackStack() })

            Text(
                "Score History", fontSize = 26.sp, fontFamily = FontFamily(Font(R.font.nunito_bold)),
                modifier = Modifier.align(Alignment.Center)
            )

            Icon(
                imageVector = Icons.Default.Delete, contentDescription = "Empty History",
                modifier = Modifier.align(Alignment.CenterEnd)
                    .padding(4.dp)
                    .clip(CircleShape)
                    .clickable { quizHistoryVM.emptyQuizHistory() }
            )
        }
        Spacer(modifier = Modifier.height(10.dp))

        if (isLoading.value){
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        }
        else{
            LazyColumn(Modifier.fillMaxWidth().padding(6.dp)) {

                    items(quizHistoryList.value){data->
                    QuizResultCard(
                        iconId = Constants.quizCategories.find { it.categoryID==data.categoryId }!!.image,
                        categoryName = Constants.quizCategories.find { it.categoryID==data.categoryId }!!.name ,
                        correctAns = data.correctAnswers,
                        wrongAns = data.wrongAnswers
                    )
                }
            }
        }
    }
}

@Composable
fun QuizResultCard(iconId :Int,categoryName : String,correctAns : Int,wrongAns:Int) {

    ElevatedCard(shape = RoundedCornerShape(12.dp),
        elevation =  CardDefaults.cardElevation(defaultElevation = 30.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp).padding(6.dp)
            .clip(RoundedCornerShape(12.dp)) // ✅ Clip before clickable
            .clickable  {  }) {
        Row(modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp, vertical = 5.dp),
            verticalAlignment = Alignment.CenterVertically) {
            Icon(painter = painterResource(iconId),
                contentDescription = null)

            Spacer(modifier = Modifier.width(8.dp))

            Column(verticalArrangement = Arrangement.SpaceEvenly) {

                Text(text = categoryName, fontWeight = FontWeight.Bold, fontSize = 26.sp)

                Spacer(Modifier.height(6.dp))

                Row {
                    DiamondCard(correctAns.toString(),Color.Green)

                    Text("Correct Answers",
                        modifier = Modifier.padding(horizontal = 6.dp),
                        color = Color.Gray)

                    Spacer(Modifier.width(6.dp))

                    DiamondCard(wrongAns.toString(),Color.Red)

                    Text("Wrong Answers",
                        modifier = Modifier.padding(horizontal = 6.dp),
                        color = Color.Gray)

                }

            }
        }
    }
}

@Composable
fun DiamondCard(text:String,color: Color) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(20.dp) // Controls the size of the diamond
            .graphicsLayer(rotationZ = 45f) // Rotates the Box to form a diamond
            .background(color, shape = RoundedCornerShape(6.dp)) // Diamond with rounded corners
    ) {
        Text(
            text = text,
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .graphicsLayer(rotationZ = -45f) // Rotates text back to normal
        )
    }
}