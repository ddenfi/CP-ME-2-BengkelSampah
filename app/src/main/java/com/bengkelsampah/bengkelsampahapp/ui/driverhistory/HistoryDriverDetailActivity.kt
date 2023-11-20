package com.bengkelsampah.bengkelsampahapp.ui.driverhistory

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bengkelsampah.bengkelsampahapp.R
import com.bengkelsampah.bengkelsampahapp.databinding.ActivityHistoryDriverDetailBinding
import com.bengkelsampah.bengkelsampahapp.domain.model.HistoryModel
import com.bengkelsampah.bengkelsampahapp.domain.model.OrderStatus
import com.bengkelsampah.bengkelsampahapp.domain.model.WasteBoxModel
import com.bengkelsampah.bengkelsampahapp.ui.adapter.WasteSoldAdapter
import com.bengkelsampah.bengkelsampahapp.ui.history.HistoryDetailActivity
import com.bengkelsampah.bengkelsampahapp.ui.history.HistoryDetailPdfFile
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.launch

class HistoryDriverDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHistoryDriverDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryDriverDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.topAppBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val historyId = intent.getIntExtra(HistoryDetailActivity.HISTORY_ID, 0)
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
//            viewModel.getHistoryDetail(historyId).collect { historyDetailUiState ->
//                when (historyDetailUiState) {
//                    is HistoryDetailUiState.Success -> {
//                        binding.shimmerHistoryDetail.visibility = View.GONE
//                        binding.historyDetail.visibility = View.VISIBLE
//                        setUpDetailPage(historyDetailUiState.history)
//                    }
//
//                    is HistoryDetailUiState.Loading -> {
//                        binding.historyDetail.visibility = View.GONE
//                        binding.shimmerHistoryDetail.visibility = View.VISIBLE
//                    }
//
//                    is HistoryDetailUiState.Error -> {
//                        Toast.makeText(
//                            this@HistoryDetailActivity,
//                            historyDetailUiState.message,
//                            Toast.LENGTH_SHORT
//                        ).show()
//                    }
//                }
//            }
        }
    }

    private fun setUpDetailPage(history: HistoryModel) {
        binding.apply {
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
            setDownloadFileVisibility(history.status)

            btnCancelOrder.setOnClickListener {
                MaterialAlertDialogBuilder(
                    this@HistoryDriverDetailActivity,
                    R.style.AlertDialogTheme
                )
                    .setMessage(getString(R.string.cancel_order_confirmation))
                    .setCancelable(false)
                    .setPositiveButton(getString(R.string.yes)) { _, _ ->
                        cancelOrder()
                    }
                    .setNegativeButton(getString(R.string.no)) { dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()
            }

            btnDownloadTransaction.setOnClickListener {
                try {

                    HistoryDetailPdfFile().generatePdfFile(
                        this@HistoryDriverDetailActivity,
                        history
                    )
                    Toast.makeText(
                        this@HistoryDriverDetailActivity,
                        getString(R.string.transaction_downloaded),
                        Toast.LENGTH_SHORT
                    ).show()
                } catch (e: Exception) {
                    Toast.makeText(
                        this@HistoryDriverDetailActivity,
                        getString(R.string.transaction_download_failed),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun cancelOrder() {
        Toast.makeText(this, getString(R.string.order_cancelled), Toast.LENGTH_LONG).show()
        this.finish()
    }

    private fun setDownloadFileVisibility(historyStatus: String) {
        if (historyStatus == OrderStatus.DONE.statusName) {
            binding.btnDownloadTransaction.visibility = View.VISIBLE
        } else {
            binding.btnDownloadTransaction.visibility = View.GONE
        }
    }

    private fun setCancelButtonVisibility(historyStatus: String) {
        binding.apply {
            when (historyStatus) {
                OrderStatus.WAIT_CONFIRMATION.statusName -> btnCancelOrder.visibility =
                    View.VISIBLE

                else -> btnCancelOrder.visibility = View.GONE
            }
        }
    }

    private fun setHistoryStatusColor(historyStatus: String) {
        binding.apply {
            when (historyStatus) {
                OrderStatus.WAIT_CONFIRMATION.statusName -> cardStatus.setCardBackgroundColor(
                    Color.parseColor(OrderStatus.WAIT_CONFIRMATION.color)
                )

                OrderStatus.DONE.statusName -> cardStatus.setCardBackgroundColor(
                    Color.parseColor(OrderStatus.DONE.color)
                )

                OrderStatus.PROCESSED.statusName -> cardStatus.setCardBackgroundColor(
                    Color.parseColor(OrderStatus.PROCESSED.color)
                )

                OrderStatus.CANCELLED.statusName -> cardStatus.setCardBackgroundColor(
                    Color.parseColor(OrderStatus.CANCELLED.color)
                )
            }
        }
    }

    private fun setUpWasteSold(waste: List<WasteBoxModel>) {
        val wasteSoldAdapter = WasteSoldAdapter()

        binding.rvWasteSold.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = wasteSoldAdapter
        }

        wasteSoldAdapter.submitList(waste)
    }

    companion object {
        const val HISTORY_ID = "history_id"
    }
}