package com.lokesh.composetutorial.quizOnline.models

import androidx.compose.ui.graphics.Color


data class QuizCategoryData(
    val categoryID: Int, val image: Int,
    val name: String, val cardColor: Color,
    val textColor: Color)