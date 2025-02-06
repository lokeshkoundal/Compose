package com.lokesh.composetutorial.quiz.composeElements

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.lokesh.composetutorial.quiz.model.Answer

@Composable
fun AnswerUI(answer: Answer,isSelected: Boolean,isCorrect: Boolean, onClick: (selectedAnswer: Answer) -> Unit) {
    Surface(
        border = BorderStroke(1.dp, Color.Black),
        shape = MaterialTheme.shapes.extraSmall,
        modifier = Modifier.fillMaxWidth(),
        color = if(!isSelected) Color.White
                    else if(isCorrect)Color(0xFF4CAF50)
                        else Color(0xFFF44336),
        shadowElevation = 8.dp,
        onClick = { onClick(answer) }
    ) {
            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth().padding(8.dp),
                verticalAlignment = Alignment.CenterVertically) {

                AnimatedContent(targetState = answer.text,
                    transitionSpec = {
                       fadeIn().togetherWith(
                                fadeOut()
                            )
                    }) {
                    Text(it, fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.Medium)

                }

                RadioButton(
                    modifier = Modifier.padding(4.dp),
                    selected = isSelected,
                    onClick = {onClick(answer)},
                    colors = RadioButtonColors(
                        selectedColor = Color(0xFF3F51B5),
                        unselectedColor = Color.Black,
                        disabledSelectedColor = Color.Black,
                        disabledUnselectedColor = Color.Black

                    )
                )
        }
    }

}