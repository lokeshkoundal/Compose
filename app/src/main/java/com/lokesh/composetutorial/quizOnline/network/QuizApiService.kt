package com.lokesh.composetutorial.quizOnline.network

import com.lokesh.composetutorial.Constants
import com.lokesh.composetutorial.quizOnline.models.QuizResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface QuizApiService {

    @GET("/api.php")
    suspend fun getQuestions(@Query("category") category: Int,
                             @Query("amount") amount: Int = Constants.TOTAL_QUIZ_QUESN,
                             @Query("type") type : String = "multiple"): Response<QuizResponse>

    @GET("/api.php")
    suspend fun getRandomQuestions(
        @Query("amount") amount: Int = Constants.TOTAL_QUIZ_QUESN,
        @Query("type") type : String = "multiple"
    ):Response<QuizResponse>
}