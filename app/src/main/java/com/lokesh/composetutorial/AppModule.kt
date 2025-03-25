package com.lokesh.composetutorial

import android.content.Context
import androidx.room.Room
import com.lokesh.composetutorial.extra.tweetApp.network.TweetApi
import com.lokesh.composetutorial.extra.tweetApp.repository.TweetRepository
import com.lokesh.composetutorial.quizOnline.database.QuizDatabase
import com.lokesh.composetutorial.quizOnline.database.QuizResultDao
import com.lokesh.composetutorial.quizOnline.repository.QuizHistoryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val TWEET_BASE_URL = "https://api.jsonbin.io/"

    @Singleton
    @Provides
    @Named("tweetRetrofit")
    fun providesRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl(TWEET_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providesTweetApi(@Named("tweetRetrofit") retrofit: Retrofit): TweetApi {
        return retrofit.create(TweetApi::class.java)
    }

    @Singleton
    @Provides
    fun providesTweetRepository(tweetApi: TweetApi): TweetRepository {
        return TweetRepository(tweetApi)
    }


    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): QuizDatabase {
        return Room.databaseBuilder(
            context,
            QuizDatabase::class.java,
            "quiz_database"
        ).build()
    }

    @Singleton
    @Provides
    fun provideQuizResultDao(database: QuizDatabase): QuizResultDao {
        return database.quizResultDao()
    }

    @Singleton
    @Provides
    fun providesQuizHistoryRepository(quizResultDao: QuizResultDao): QuizHistoryRepository {
        return QuizHistoryRepository(quizResultDao)

    }

}