package com.bengkelsampah.bengkelsampahapp.ui.history

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bengkelsampah.bengkelsampahapp.R
import com.bengkelsampah.bengkelsampahapp.databinding.ActivityHistoryDetailBinding
import com.bengkelsampah.bengkelsampahapp.domain.model.DummyHistoryData
import com.bengkelsampah.bengkelsampahapp.domain.model.DummyWaste
import com.bengkelsampah.bengkelsampahapp.ui.adapter.WasteSoldAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HistoryDetailActivity : AppCompatActivity() {
    private lateinit var historyDetailBinding: ActivityHistoryDetailBinding
    private val viewModel: HistoryDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        historyDetailBinding = ActivityHistoryDetailBinding.inflate(layoutInflater)
        setContentView(historyDetailBinding.root)

        setSupportActionBar(historyDetailBinding.topAppBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val historyId = intent.getIntExtra(HISTORY_ID, 0)
        gettingDetailData(historyId)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressedDispatcher.onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun gettingDetailData(historyId: Int) {
        lifecycleScope.launch {
            viewModel.getHistoryDetail(historyId).collect { historyDetailUiState ->
                when (historyDetailUiState) {
                    is HistoryDetailUiState.Success -> {
                        historyDetailBinding.shimmerHistoryDetail.visibility = View.GONE
                        historyDetailBinding.historyDetail.visibility = View.VISIBLE
                        setUpDetailPage(historyDetailUiState.history)
                    }

                    is HistoryDetailUiState.Loading -> {
                        historyDetailBinding.historyDetail.visibility = View.GONE
                        historyDetailBinding.shimmerHistoryDetail.visibility = View.VISIBLE
                    }

                    is HistoryDetailUiState.Error -> {
                        Toast.makeText(
                            this@HistoryDetailActivity,
                            historyDetailUiState.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    private fun setUpDetailPage(history: DummyHistoryData) {
        historyDetailBinding.apply {
            tvStatus.text = history.status
            tvAgentName.text = history.agent
            tvAgentAddress.text = history.agentAddress
            tvAgentPhone.text =
                getString(R.string.agent_phone, history.agentPhone)
            tvTotalAll.text =
                getString(
                    R.string.total_detail_history,
                    history.total.toString()
                )
            tvPickUpAddress.text = history.address
            tvPickUpDescription.text = history.description

            setUpWasteSold(history.waste)
            setHistoryStatusColor(history.status)
            setCancelButtonVisibility(history.status)
        }
    }

    private fun setCancelButtonVisibility(historyStatus: String) {
        historyDetailBinding.apply {
            when (historyStatus) {
                HistoryStatus.MENUNGGU_KONFIRMASI.statusValue -> btnCancelOrder.visibility =
                    View.VISIBLE

                else -> btnCancelOrder.visibility = View.GONE
            }
        }
    }

    private fun setHistoryStatusColor(historyStatus: String) {
        historyDetailBinding.apply {
            when (historyStatus) {
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