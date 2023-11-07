package com.bengkelsampah.bengkelsampahapp.ui.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.bengkelsampah.bengkelsampahapp.databinding.ActivityOnboadingBinding
import com.bengkelsampah.bengkelsampahapp.domain.model.OnboardingPageModel
import com.bengkelsampah.bengkelsampahapp.ui.adapter.OnboardingAdapter
import com.bengkelsampah.bengkelsampahapp.ui.auth.LoginActivity

class OnboardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboadingBinding
    private lateinit var itemOnboarding: List<OnboardingPageModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboadingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        itemOnboarding = OnboardingPageModel.listOnboarding

        setUpOnboardingView()
    }


    private fun setUpOnboardingView() {
        val adapter = OnboardingAdapter()
        binding.vpOnboard.adapter = adapter
        binding.indicatorOnboard.attachTo(binding.vpOnboard)

        binding.vpOnboard.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == itemOnboarding.size - 1) {
                    binding.btnOnboardFinish.visibility = View.VISIBLE
                    binding.ctnOnboardNav.visibility = View.GONE
                } else {
                    binding.btnOnboardFinish.visibility = View.GONE
                    binding.ctnOnboardNav.visibility = View.VISIBLE
                }
            }
        })

        binding.btnOnboardFinish.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.tvOnboardSkip.setOnClickListener {
            binding.vpOnboard.currentItem = itemOnboarding.size
        }

        binding.btnOnboardNext.setOnClickListener {
            if (binding.vpOnboard.currentItem < itemOnboarding.size - 1) {
                binding.vpOnboard.currentItem = binding.vpOnboard.currentItem + 1
            }
        }
    }
}