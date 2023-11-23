package com.bengkelsampah.bengkelsampahapp.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bengkelsampah.bengkelsampahapp.R
import com.bengkelsampah.bengkelsampahapp.databinding.ItemDriverPickupPeekorderBinding
import com.bengkelsampah.bengkelsampahapp.domain.model.WasteBoxModel
import com.bengkelsampah.bengkelsampahapp.domain.model.WasteOrderModel
import okhttp3.internal.wait

class PickupOrderPeekAdapter(private val wasteBox: List<WasteBoxModel>) :
    RecyclerView.Adapter<PickupOrderPeekAdapter.PeekAdapter>() {
    inner class PeekAdapter(val binding: ItemDriverPickupPeekorderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: WasteBoxModel) {
            binding.apply {
                tvPeekOrderName.text = item.waste.name
                tvPeekOrderWeight.text = binding.root.context.getString(
                    R.string.waste_weight,
                    item.amount,
                    item.waste.unit
                )
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PickupOrderPeekAdapter.PeekAdapter {
        val binding = ItemDriverPickupPeekorderBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PeekAdapter(binding)
    }

    override fun onBindViewHolder(holder: PickupOrderPeekAdapter.PeekAdapter, position: Int) {
        holder.bind(wasteBox[position])
    }

    override fun getItemCount(): Int = wasteBox.size


}