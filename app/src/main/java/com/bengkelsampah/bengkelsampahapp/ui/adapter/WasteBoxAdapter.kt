package com.bengkelsampah.bengkelsampahapp.ui.adapter

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bengkelsampah.bengkelsampahapp.R
import com.bengkelsampah.bengkelsampahapp.databinding.ItemWasteBoxBinding
import com.bengkelsampah.bengkelsampahapp.domain.model.WasteBoxModel
import com.bengkelsampah.bengkelsampahapp.domain.model.WasteModel
import kotlin.math.roundToInt

class WasteBoxAdapter(
    private val onClickAdd: (WasteModel, Double) -> Unit,
    private val onClickSubtract: (WasteModel, Double) -> Unit
) : ListAdapter<WasteBoxModel, WasteBoxAdapter.WasteBoxViewHolder>(
    object : DiffUtil.ItemCallback<WasteBoxModel>() {
        override fun areItemsTheSame(oldItem: WasteBoxModel, newItem: WasteBoxModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: WasteBoxModel, newItem: WasteBoxModel): Boolean {
            return oldItem == newItem
        }
    }
) {
    inner class WasteBoxViewHolder(val binding: ItemWasteBoxBinding) : ViewHolder(binding.root) {
        private val tvWasteName by lazy { binding.tvWasteName }
        private val tvWastePrice by lazy { binding.tvWastePrice }
        private val tvWastePricePerUnit by lazy { binding.tvWastePricePerUnit }
        private val edWasteWeight by lazy { binding.edWasteWeight }
        private val chipAdd by lazy { binding.chipAdd }
        private val chipMinus by lazy { binding.chipMinus }

        fun bind(wasteSold: WasteBoxModel) {
            tvWasteName.text = wasteSold.waste.name
            tvWastePrice.text = itemView.context.getString(
                R.string.price_value,
                WasteBoxModel.countSubtotal(wasteSold.waste.pricePerUnit, wasteSold.amount)
                    .roundToInt()
            )
            tvWastePricePerUnit.text = itemView.context.getString(
                R.string.price_per_unit_value,
                wasteSold.waste.pricePerUnit.toString(),
                wasteSold.waste.unit
            )
            edWasteWeight.setText(wasteSold.amount.toString())

            edWasteWeight.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun afterTextChanged(newText: Editable?) {
                    if (newText.toString().isNotEmpty()) {
                        edWasteWeight.error = null
                    } else {
                        edWasteWeight.error = itemView.context.getString(R.string.weight_error)
                    }
                }
            })

            chipAdd.setOnClickListener { onClickAdd(wasteSold.waste, wasteSold.amount) }
            chipMinus.setOnClickListener { onClickSubtract(wasteSold.waste, wasteSold.amount) }
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