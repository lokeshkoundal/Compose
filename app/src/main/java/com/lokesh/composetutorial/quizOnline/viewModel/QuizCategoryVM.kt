package com.lokesh.composetutorial.quizOnline.viewModel

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.lokesh.composetutorial.R
import com.lokesh.composetutorial.quizOnline.models.QuizCategoryData

class QuizCategoryVM : ViewModel() {

    val quizCategories = listOf(
        QuizCategoryData(9, R.drawable.ic_kotlin, "General Knowledge", Color(0xFF2196F3), Color.White), // Blue
        QuizCategoryData(10, R.drawable.ic_kotlin, "Books", Color(0xFFD32F2F), Color.White), // Red
        QuizCategoryData(11, R.drawable.ic_kotlin, "Film", Color(0xFF4CAF50), Color.Black), // Green
        QuizCategoryData(12, R.drawable.ic_kotlin, "Music", Color(0xFFFFEB3B), Color.Black), // Yellow
        QuizCategoryData(13, R.drawable.ic_kotlin, "Musicals & Theatres", Color(0xFF9C27B0), Color.White), // Purple
        QuizCategoryData(14, R.drawable.ic_kotlin, "Television", Color(0xFF00BCD4), Color.Black), // Cyan
        QuizCategoryData(15, R.drawable.ic_kotlin, "Video Games", Color(0xFF616161), Color.White), // Dark Gray
        QuizCategoryData(16, R.drawable.ic_kotlin, "Board Games", Color(0xFF795548), Color.White), // Brown
        QuizCategoryData(17, R.drawable.ic_kotlin, "Science & Nature", Color(0xFF03A9F4), Color.White), // Light Blue
        QuizCategoryData(18, R.drawable.ic_kotlin, "Computers", Color(0xFF607D8B), Color.White), // Blue Gray
        QuizCategoryData(19, R.drawable.ic_kotlin, "Mathematics", Color(0xFFF44336), Color.White), // Bright Red
        QuizCategoryData(20, R.drawable.ic_kotlin, "Mythology", Color(0xFF673AB7), Color.White), // Deep Purple
        QuizCategoryData(21, R.drawable.ic_kotlin, "Sports", Color(0xFFFF9800), Color.White), // Orange
        QuizCategoryData(22, R.drawable.ic_kotlin, "Geography", Color(0xFF4CAF50), Color.White), // Green
        QuizCategoryData(23, R.drawable.ic_kotlin, "History", Color(0xFF6D4C41), Color.White), // Brown
        QuizCategoryData(24, R.drawable.ic_kotlin, "Politics", Color(0xFFD50000), Color.White), // Dark Red
        QuizCategoryData(25, R.drawable.ic_kotlin, "Art", Color(0xFFFF4081), Color.Black), // Pink
        QuizCategoryData(26, R.drawable.ic_kotlin, "Celebrities", Color(0xFFFFD700), Color.Black), // Gold
        QuizCategoryData(27, R.drawable.ic_kotlin, "Animals", Color(0xFF388E3C), Color.White), // Dark Green
        QuizCategoryData(28, R.drawable.ic_kotlin, "Vehicles", Color(0xFF1976D2), Color.White), // Deep Blue
        QuizCategoryData(29, R.drawable.ic_kotlin, "Comics", Color(0xFFFF5252), Color.Black), // Light Red
        QuizCategoryData(30, R.drawable.ic_kotlin, "Gadgets", Color(0xFF9E9E9E), Color.White), // Gray
        QuizCategoryData(31, R.drawable.ic_kotlin, "Japanese Anime & Manga", Color(0xFFFF4081), Color.Black), // Pink
        QuizCategoryData(32, R.drawable.ic_kotlin, "Cartoon & Animations", Color(0xFFFFD600), Color.Black), // Bright Yellow
        QuizCategoryData(-1, R.drawable.ic_kotlin, "Random", Color(0xFF934FCC), Color.Black) // Bright Yellow
    )

}