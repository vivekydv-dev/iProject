package com.ssingh.iproject

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "article_table")
data class ArticleEntity(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
//    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String,
    @PrimaryKey(autoGenerate = true)
    val uid: Int = 0,
)
