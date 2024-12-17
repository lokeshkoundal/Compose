package com.lokesh.composetutorial.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers


interface TweetApi {

    @GET("/v3/b/66d69f94e41b4d34e4297cd8?meta=false")
    suspend fun getTweetsByCategory(@Header("X-JSON-Path")category : String): Response<Tweets>


    @GET("/v3/b/66d69f94e41b4d34e4297cd8?meta=false")
    suspend fun getAllData():Response<Tweets>


    @Headers("X-JSON-Path:tweets..category")
    @GET("/v3/b/66d69f94e41b4d34e4297cd8?meta=false")
    suspend fun getCategories():Response<List<String>>
}