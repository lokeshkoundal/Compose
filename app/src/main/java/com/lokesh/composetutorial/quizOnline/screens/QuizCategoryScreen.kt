package com.lokesh.composetutorial.quizOnline.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.lokesh.composetutorial.R
import com.lokesh.composetutorial.quizOnline.composeElements.CategoryCard
import com.lokesh.composetutorial.quizOnline.viewModel.QuizCategoryVM

@Composable
fun QuizCategoryScreen(navController: NavHostController,onCategoryClick: (Int) -> Unit) {

    val quizVM : QuizCategoryVM = hiltViewModel()
    val quizCategories = quizVM.quizCategories

    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize().padding(12.dp)) {

        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxWidth()) {
            Spacer(Modifier.height(12.dp))

            Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back",
                modifier = Modifier
                    .padding(6.dp)
                    .clip(CircleShape)
                    .clickable {
                    navController.popBackStack()
                })

            Spacer(Modifier.width(26.dp))

            Text(text = "Pick a Category", fontFamily = FontFamily(Font(R.font.nunito_bold)), fontSize = 24.sp)
        }

        Spacer(Modifier.height(20.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
//            contentPadding = PaddingValues(8.dp)
        ) {
            items(quizCategories){quizCategory ->
                CategoryCard(
                    image = quizCategory.image,
                    name = quizCategory.name,
                    colors = cardColors(
                        containerColor = quizCategory.cardColor
                    ),
                    textColor = quizCategory.textColor,
                    onClick = {
                        onCategoryClick(quizCategory.categoryID)
                    }

                )
            }
        }
    }
}


