package com.lokesh.composetutorial.quizOnline.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface QuizResultDao {
    @Query("SELECT * FROM quiz_results")
    suspend fun getAllQuizResults(): List<QuizResult>

    @Query("DELETE FROM quiz_results")
    suspend fun deleteAllQuizResults()

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertIntoHistory(quizResult: QuizResult)

}