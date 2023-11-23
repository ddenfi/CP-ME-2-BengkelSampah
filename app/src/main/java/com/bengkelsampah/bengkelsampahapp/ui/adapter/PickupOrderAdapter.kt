package com.bengkelsampah.bengkelsampahapp.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bengkelsampah.bengkelsampahapp.databinding.ItemPickupBinding
import com.bengkelsampah.bengkelsampahapp.domain.model.WasteOrderModel
import com.bengkelsampah.bengkelsampahapp.utils.DateHelper

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
            with(binding) {
                tvPickupCustomerName.text = item.consumerName
                tvPickupStatus.text = item.status.statusName
                tvPickupDistance.text = "3Km"
                tvPickupWeight.text = "3 Kg"
                tvPickupVehicle.text = "Pick-up"
                tvPickupDate.text = DateHelper.get3DaysLaterDate()
            }


            val peekOrderAdapter = PickupOrderPeekAdapter(item.wasteBox.subList(0, 3))
            binding.rvItemPickupPeekOrder.layoutManager = LinearLayoutManager(binding.root.context)
            binding.rvItemPickupPeekOrder.adapter = peekOrderAdapter
            if (item.wasteBox.size > 3) {
                binding.ivItemPickupMore.visibility = View.VISIBLE
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