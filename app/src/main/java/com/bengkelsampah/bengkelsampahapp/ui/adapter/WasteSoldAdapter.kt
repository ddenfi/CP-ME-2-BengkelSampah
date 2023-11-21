package com.bengkelsampah.bengkelsampahapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bengkelsampah.bengkelsampahapp.databinding.ItemWasteSoldBinding
import com.bengkelsampah.bengkelsampahapp.domain.model.WasteBoxModel

class WasteSoldAdapter : ListAdapter<WasteBoxModel, WasteSoldAdapter.WasteSoldViewHolder>(
    object : DiffUtil.ItemCallback<WasteBoxModel>() {
        override fun areItemsTheSame(oldItem: WasteBoxModel, newItem: WasteBoxModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: WasteBoxModel, newItem: WasteBoxModel): Boolean {
            return oldItem == newItem
        }
    }
) {

    inner class WasteSoldViewHolder(private val binding: ItemWasteSoldBinding) :
        ViewHolder(binding.root) {
        private val tvWasteName by lazy { binding.tvWasteName }
        private val tvWasteAmount by lazy { binding.tvWasteAmount }
        private val tvWasteUnit by lazy { binding.tvWasteUnit }
        private val tvWastePricePerUnit by lazy { binding.tvWastePricePerUnit }
        private val tvWastePrice by lazy { binding.tvWastePrice }

        fun bind(waste: WasteBoxModel) {
            tvWasteName.text = waste.waste.name
            tvWasteAmount.text = waste.amount.toString()
            tvWasteUnit.text = waste.waste.unit.name
            tvWastePricePerUnit.text = waste.waste.pricePerUnit.toString()
            tvWastePrice.text =
                WasteBoxModel.countSubtotal(waste.waste.pricePerUnit, waste.amount).toInt()
                    .toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WasteSoldViewHolder =
        WasteSoldViewHolder(
            ItemWasteSoldBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: WasteSoldViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}