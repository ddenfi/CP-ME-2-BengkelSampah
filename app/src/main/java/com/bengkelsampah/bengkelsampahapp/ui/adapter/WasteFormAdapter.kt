package com.bengkelsampah.bengkelsampahapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bengkelsampah.bengkelsampahapp.R
import com.bengkelsampah.bengkelsampahapp.databinding.ItemDaftarSampahBinding
import com.bengkelsampah.bengkelsampahapp.domain.model.WasteBoxModel
import com.bengkelsampah.bengkelsampahapp.utils.CurrencyNumberFormat
import kotlin.math.roundToInt

class WasteFormAdapter : ListAdapter<WasteBoxModel, WasteFormAdapter.WasteFormViewHolder>(
    object : DiffUtil.ItemCallback<WasteBoxModel>() {
        override fun areItemsTheSame(oldItem: WasteBoxModel, newItem: WasteBoxModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: WasteBoxModel, newItem: WasteBoxModel): Boolean {
            return oldItem == newItem
        }
    }
) {

    inner class WasteFormViewHolder(private val binding: ItemDaftarSampahBinding) :
        ViewHolder(binding.root) {
        private val tvWasteName by lazy { binding.tvWasteName }
        private val tvWasteAmount by lazy { binding.tvWasteAmount }
        private val tvWastePricePerUnit by lazy { binding.tvWastePricePerUnit }
        private val tvWasteSubtotal by lazy { binding.tvSubtotal }

        fun bind(waste: WasteBoxModel) {
            tvWasteName.text = waste.waste.name
            tvWasteAmount.text = itemView.context.getString(
                R.string.waste_weight,
                waste.amount,
                waste.waste.unit.abbreviation
            )
            tvWastePricePerUnit.text =
                CurrencyNumberFormat.convertToCurrencyFormat(waste.waste.pricePerUnit)
            tvWasteSubtotal.text =
                CurrencyNumberFormat.convertToCurrencyFormat(
                    WasteBoxModel.countSubtotal(
                        waste.waste.pricePerUnit,
                        waste.amount
                    ).roundToInt()
                )
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WasteFormViewHolder =
        WasteFormViewHolder(
            ItemDaftarSampahBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: WasteFormViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}