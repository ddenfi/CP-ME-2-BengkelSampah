package com.bengkelsampah.bengkelsampahapp.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bengkelsampah.bengkelsampahapp.R
import com.bengkelsampah.bengkelsampahapp.databinding.ItemHistoryBinding
import com.bengkelsampah.bengkelsampahapp.domain.model.HistoryModel
import com.bengkelsampah.bengkelsampahapp.domain.model.OrderStatus
import com.bengkelsampah.bengkelsampahapp.domain.model.WasteOrderModel

class HistoryAdapter(private val onClick: (WasteOrderModel) -> Unit) :
    ListAdapter<WasteOrderModel, HistoryAdapter.HistoryViewHolder>(
        object : DiffUtil.ItemCallback<WasteOrderModel>() {
            override fun areItemsTheSame(
                oldItem: WasteOrderModel,
                newItem: WasteOrderModel
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: WasteOrderModel,
                newItem: WasteOrderModel
            ): Boolean {
                return oldItem == newItem
            }
        }
    ) {

    inner class HistoryViewHolder(private val binding: ItemHistoryBinding) :
        ViewHolder(binding.root) {
        private val tvPickUpDate by lazy { binding.tvPickUpDate }
        private val tvPickUpAddress by lazy { binding.tvPickUpAddress }
        private val tvOrderDate by lazy { binding.tvOrderDate }
        private val tvStatus by lazy { binding.tvStatus }
        private val tvTotal by lazy { binding.tvTotal }
        private val cardStatus by lazy { binding.cardStatus }

        fun bind(history: WasteOrderModel) {
            tvPickUpDate.text = history.pickUpDate
            tvPickUpAddress.text = history.address
            tvOrderDate.text = history.date
            tvStatus.text = history.status.statusName

            when (history.status) {
                OrderStatus.WAIT_CONFIRMATION -> cardStatus.setCardBackgroundColor(
                    Color.parseColor(OrderStatus.WAIT_CONFIRMATION.color)
                )

                OrderStatus.DONE-> cardStatus.setCardBackgroundColor(
                    Color.parseColor(OrderStatus.DONE.color)
                )

                OrderStatus.PROCESSED -> cardStatus.setCardBackgroundColor(
                    Color.parseColor(OrderStatus.PROCESSED.color)
                )

                OrderStatus.CANCELLED -> cardStatus.setCardBackgroundColor(
                    Color.parseColor(OrderStatus.CANCELLED.color)
                )

                OrderStatus.PICKING_UP -> cardStatus.setCardBackgroundColor(
                    Color.parseColor(OrderStatus.PICKING_UP.color)
                )
            }

            tvTotal.text = itemView.context.getString(R.string.total, history.total)

            itemView.setOnClickListener {
                onClick(history)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder =
        HistoryViewHolder(
            ItemHistoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}