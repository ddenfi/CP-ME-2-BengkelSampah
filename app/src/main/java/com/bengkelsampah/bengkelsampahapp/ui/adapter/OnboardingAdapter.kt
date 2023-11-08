package com.bengkelsampah.bengkelsampahapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bengkelsampah.bengkelsampahapp.databinding.ItemOnboardingPageBinding
import com.bengkelsampah.bengkelsampahapp.domain.model.OnboardingPageModel

class OnboardingAdapter : RecyclerView.Adapter<OnboardingAdapter.OnboardingViewHolder>() {

    private val itemOnboard = OnboardingPageModel.listOnboarding

    inner class OnboardingViewHolder(private val binding: ItemOnboardingPageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: OnboardingPageModel) {
            binding.apply {
                ivItemOnboard.setImageResource(item.id)
                tvItemOnboardTitle.text = item.title
                tvItemOnboardDesc.text = item.desc
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingViewHolder {
        val binding =
            ItemOnboardingPageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OnboardingViewHolder(binding)
    }

    override fun getItemCount(): Int = itemOnboard.count()

    override fun onBindViewHolder(holder: OnboardingViewHolder, position: Int) {
        holder.bind(itemOnboard[position])
    }
}