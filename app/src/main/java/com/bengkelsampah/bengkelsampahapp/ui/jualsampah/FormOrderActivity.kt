package com.bengkelsampah.bengkelsampahapp.ui.jualsampah

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bengkelsampah.bengkelsampahapp.R
import com.bengkelsampah.bengkelsampahapp.databinding.ActivityFormOrderBinding

class FormOrderActivity : AppCompatActivity() {

    private lateinit var binding:ActivityFormOrderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //TODO set data
        //TODO set rv
    }

    /**
     * Setup loading ui state
     */
    fun showLoading(isLoading:Boolean){
        if (!isLoading){
            binding.apply {
                shimmerFromOrderOrderPage.visibility = View.GONE
                shimmerFromOrderBottomActionMenu.visibility = View.GONE
                fromOrderBottomActionMenu.visibility = View.VISIBLE
                fromOrderOrderPage.visibility = View.VISIBLE
            }
        } else {
            binding.apply {
                shimmerFromOrderOrderPage.visibility = View.VISIBLE
                shimmerFromOrderBottomActionMenu.visibility = View.VISIBLE
                fromOrderBottomActionMenu.visibility = View.GONE
                fromOrderOrderPage.visibility = View.GONE
            }
        }
    }


    companion object {
        const val PARTNER_ID = "partner_id"
    }
}