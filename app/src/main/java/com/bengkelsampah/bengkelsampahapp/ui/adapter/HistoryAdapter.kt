package com.bengkelsampah.bengkelsampahapp.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bengkelsampah.bengkelsampahapp.R
import com.bengkelsampah.bengkelsampahapp.databinding.ItemHistoryBinding
import com.bengkelsampah.bengkelsampahapp.domain.model.DummyHistoryData
import com.bengkelsampah.bengkelsampahapp.ui.history.HistoryStatus

class HistoryAdapter : ListAdapter<DummyHistoryData, HistoryAdapter.HistoryViewHolder>(
    object : DiffUtil.ItemCallback<DummyHistoryData>() {
        override fun areItemsTheSame(
            oldItem: DummyHistoryData,
            newItem: DummyHistoryData
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: DummyHistoryData,
            newItem: DummyHistoryData
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

        fun bind(history: DummyHistoryData) {
            tvPickUpDate.text = history.pickUpDate
            tvPickUpAddress.text = history.address
            tvOrderDate.text = history.date
            tvStatus.text = history.status

            when (history.status) {
                HistoryStatus.MENUNGGU_KONFIRMASI.statusValue -> cardStatus.setCardBackgroundColor(
                    Color.parseColor(HistoryStatus.MENUNGGU_KONFIRMASI.color)
                )

                HistoryStatus.SELESAI.statusValue -> cardStatus.setCardBackgroundColor(
                    Color.parseColor(HistoryStatus.SELESAI.color)
                )

                HistoryStatus.DIPROSES.statusValue -> cardStatus.setCardBackgroundColor(
                    Color.parseColor(HistoryStatus.DIPROSES.color)
                )

                HistoryStatus.DIBATALKAN.statusValue -> cardStatus.setCardBackgroundColor(
                    Color.parseColor(HistoryStatus.DIBATALKAN.color)
                )
            }

            tvTotal.text = itemView.context.getString(R.string.total, history.total)
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