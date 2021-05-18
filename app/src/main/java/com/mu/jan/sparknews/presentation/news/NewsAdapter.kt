package com.mu.jan.sparknews.presentation.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mu.jan.sparknews.data.model.ArticleResponse
import com.mu.jan.sparknews.databinding.SingleArticleBinding

class NewsAdapter: ListAdapter<ArticleResponse, NewsAdapter.MyViewHolder>(MyDiffUtil) {
    inner class MyViewHolder(private val binding: SingleArticleBinding)
        : RecyclerView.ViewHolder(binding.root){
            fun bind(item: ArticleResponse){
                binding.article = item
                binding.executePendingBindings()
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            SingleArticleBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    object MyDiffUtil: DiffUtil.ItemCallback<ArticleResponse>() {
        override fun areItemsTheSame(oldItem: ArticleResponse, newItem: ArticleResponse): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: ArticleResponse,
            newItem: ArticleResponse
        ): Boolean {
            return oldItem == newItem
        }

    }
}