package com.ssingh.iproject

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class ArticleAdapter(val context: Activity, private val articleList: List<ArticleEntity>) :
    RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {
    class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val hTitle = itemView.findViewById<TextView>(R.id.title)
        val hImage = itemView.findViewById<ImageView>(R.id.image)
        val hAuthor = itemView.findViewById<TextView>(R.id.author)
        val hPublishedAt = itemView.findViewById<TextView>(R.id.publishedAt)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.row_item, parent, false)
        return ArticleViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return articleList.size
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val currentItem = articleList[position]
//        holder.hImage.setImageResource(currentItem.urlToImage)
        Picasso.get().load(currentItem.urlToImage).into(holder.hImage)
        holder.hTitle.text = currentItem.title
        holder.hAuthor.text = currentItem.author
        holder.hPublishedAt.text = currentItem.publishedAt
    }
}