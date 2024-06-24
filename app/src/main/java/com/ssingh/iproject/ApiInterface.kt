package com.ssingh.iproject

import com.ssingh.iproject.model.ApiData
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("top-headlines?sources=techcrunch&apiKey=204b409bd3f74b2a902fd73cb750a49b")
    fun getArticle(): Call<ApiData>
}