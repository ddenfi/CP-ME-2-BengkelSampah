package com.bengkelsampah.bengkelsampahapp.ui.history

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bengkelsampah.bengkelsampahapp.R
import com.bengkelsampah.bengkelsampahapp.databinding.ActivityHistoryDetailBinding

class HistoryDetailActivity : AppCompatActivity() {
    private lateinit var historyDetailBinding: ActivityHistoryDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        historyDetailBinding = ActivityHistoryDetailBinding.inflate(layoutInflater)
        setContentView(historyDetailBinding.root)

        setSupportActionBar(historyDetailBinding.topAppBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setUpDetailPage()
        setUpBottomNavigation()
    }

    private fun setUpDetailPage() {
        val dummyHistoryData = DummyData.generateDummyData()
        historyDetailBinding.apply {
            tvStatus.text = dummyHistoryData[0].status

            when (dummyHistoryData[0].status.uppercase()) {
                "MENUNGGU KONFIRMASI" -> cardStatus.setCardBackgroundColor(Color.parseColor("#999797"))
                "SELESAI" -> cardStatus.setCardBackgroundColor(Color.parseColor("#519B37"))
                "DIPROSES" -> cardStatus.setCardBackgroundColor(Color.parseColor("#FFAB2A"))
                "DIBATALKAN" -> cardStatus.setCardBackgroundColor(Color.parseColor("#E62E2E"))
            }

            tvAgentName.text = dummyHistoryData[0].agent
            tvAgentAddress.text = dummyHistoryData[0].agentAddress
            tvAgentPhone.text = getString(R.string.agent_phone, dummyHistoryData[0].agentPhone)

            setUpWasteSold(dummyHistoryData[0].waste)

            tvTotalAll.text =
                getString(R.string.total_detail_history, dummyHistoryData[0].total.toString())
            tvPickUpAddress.text = dummyHistoryData[0].address
            tvPickUpDescription.text = dummyHistoryData[0].description
        }
    }

    private fun setUpWasteSold(waste: List<DummyWaste>) {
        val wasteSoldAdapter = WasteSoldAdapter()

        historyDetailBinding.rvWasteSold.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = wasteSoldAdapter
        }

        wasteSoldAdapter.submitList(waste)
    }

    private fun setUpBottomNavigation() {
        historyDetailBinding.bottomNavigation.selectedItemId = R.id.menu_history
        historyDetailBinding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_home -> {
                    true
                }

                R.id.menu_history -> {
                    true
                }

                R.id.menu_cart -> {
                    true
                }

                R.id.menu_money_bag -> {
                    true
                }

                R.id.menu_profile -> {
                    true
                }

                else -> false
            }
        }
    }
}