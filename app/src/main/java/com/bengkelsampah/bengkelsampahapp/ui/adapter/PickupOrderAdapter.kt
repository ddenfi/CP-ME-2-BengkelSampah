package com.bengkelsampah.bengkelsampahapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bengkelsampah.bengkelsampahapp.databinding.ItemPickupBinding
import com.bengkelsampah.bengkelsampahapp.domain.model.WasteOrderModel

class PickupOrderAdapter(
    private var listPickupOrder: List<WasteOrderModel> = listOf()
) : RecyclerView.Adapter<PickupOrderAdapter.PickupOrderViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun submitList(newList: List<WasteOrderModel>) {
        listPickupOrder = newList
        notifyDataSetChanged()
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class PickupOrderViewHolder(private val binding: ItemPickupBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: WasteOrderModel) {
            with(binding){
                tvPickupCustomerName.text = item.consumerName
                tvPickupStatus.text = item.status.statusName
                tvPickupDistance.text = "3Km"
                tvPickupWeight.text = "3 Kg"
                tvPickupVehicle.text = "Pick-up"
            }

            binding.root.setOnClickListener {
                onItemClickCallback.onItemClicked(item)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PickupOrderAdapter.PickupOrderViewHolder {
        val binding = ItemPickupBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PickupOrderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PickupOrderAdapter.PickupOrderViewHolder, position: Int) {
        holder.bind(listPickupOrder[position])
    }

    override fun getItemCount(): Int = listPickupOrder.size

    interface OnItemClickCallback {
        fun onItemClicked(data: WasteOrderModel)
    }

}