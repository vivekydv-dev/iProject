package com.ssingh.iproject

import com.ssingh.iproject.model.ApiData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("top-headlines")
    fun getArticle(@Query("sources") sources: String, @Query("apiKey") apiKey: String): Call<ApiData>
}