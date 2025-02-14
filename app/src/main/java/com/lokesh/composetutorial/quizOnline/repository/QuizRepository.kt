package com.lokesh.composetutorial.quizOnline.repository

import com.lokesh.composetutorial.quizOnline.models.QuizResponse
import com.lokesh.composetutorial.quizOnline.network.QuizApiService
import retrofit2.Response
import javax.inject.Inject

class QuizRepository @Inject constructor(private val quizApiService: QuizApiService) {

    suspend fun getQuestions(categoryId: Int):Response<QuizResponse> {
        val res =  quizApiService.getQuestions(category = categoryId)
        return res
    }

    suspend fun getRandomQuestions():Response<QuizResponse> {
        val res =  quizApiService.getRandomQuestions()
        return res
    }
}
