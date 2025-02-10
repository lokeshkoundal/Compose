package com.lokesh.composetutorial.quizOnline.composeElements

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun CategoryCard(modifier: Modifier = Modifier,image : Int,name : String,colors: CardColors,textColor :Color ) {

    Card(
        shape = RoundedCornerShape(14.dp),
        modifier = Modifier.size(150.dp)
            .padding(4.dp)
            .then(modifier),
        colors = colors
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {

            Image(painter = painterResource(id = image), contentDescription = "Category Image")

            Spacer(Modifier.height(6.dp))

            Text(text = name.uppercase(), textAlign = TextAlign.Center, color = textColor)
        }
    }
}