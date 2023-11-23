package com.bengkelsampah.bengkelsampahapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bengkelsampah.bengkelsampahapp.databinding.ItemWasteListBinding
import com.bengkelsampah.bengkelsampahapp.domain.model.WasteBoxModel
import com.bengkelsampah.bengkelsampahapp.utils.CurrencyNumberFormat
import kotlin.math.roundToInt

class WasteListAdapter : ListAdapter<WasteBoxModel, WasteListAdapter.OrderWasteViewHolder>(
    object : DiffUtil.ItemCallback<WasteBoxModel>() {
        override fun areItemsTheSame(
            oldItem: WasteBoxModel,
            newItem: WasteBoxModel
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: WasteBoxModel,
            newItem: WasteBoxModel
        ): Boolean {
            return oldItem == newItem
        }
    }
) {
    inner class OrderWasteViewHolder(private val binding: ItemWasteListBinding) :
        ViewHolder(binding.root) {
        fun bind(item: WasteBoxModel) {
            with(binding) {
                itemListWasteName.text = item.waste.name
                itemListWasteAmount.text = item.amount.toString()
                itemListWasteSum.text = CurrencyNumberFormat.convertToCurrencyFormat(
                    WasteBoxModel.countSubtotal(item.waste.pricePerUnit, item.amount).roundToInt()
                )
                itemListWastePricePerUnit.text =
                    CurrencyNumberFormat.convertToCurrencyFormat(item.waste.pricePerUnit)
            }
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