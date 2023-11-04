package com.bengkelsampah.bengkelsampahapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bengkelsampah.bengkelsampahapp.databinding.ItemWasteSoldBinding
import com.bengkelsampah.bengkelsampahapp.domain.model.DummyWaste

class WasteSoldAdapter : ListAdapter<DummyWaste, WasteSoldAdapter.WasteSoldViewHolder>(
    object : DiffUtil.ItemCallback<DummyWaste>() {
        override fun areItemsTheSame(oldItem: DummyWaste, newItem: DummyWaste): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: DummyWaste, newItem: DummyWaste): Boolean {
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

        fun bind(waste: DummyWaste) {
            tvWasteName.text = waste.name
            tvWasteAmount.text = waste.amount.toString()
            tvWasteUnit.text = waste.unit
            tvWastePricePerUnit.text = waste.pricePerUnit.toString()
            tvWastePrice.text = (waste.amount * waste.pricePerUnit).toInt().toString()
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