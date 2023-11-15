package com.bengkelsampah.bengkelsampahapp.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bengkelsampah.bengkelsampahapp.databinding.ItemWasteListBinding
import com.bengkelsampah.bengkelsampahapp.domain.model.WasteModel

class WasteListAdapter : ListAdapter<WasteModel, WasteListAdapter.OrderWasteViewHolder>(
    object : DiffUtil.ItemCallback<WasteModel>() {
        override fun areItemsTheSame(
            oldItem: WasteModel,
            newItem: WasteModel
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: WasteModel,
            newItem: WasteModel
        ): Boolean {
            return oldItem == newItem
        }
    }
) {
    inner class OrderWasteViewHolder(private val binding: ItemWasteListBinding) :
        ViewHolder(binding.root) {
        fun bind(item: WasteModel) {
            with(binding) {
                itemListWasteName.text = item.name
                itemListWasteAmount.text = item.amount.toString()
                itemListWasteSum.text = "2000"
                itemListWastePricePerUnit.text = item.pricePerUnit.toString()
            }
            Log.d("Adapter", "bind: Called")
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OrderWasteViewHolder {
        val binding =
            ItemWasteListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OrderWasteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrderWasteViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}