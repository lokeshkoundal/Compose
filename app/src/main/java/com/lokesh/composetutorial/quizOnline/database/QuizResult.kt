package com.lokesh.composetutorial.quizOnline.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quiz_results")
data class QuizResult(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val correctAnswers: Int,
    val wrongAnswers: Int,
    val categoryId: Int
)
