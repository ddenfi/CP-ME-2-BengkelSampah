package com.bengkelsampah.bengkelsampahapp.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bengkelsampah.bengkelsampahapp.R
import com.bengkelsampah.bengkelsampahapp.databinding.ItemHistoryDriverBinding
import com.bengkelsampah.bengkelsampahapp.domain.model.OrderStatus.*
import com.bengkelsampah.bengkelsampahapp.domain.model.WasteOrderModel
import com.bengkelsampah.bengkelsampahapp.utils.CurrencyNumberFormat

class HistoryDriverAdapter(private val onClick: (WasteOrderModel) -> Unit) :
    ListAdapter<WasteOrderModel, HistoryDriverAdapter.HistoryViewHolder>(
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

    inner class HistoryViewHolder(private val binding: ItemHistoryDriverBinding) :
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
                WAIT_CONFIRMATION -> cardStatus.setCardBackgroundColor(
                    Color.parseColor(WAIT_CONFIRMATION.color)
                )

                DONE -> cardStatus.setCardBackgroundColor(
                    Color.parseColor(DONE.color)
                )

                PROCESSED -> cardStatus.setCardBackgroundColor(
                    Color.parseColor(PROCESSED.color)
                )

                CANCELLED -> cardStatus.setCardBackgroundColor(
                    Color.parseColor(CANCELLED.color)
                )

                PICKING_UP -> cardStatus.setCardBackgroundColor(
                    Color.parseColor(PICKING_UP.color)
                )
            }

            tvTotal.text = itemView.context.getString(
                R.string.total,
                CurrencyNumberFormat.convertToCurrencyFormat(history.total)
            )

            itemView.setOnClickListener {
                onClick(history)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder =
        HistoryViewHolder(
            ItemHistoryDriverBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}