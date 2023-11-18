package com.bengkelsampah.bengkelsampahapp.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bengkelsampah.bengkelsampahapp.R
import com.bengkelsampah.bengkelsampahapp.databinding.ItemWasteBoxBinding
import com.bengkelsampah.bengkelsampahapp.domain.model.WasteSoldModel

class WasteBoxAdapter : ListAdapter<WasteSoldModel, WasteBoxAdapter.WasteBoxViewHolder>(
    object : DiffUtil.ItemCallback<WasteSoldModel>() {
        override fun areItemsTheSame(oldItem: WasteSoldModel, newItem: WasteSoldModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: WasteSoldModel, newItem: WasteSoldModel): Boolean {
            return oldItem == newItem
        }
    }
) {
    inner class WasteBoxViewHolder(val binding: ItemWasteBoxBinding) : ViewHolder(binding.root) {
        private val tvWasteName by lazy { binding.tvWasteName }
        private val tvWastePrice by lazy { binding.tvWastePrice }
        private val tvWastePricePerUnit by lazy { binding.tvWastePricePerUnit }
        private val tvWasteWeight by lazy { binding.tvWasteWeight }
        private val chipAdd by lazy { binding.chipAdd }
        private val chipMinus by lazy { binding.chipMinus }

        @SuppressLint("StringFormatMatches")
        fun bind(wasteSold: WasteSoldModel) {
            tvWasteName.text = wasteSold.waste.name
            tvWastePrice.text = itemView.context.getString(
                R.string.price_value,
                WasteSoldModel.countSubtotal(wasteSold.waste.pricePerUnit, wasteSold.amount)
            )
            tvWastePricePerUnit.text = itemView.context.getString(
                R.string.price_per_unit_value,
                wasteSold.waste.pricePerUnit,
                wasteSold.waste.unit
            )
            tvWasteWeight.text = wasteSold.amount.toString()

            chipAdd.setOnClickListener { addWeight() }
            chipMinus.setOnClickListener { subtractWeight() }
        }

        private fun addWeight() {
            val newValue = tvWasteWeight.text.toString().toInt() + 1
            tvWasteWeight.text = newValue.toString()
        }

        private fun subtractWeight() {
            val newValue = tvWasteWeight.text.toString().toInt() - 1
            tvWasteWeight.text = newValue.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WasteBoxViewHolder =
        WasteBoxViewHolder(
            ItemWasteBoxBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: WasteBoxViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}