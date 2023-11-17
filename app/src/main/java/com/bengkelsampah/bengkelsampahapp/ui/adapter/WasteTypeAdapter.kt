package com.bengkelsampah.bengkelsampahapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bengkelsampah.bengkelsampahapp.databinding.ItemWasteTypeBinding
import com.bengkelsampah.bengkelsampahapp.domain.model.WasteModel

class WasteTypeAdapter(private val onClick: (WasteModel) -> Unit) :
    ListAdapter<WasteModel, WasteTypeAdapter.WasteTypeViewHolder>(
        object : DiffUtil.ItemCallback<WasteModel>() {
            override fun areItemsTheSame(oldItem: WasteModel, newItem: WasteModel): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: WasteModel, newItem: WasteModel): Boolean {
                return oldItem == newItem
            }
        }
    ) {

    inner class WasteTypeViewHolder(val binding: ItemWasteTypeBinding) : ViewHolder(binding.root) {
        private val tvWasteName by lazy { binding.tvWasteName }

        fun bind(waste: WasteModel) {
            tvWasteName.text = waste.name
            itemView.setOnClickListener {
                onClick(waste)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WasteTypeViewHolder =
        WasteTypeViewHolder(
            ItemWasteTypeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: WasteTypeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}