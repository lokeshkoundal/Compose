package com.lokesh.composetutorial.quizOnline.viewModel

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.lokesh.composetutorial.R
import com.lokesh.composetutorial.quizOnline.models.QuizCategoryData

class QuizCategoryVM : ViewModel() {

    val quizCategories = listOf(
        QuizCategoryData(9, R.drawable.ic_kotlin, "General Knowledge", Color(0xFF56A7EC), Color.White), // Blue
        QuizCategoryData(10, R.drawable.ic_kotlin, "Books", Color(0xFFE55858), Color.White), // Red
        QuizCategoryData(11, R.drawable.ic_kotlin, "Film", Color(0xFF5CD561), Color.Black), // Green
        QuizCategoryData(12, R.drawable.ic_kotlin, "Music", Color(0xFFE0D258), Color.Black), // Yellow
        QuizCategoryData(13, R.drawable.ic_kotlin, "Musicals & Theatres", Color(0xFFC958DE), Color.White), // Purple
        QuizCategoryData(14, R.drawable.ic_kotlin, "Television", Color(0xFF57CBDA), Color.Black), // Cyan
        QuizCategoryData(15, R.drawable.ic_kotlin, "Video Games", Color(0xFF676767), Color.White), // Dark Gray
        QuizCategoryData(16, R.drawable.ic_kotlin, "Board Games", Color(0xFFC5856F), Color.White), // Brown
        QuizCategoryData(17, R.drawable.ic_kotlin, "Science & Nature", Color(0xFF57B0D9), Color.White), // Light Blue
        QuizCategoryData(18, R.drawable.ic_kotlin, "Computers", Color(0xFF5A9EBE), Color.White), // Blue Gray
        QuizCategoryData(19, R.drawable.ic_kotlin, "Mathematics", Color(0xFFD9756C), Color.White), // Bright Red
        QuizCategoryData(20, R.drawable.ic_kotlin, "Mythology", Color(0xFF835ACC), Color.White), // Deep Purple
        QuizCategoryData(21, R.drawable.ic_kotlin, "Sports", Color(0xFFD79E4F), Color.White), // Orange
        QuizCategoryData(22, R.drawable.ic_kotlin, "Geography", Color(0xFF50B454), Color.White), // Green
        QuizCategoryData(23, R.drawable.ic_kotlin, "History", Color(0xFF79574C), Color.White), // Brown
        QuizCategoryData(24, R.drawable.ic_kotlin, "Politics", Color(0xFFE35656), Color.White), // Dark Red
        QuizCategoryData(25, R.drawable.ic_kotlin, "Art", Color(0xFFFF4081), Color.Black), // Pink
        QuizCategoryData(26, R.drawable.ic_kotlin, "Celebrities", Color(0xFFD2BD50), Color.Black), // Gold
        QuizCategoryData(27, R.drawable.ic_kotlin, "Animals", Color(0xFF439B47), Color.White), // Dark Green
        QuizCategoryData(28, R.drawable.ic_kotlin, "Vehicles", Color(0xFF5393D2), Color.White), // Deep Blue
        QuizCategoryData(29, R.drawable.ic_kotlin, "Comics", Color(0xFFC44F4F), Color.Black), // Light Red
        QuizCategoryData(30, R.drawable.ic_kotlin, "Gadgets", Color(0xFF9E9E9E), Color.White), // Gray
        QuizCategoryData(31, R.drawable.ic_kotlin, "Japanese Anime & Manga", Color(0xFFD0527D), Color.Black), // Pink
        QuizCategoryData(32, R.drawable.ic_kotlin, "Cartoon & Animations", Color(0xFFB2A14B), Color.Black), // Bright Yellow
        QuizCategoryData(-1, R.drawable.ic_kotlin, "Random", Color(0xFF8A54B9), Color.Black) // Bright Yellow
    )

}