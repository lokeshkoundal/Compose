package com.lokesh.composetutorial.quizOnline.di

import com.lokesh.composetutorial.quizOnline.network.QuizApiService
import com.lokesh.composetutorial.quizOnline.repository.QuizRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class QuizModule {

    private val QUIZ_BASE_URL = "https://opentdb.com/"

    @Singleton
    @Provides
    fun ProvidesQuizRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(QUIZ_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providesQuizApi(quizRetrofit: Retrofit): QuizApiService {
        return quizRetrofit.create(QuizApiService::class.java)
    }

    @Singleton
    @Provides
    fun ProvidesQuizRepository(quizApiService: QuizApiService) : QuizRepository{
        return QuizRepository(quizApiService)

    }




}