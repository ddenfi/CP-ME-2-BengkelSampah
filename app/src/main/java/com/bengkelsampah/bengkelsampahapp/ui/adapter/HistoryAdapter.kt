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

class HistoryAdapter(private val onClick: (HistoryModel) -> Unit) :
    ListAdapter<HistoryModel, HistoryAdapter.HistoryViewHolder>(
        object : DiffUtil.ItemCallback<HistoryModel>() {
            override fun areItemsTheSame(
                oldItem: HistoryModel,
                newItem: HistoryModel
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: HistoryModel,
                newItem: HistoryModel
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

        fun bind(history: HistoryModel) {
            tvPickUpDate.text = history.pickUpDate
            tvPickUpAddress.text = history.address
            tvOrderDate.text = history.date
            tvStatus.text = history.status

            when (history.status) {
                OrderStatus.WAIT_CONFIRMATION.statusName -> cardStatus.setCardBackgroundColor(
                    Color.parseColor(OrderStatus.WAIT_CONFIRMATION.color)
                )

                OrderStatus.DONE.statusName -> cardStatus.setCardBackgroundColor(
                    Color.parseColor(OrderStatus.DONE.color)
                )

                OrderStatus.PROCESSED.statusName -> cardStatus.setCardBackgroundColor(
                    Color.parseColor(OrderStatus.PROCESSED.color)
                )

                OrderStatus.CANCELLED.statusName -> cardStatus.setCardBackgroundColor(
                    Color.parseColor(OrderStatus.CANCELLED.color)
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