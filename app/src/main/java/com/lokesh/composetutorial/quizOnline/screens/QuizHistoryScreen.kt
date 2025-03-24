package com.lokesh.composetutorial.quizOnline.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.lokesh.composetutorial.quizOnline.viewModel.QuizHistoryVM


@Composable
fun QuizHistoryScreen(navController: NavController) {
    val quizHistoryVM : QuizHistoryVM = hiltViewModel()


}

@Composable
fun QuizResultCard(iconId :Int,categoryName : String,correctAns : Int,wrongAns:Int) {

    Card(shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)) {
        Row(modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 6.dp, vertical = 3.dp)) {
            Icon(painter = painterResource(iconId),
                contentDescription = null)

            Spacer(modifier = Modifier.width(4.dp))

            Column(verticalArrangement = Arrangement.Center) {

                Text(text = categoryName, fontWeight = FontWeight.Bold, fontSize = 26.sp)

                Row {
                    
                }

            }
        }
    }
}

@Composable
fun DiamondCard(modifier: Modifier = Modifier) {

    Card() {}
}