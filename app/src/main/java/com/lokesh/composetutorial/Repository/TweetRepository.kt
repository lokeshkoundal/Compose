package com.lokesh.composetutorial.Repository

import com.lokesh.composetutorial.network.Tweet
import com.lokesh.composetutorial.network.TweetApi
import com.lokesh.composetutorial.network.Tweets
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TweetRepository @Inject constructor(private val tweetApi: TweetApi){

    private val _categories = MutableStateFlow<List<String>>(emptyList())
    val categories : StateFlow<List<String>> = _categories


    private val _tweets = MutableStateFlow<Tweets>(Tweets(emptyList()))
    val tweets:StateFlow<Tweets> = _tweets


    suspend fun getCategories(){
        val res = tweetApi.getCategories()
        if(res.isSuccessful && res.body() != null){
            _categories.emit(res.body()!!)
        }
    }

    suspend fun getTweets(category: String){
        val res = tweetApi.getTweetsByCategory(category)
        if(res.isSuccessful && res.body() != null){
            _tweets.emit(res.body()!!)
        }
    }
}