package com.lokesh.composetutorial.quizOnline.composeElements

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lokesh.composetutorial.R
import kotlinx.coroutines.delay

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CategoryCard(modifier: Modifier = Modifier,image : Int,name : String,colors: CardColors,textColor :Color,onClick :() -> Unit ) {

    var isPressed by remember { mutableStateOf(false) }

    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.9f else 1f, // Shrinks slightly when pressed
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
    )

    LaunchedEffect(isPressed) {
        if (isPressed) {
            delay(80) // Short delay for bounce effect
            isPressed = false
        }
    }

        Card(
            shape = RoundedCornerShape(14.dp),
            modifier = Modifier.width(150.dp)
                .height(180.dp)
                .padding(6.dp)
                .then(modifier)
                .graphicsLayer(scaleX = scale, scaleY = scale) // Apply scaling
                .combinedClickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null, // Removes default ripple effect

                    onLongClick = {
                        isPressed = true
                    },
                    onClick = {
                        isPressed = true
                        onClick()
                    }
                ),
            colors = colors,
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),

        ) {

            Column(horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxSize()) {

                Image(painter = painterResource(id = image), contentDescription = "Category Image")

                Spacer(Modifier.height(6.dp))

                Text(text = name, textAlign = TextAlign.Center,
                    fontSize = 24.sp,
                    color = textColor,
                    fontFamily = FontFamily(Font(R.font.nunito_bold)))
            }
        }


}