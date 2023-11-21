package com.bengkelsampah.bengkelsampahapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bengkelsampah.bengkelsampahapp.databinding.ItemNewBinding
import com.bengkelsampah.bengkelsampahapp.domain.model.NewsResourceModel
import com.bengkelsampah.bengkelsampahapp.domain.model.WasteOrderModel
import com.bumptech.glide.Glide

class NewsAdapter : ListAdapter<NewsResourceModel, NewsAdapter.NewsViewHolder>(
    object : DiffUtil.ItemCallback<NewsResourceModel>() {
        override fun areItemsTheSame(
            oldItem: NewsResourceModel,
            newItem: NewsResourceModel
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: NewsResourceModel,
            newItem: NewsResourceModel
        ): Boolean {
            return oldItem == newItem
        }
    }
) {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class NewsViewHolder(val binding: ItemNewBinding) : ViewHolder(binding.root) {
        fun bind(item: NewsResourceModel) {
            Glide
                .with(binding.root.context)
                .load(item.urlToImage)
                .into(binding.ivNewPhoto)
            binding.tvNewTitle.text = item.title
            binding.tvNewTimestamp.text = item.publishedAt
            binding.tvNewContent.text = item.content

            binding.root.setOnClickListener {
                onItemClickCallback.onItemClicked(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter.NewsViewHolder {
        val binding = ItemNewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsAdapter.NewsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: NewsResourceModel)
    }

}