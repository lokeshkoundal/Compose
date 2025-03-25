package com.lokesh.composetutorial.quizOnline.repository

import com.lokesh.composetutorial.quizOnline.database.QuizResult
import com.lokesh.composetutorial.quizOnline.database.QuizResultDao
import javax.inject.Inject


class QuizHistoryRepository @Inject constructor(private val quizResultDao: QuizResultDao) {

    suspend fun insert(result: QuizResult) {
        quizResultDao.insertIntoHistory(result)
    }
    suspend fun getAllQuizResults(): List<QuizResult> {
        return quizResultDao.getAllQuizResults()
    }
    suspend fun deleteAllQuizResults() {
        quizResultDao.deleteAllQuizResults()
    }

}