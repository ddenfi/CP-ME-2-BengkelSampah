package com.bengkelsampah.bengkelsampahapp.ui.history

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bengkelsampah.bengkelsampahapp.R
import com.bengkelsampah.bengkelsampahapp.databinding.ItemHistoryBinding

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

            when (history.status.uppercase()) {
                "MENUNGGU KONFIRMASI" -> cardStatus.setCardBackgroundColor(Color.parseColor("#999797"))
                "SELESAI" -> cardStatus.setCardBackgroundColor(Color.parseColor("#519B37"))
                "DIPROSES" -> cardStatus.setCardBackgroundColor(Color.parseColor("#FFAB2A"))
                "DIBATALKAN" -> cardStatus.setCardBackgroundColor(Color.parseColor("#E62E2E"))
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