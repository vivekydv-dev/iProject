package com.ssingh.iproject.model

data class ApiData(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)