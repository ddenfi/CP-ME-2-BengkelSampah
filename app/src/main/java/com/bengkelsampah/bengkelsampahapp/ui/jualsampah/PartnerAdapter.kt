package com.bengkelsampah.bengkelsampahapp.ui.jualsampah

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bengkelsampah.bengkelsampahapp.data.source.remote.response.partner.GetPartnerItem
import com.bengkelsampah.bengkelsampahapp.databinding.ItemPartnerBinding
import java.util.UUID

class PartnerAdapter(private val onClickListener: (id: UUID, partner: GetPartnerItem) -> Unit) :
    RecyclerView.Adapter<PartnerAdapter.PartnerViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<GetPartnerItem>() {
        override fun areItemsTheSame(oldItem: GetPartnerItem, newItem: GetPartnerItem) =
            oldItem.partnerId == newItem.partnerId

        override fun areContentsTheSame(oldItem: GetPartnerItem, newItem: GetPartnerItem) =
            oldItem == newItem
    }

    private val listDiffer = AsyncListDiffer(this, diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PartnerViewHolder {
        val binding =
            ItemPartnerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PartnerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PartnerViewHolder, position: Int) {
        holder.bind(listDiffer.currentList[position])
    }

    override fun getItemCount(): Int = listDiffer.currentList.size

    inner class PartnerViewHolder(private val binding: ItemPartnerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: GetPartnerItem) {
            binding.apply {
                tvMitraName.text = item.name
                tvMitraAddress.text = item.address
                tvPhoneNumber.text = item.phoneNumber

                itemPartner.setOnClickListener {
                    onClickListener.invoke(item.partnerId, item)
                }
            }
        }
    }

}