package com.chaitanya.newsapp

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsAdapter(private val onClick : (News) -> Unit, private val btnClick: (News) -> Unit) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private val newsList = ArrayList<News>()

    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleView: TextView = itemView.findViewById<TextView>(R.id.textView)
        val image: ImageView = itemView.findViewById(R.id.imageView)
        private val share: Button = itemView.findViewById<Button>(R.id.shareButton)
        private val layout: ConstraintLayout = itemView.findViewById<ConstraintLayout>(R.id.layout)

        fun onBind(currentItem: News, onClick: (News) -> Unit, btnClick: (News) -> Unit) {
            image.setOnClickListener {
                onClick(currentItem)
            }
            titleView.setOnClickListener {
                onClick(currentItem)
            }
            share.setOnClickListener {
                btnClick(currentItem)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem = newsList[position]
        holder.onBind(currentItem, onClick, btnClick)
        holder.titleView.text = currentItem.title
        Glide.with(holder.itemView.context).load(currentItem.imgUrl).into(holder.image)
    }

    override fun getItemCount(): Int {
        return newsList.count()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateNews(updatedNews: ArrayList<News>) {
        newsList.clear()
        newsList.addAll(updatedNews)
        notifyDataSetChanged()
    }
}