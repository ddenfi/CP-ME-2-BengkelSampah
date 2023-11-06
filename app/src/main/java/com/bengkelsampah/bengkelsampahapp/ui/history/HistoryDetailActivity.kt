package com.bengkelsampah.bengkelsampahapp.ui.history

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.bengkelsampah.bengkelsampahapp.R
import com.bengkelsampah.bengkelsampahapp.databinding.ActivityHistoryDetailBinding
import com.bengkelsampah.bengkelsampahapp.domain.model.DummyData
import com.bengkelsampah.bengkelsampahapp.domain.model.DummyWaste
import com.bengkelsampah.bengkelsampahapp.ui.adapter.WasteSoldAdapter

class HistoryDetailActivity : AppCompatActivity() {
    private lateinit var historyDetailBinding: ActivityHistoryDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        historyDetailBinding = ActivityHistoryDetailBinding.inflate(layoutInflater)
        setContentView(historyDetailBinding.root)

        setSupportActionBar(historyDetailBinding.topAppBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val historyId = intent.getIntExtra(HISTORY_ID, 0)
        setUpDetailPage(historyId)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressedDispatcher.onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setUpDetailPage(historyId: Int) {
        val dummyHistoryData = DummyData.generateDummyData()
        historyDetailBinding.apply {
            tvStatus.text = dummyHistoryData[historyId].status

            when (dummyHistoryData[historyId].status) {
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

            tvAgentName.text = dummyHistoryData[historyId].agent
            tvAgentAddress.text = dummyHistoryData[historyId].agentAddress
            tvAgentPhone.text =
                getString(R.string.agent_phone, dummyHistoryData[historyId].agentPhone)

            setUpWasteSold(dummyHistoryData[historyId].waste)

            tvTotalAll.text =
                getString(
                    R.string.total_detail_history,
                    dummyHistoryData[historyId].total.toString()
                )
            tvPickUpAddress.text = dummyHistoryData[historyId].address
            tvPickUpDescription.text = dummyHistoryData[historyId].description
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

    companion object {
        const val HISTORY_ID = "history_id"
    }
}