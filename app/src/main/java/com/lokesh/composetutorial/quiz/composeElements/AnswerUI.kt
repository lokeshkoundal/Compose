package com.lokesh.composetutorial.quiz.composeElements

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.lokesh.composetutorial.quiz.model.Answer

@Composable
fun AnswerUI(answer : Answer, onClick: () -> Unit) {
    Surface(
        border = BorderStroke(1.dp, Color.Black),
        shape = MaterialTheme.shapes.small,
        modifier = Modifier.fillMaxWidth(),
        color = Color(0xFFDE6565),
        shadowElevation = 8.dp
    ) {
        Box(Modifier.fillMaxWidth().padding(16.dp)){
            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(painterResource(answer.imageID), contentDescription = "answer",
                        Modifier.size(45.dp)
                            .padding(8.dp))
                    Spacer(Modifier.width(4.dp))

                    Text(answer.answer)
                }


                RadioButton(selected = answer.isSelected,
                    onClick = onClick)

            }
        }
    }

}