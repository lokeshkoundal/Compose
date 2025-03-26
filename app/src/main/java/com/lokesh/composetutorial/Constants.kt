package com.lokesh.composetutorial

import androidx.compose.ui.graphics.Color
import com.lokesh.composetutorial.quizOnline.models.QuizCategoryData

object Constants {
    const val TOTAL_QUIZ_QUESN = 10

    val quizCategories by lazy {
        listOf(
            QuizCategoryData(9, R.drawable.ic_gen_knowledge, "General Knowledge", Color(0xFF56A7EC), Color.White),
            QuizCategoryData(10, R.drawable.ic_books, "Books", Color(0xFFE55858), Color.White),
            QuizCategoryData(11, R.drawable.ic_film, "Film", Color(0xFF5CD561), Color.Black),
            QuizCategoryData(12, R.drawable.ic_music, "Music", Color(0xFFE0D258), Color.Black),
            QuizCategoryData(13, R.drawable.ic_theatre, "Musicals & Theatres", Color(0xFFC958DE), Color.White),
            QuizCategoryData(14, R.drawable.ic_tv, "Television", Color(0xFF57CBDA), Color.Black),
            QuizCategoryData(15, R.drawable.ic_game, "Video Games", Color(0xFF676767), Color.White),
            QuizCategoryData(16, R.drawable.ic_board_game, "Board Games", Color(0xFFC5856F), Color.White),
            QuizCategoryData(17, R.drawable.ic_science, "Science & Nature", Color(0xFF57B0D9), Color.White),
            QuizCategoryData(18, R.drawable.ic_computer, "Computers", Color(0xFF5A9EBE), Color.White),
            QuizCategoryData(19, R.drawable.ic_maths, "Mathematics", Color(0xFFD9756C), Color.White),
            QuizCategoryData(20, R.drawable.ic_mythology, "Mythology", Color(0xFF835ACC), Color.White),
            QuizCategoryData(21, R.drawable.ic_sports, "Sports", Color(0xFFD79E4F), Color.White),
            QuizCategoryData(22, R.drawable.ic_geography, "Geography", Color(0xFF50B454), Color.White),
            QuizCategoryData(23, R.drawable.ic_history, "History", Color(0xFF79574C), Color.White),
            QuizCategoryData(24, R.drawable.ic_politics, "Politics", Color(0xFFE35656), Color.White),
            QuizCategoryData(25, R.drawable.ic_art, "Art", Color(0xFFFF4081), Color.Black),
            QuizCategoryData(26, R.drawable.ic_celebrity, "Celebrities", Color(0xFFD2BD50), Color.Black),
            QuizCategoryData(27, R.drawable.ic_animals, "Animals", Color(0xFF439B47), Color.White),
            QuizCategoryData(28, R.drawable.ic_vehicle, "Vehicles", Color(0xFF5393D2), Color.White),
            QuizCategoryData(29, R.drawable.ic_comics, "Comics", Color(0xFFC44F4F), Color.Black),
            QuizCategoryData(30, R.drawable.ic_gadgets, "Gadgets", Color(0xFF9E9E9E), Color.White),
            QuizCategoryData(31, R.drawable.ic_anime, "Japanese Anime & Manga", Color(0xFFD0527D), Color.Black),
            QuizCategoryData(32, R.drawable.ic_cartoon, "Cartoon & Animations", Color(0xFFB2A14B), Color.Black),
            QuizCategoryData(-1, R.drawable.ic_random, "Random", Color(0xFF8A54B9), Color.Black)
        )
    }

}