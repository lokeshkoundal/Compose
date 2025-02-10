package com.lokesh.composetutorial.quizOnline.models

import android.graphics.Color
import androidx.compose.material3.CardColors

data class QuizCategoryData(val categoryID : Int,val image : Int,
                            val name : String, val cardColors: CardColors,
                            val textColor  : Color)