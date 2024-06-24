package com.ssingh.iproject

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssingh.iproject.databinding.ActivityMainBinding
import com.ssingh.iproject.model.ApiData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var apiInterface: ApiInterface
    lateinit var db: AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        apiInterface = Utils.retrofitBuilder().create(ApiInterface::class.java)
        db = Utils.getDB(this)

        if (Utils.checkForInternet(this)) {
            Toast.makeText(this, "Connected", Toast.LENGTH_SHORT).show()
            getArticleData()
        } else {
            Toast.makeText(this, "Disconnected", Toast.LENGTH_SHORT).show()
        }
        val allArticle = db.articleDao.getAllArticle()
        val recycleView = binding.articleRecycleView
        recycleView.layoutManager = LinearLayoutManager(this)
        recycleView.adapter = ArticleAdapter(this, allArticle)
    }

    private fun getArticleData() {
        val call = apiInterface.getArticle()
        call.enqueue(object : Callback<ApiData?> {
            override fun onResponse(p0: Call<ApiData?>, response: Response<ApiData?>) {
                db.articleDao.deleteAllArticle()
                val articles = response.body()?.articles
                if (articles != null) {
                    for (article in articles) {
                        db.articleDao.insertArticle(
                            ArticleEntity(
                                content = article.content,
                                author = article.author,
                                publishedAt = article.publishedAt,
//                            source = art.source,
                                urlToImage = article.urlToImage,
                                description = article.description,
                                title = article.title,
                                url = article.url
                            )
                        )
                    }
                }
            }

            override fun onFailure(p0: Call<ApiData?>, p1: Throwable) {
                Toast.makeText(this@MainActivity, "Failed to call api", Toast.LENGTH_SHORT).show()
            }
        })
    }
}