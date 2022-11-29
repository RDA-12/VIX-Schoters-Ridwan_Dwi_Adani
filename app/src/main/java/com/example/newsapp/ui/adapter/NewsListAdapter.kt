package com.example.newsapp.ui.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.databinding.NewsItemListBinding
import com.example.newsapp.domain.models.NewsModel
import com.facebook.drawee.drawable.ScalingUtils

class NewsListAdapter(
    private val onItemClickListener: (NewsModel) -> Unit,
) : ListAdapter<NewsModel, NewsListAdapter.NewsListViewHolder>(DiffCallback) {

    object DiffCallback : DiffUtil.ItemCallback<NewsModel>() {
        override fun areItemsTheSame(oldItem: NewsModel, newItem: NewsModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: NewsModel, newItem: NewsModel): Boolean {
            return oldItem.content == newItem.content
        }

    }

    class NewsListViewHolder(
        private val binding: NewsItemListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(news: NewsModel) {
            binding.apply {
                tvAuthor.setText(news.author)
                tvDescription.setText(news.description)
                tvPublishedAt.setText(news.publishedAt)
                ivNews.apply {
                    setImageURI(news.imageUrl, null)
                    hierarchy.setFailureImage(
                        R.drawable.ic_broken_image,
                        ScalingUtils.ScaleType.FIT_CENTER
                    )
                    hierarchy.setPlaceholderImage(
                        R.drawable.ic_image,
                        ScalingUtils.ScaleType.FIT_CENTER
                    )
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsListViewHolder {
        return NewsListViewHolder(
            NewsItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: NewsListViewHolder, position: Int) {
        val news = getItem(position)
        holder.bind(news)
        holder.itemView.setOnClickListener {
            onItemClickListener(news)
        }
    }

}