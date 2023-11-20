package com.bengkelsampah.bengkelsampahapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bengkelsampah.bengkelsampahapp.R
import com.bengkelsampah.bengkelsampahapp.databinding.ItemMoneybagHistoryBinding
import com.bengkelsampah.bengkelsampahapp.domain.model.HistoryModel
import com.bengkelsampah.bengkelsampahapp.domain.model.MoneybagHistoryModel

class MoneybagHistoryAdapter :
    ListAdapter<MoneybagHistoryModel, MoneybagHistoryAdapter.MoneybagHistoryViewHolder>(
        object : DiffUtil.ItemCallback<MoneybagHistoryModel>() {
            override fun areItemsTheSame(
                oldItem: MoneybagHistoryModel,
                newItem: MoneybagHistoryModel
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: MoneybagHistoryModel,
                newItem: MoneybagHistoryModel
            ): Boolean {
                return oldItem == newItem
            }
        }
    ) {

    inner class MoneybagHistoryViewHolder(val binding: ItemMoneybagHistoryBinding) :
        ViewHolder(binding.root) {
        fun bind(item: MoneybagHistoryModel) {
            binding.tvItemHistoryMoneybagTitle.text = item.title
            binding.tvItemHistoryMoneybagPrice.text =
                binding.root.context.getString(R.string.idr, item.price)
            binding.idItemHistoryHistoryMoneybagDate.text = item.date
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MoneybagHistoryAdapter.MoneybagHistoryViewHolder {
        val binding =
            ItemMoneybagHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoneybagHistoryViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: MoneybagHistoryAdapter.MoneybagHistoryViewHolder,
        position: Int
    ) {
        holder.bind(getItem(position))
    }
}