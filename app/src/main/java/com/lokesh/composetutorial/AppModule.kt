package com.lokesh.composetutorial

import com.lokesh.composetutorial.extra.tweetApp.network.TweetApi
import com.lokesh.composetutorial.extra.tweetApp.repository.TweetRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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


}