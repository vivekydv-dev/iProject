package com.ssingh.iproject

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ArticleDao {
    @Query("SELECT * FROM 'article_table'")
    fun getAllArticle(): List<ArticleEntity>

    @Query("DELETE FROM 'article_table'")
    fun deleteAllArticle()

    @Insert
    fun insertArticle(articleData: ArticleEntity)
}