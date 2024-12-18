package com.lokesh.composetutorial.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers


interface TweetApi {


    @Headers("X-Access-Key: \$2a\$10\$qo6XUEQRWop65St.iJiTv.kZJ7WmdcQELgTnDUHxnF8./EPP8UzgO")
    @GET("/v3/b/66d69f94e41b4d34e4297cd8?meta=false")
    suspend fun getTweetsByCategory(@Header("X-JSON-Path")category : String): Response<List<Tweet>>


    @GET("/v3/b/66d69f94e41b4d34e4297cd8?meta=false")
    suspend fun getAllData():Response<List<Tweet>>


    @Headers("X-JSON-Path:tweets..category")
    @GET("/v3/b/66d69f94e41b4d34e4297cd8?meta=false")
    suspend fun getCategories():Response<List<String>>
}