package com.bengkelsampah.bengkelsampahapp.ui.jualsampah

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bengkelsampah.bengkelsampahapp.R
import com.bengkelsampah.bengkelsampahapp.data.source.remote.response.partner.GetPartnerItem
import com.bengkelsampah.bengkelsampahapp.data.source.remote.response.partner.PartnerDummyData
import com.bengkelsampah.bengkelsampahapp.databinding.ActivityPartnerBinding
import com.bengkelsampah.bengkelsampahapp.utils.MarginItemDecoration

class PartnerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPartnerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPartnerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.topAppBar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val dummyData = PartnerDummyData.getDummyPartnerList()
        setUpPartner(dummyData)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressedDispatcher.onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setUpPartner(partner: List<GetPartnerItem>) {
        val partnerAdapter = PartnerAdapter { _, _ ->

        }

        binding.rvMitra.apply {
            layoutManager = LinearLayoutManager(this@PartnerActivity)
            adapter = partnerAdapter
            addItemDecoration(
                MarginItemDecoration(
                    resources.getDimensionPixelSize(R.dimen.rv_margin_vertical_8),
                    resources.getDimensionPixelSize(R.dimen.rv_margin_horizontal_16)
                )
            )
        }
        partnerAdapter.submitData(partner)
    }
}