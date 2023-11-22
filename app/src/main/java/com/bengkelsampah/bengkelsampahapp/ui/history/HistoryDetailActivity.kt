package com.bengkelsampah.bengkelsampahapp.ui.history

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import cn.pedant.SweetAlert.SweetAlertDialog
import com.bengkelsampah.bengkelsampahapp.R
import com.bengkelsampah.bengkelsampahapp.databinding.ActivityHistoryDetailBinding
import com.bengkelsampah.bengkelsampahapp.domain.model.OrderStatus
import com.bengkelsampah.bengkelsampahapp.domain.model.WasteBoxModel
import com.bengkelsampah.bengkelsampahapp.domain.model.WasteOrderModel
import com.bengkelsampah.bengkelsampahapp.ui.adapter.WasteSoldAdapter
import com.bengkelsampah.bengkelsampahapp.utils.CurrencyNumberFormat
import com.bengkelsampah.bengkelsampahapp.utils.SweetAlertDialogUtils
import com.google.android.material.dialog.MaterialAlertDialogBuilder
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

        val historyId = intent.getStringExtra(HISTORY_ID)
        gettingDetailData(historyId.toString())
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressedDispatcher.onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun gettingDetailData(historyId: String) {
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
                        SweetAlertDialogUtils.showSweetAlertDialog(
                            this@HistoryDetailActivity,
                            historyDetailUiState.message.toString(),
                            SweetAlertDialog.ERROR_TYPE,
                            hasConfirmationButton = false,
                            willFinishActivity = true
                        )
                    }
                }
            }
        }
    }

    private fun setUpDetailPage(history: WasteOrderModel) {
        historyDetailBinding.apply {
            tvStatus.text = history.status.statusName
            tvAgentName.text = history.agent
            tvAgentAddress.text = history.agentAddress
            tvAgentPhone.text =
                getString(R.string.agent_phone, history.agentPhone)
            tvTotalAll.text =
                getString(
                    R.string.total_detail_history,
                    CurrencyNumberFormat.convertToCurrencyFormat(history.total)
                )
            tvPickUpAddress.text = history.address
            tvPickUpDescription.text = history.description

            setUpWasteSold(history.wasteBox)
            setHistoryStatusColor(history.status)
            setCancelButtonVisibility(history.status)
            setDownloadFileVisibility(history.status)

            btnCancelOrder.setOnClickListener {
                MaterialAlertDialogBuilder(this@HistoryDetailActivity, R.style.AlertDialogTheme)
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
                    HistoryDetailPdfFile().generatePdfFile(this@HistoryDetailActivity, history)
                    SweetAlertDialogUtils.showSweetAlertDialog(
                        this@HistoryDetailActivity,
                        getString(R.string.transaction_downloaded),
                        SweetAlertDialog.SUCCESS_TYPE,
                        hasConfirmationButton = false,
                        willFinishActivity = false
                    )
                } catch (e: Exception) {
                    SweetAlertDialogUtils.showSweetAlertDialog(
                        this@HistoryDetailActivity,
                        getString(R.string.transaction_download_failed),
                        SweetAlertDialog.ERROR_TYPE,
                        hasConfirmationButton = false,
                        willFinishActivity = false
                    )
                }
            }
        }
    }

    private fun cancelOrder() {
        SweetAlertDialogUtils.showSweetAlertDialog(
            this,
            getString(R.string.order_cancelled),
            SweetAlertDialog.SUCCESS_TYPE,
            hasConfirmationButton = true,
            willFinishActivity = true
        )
    }

    private fun setDownloadFileVisibility(historyStatus: OrderStatus) {
        if (historyStatus == OrderStatus.DONE) {
            historyDetailBinding.btnDownloadTransaction.visibility = View.VISIBLE
        } else {
            historyDetailBinding.btnDownloadTransaction.visibility = View.GONE
        }
    }

    private fun setCancelButtonVisibility(historyStatus: OrderStatus) {
        historyDetailBinding.apply {
            when (historyStatus) {
                OrderStatus.WAIT_CONFIRMATION -> btnCancelOrder.visibility =
                    View.VISIBLE

                else -> btnCancelOrder.visibility = View.GONE
            }
        }
    }

    private fun setHistoryStatusColor(historyStatus: OrderStatus) {
        historyDetailBinding.apply {
            when (historyStatus) {
                OrderStatus.WAIT_CONFIRMATION -> cardStatus.setCardBackgroundColor(
                    Color.parseColor(OrderStatus.WAIT_CONFIRMATION.color)
                )

                OrderStatus.DONE -> cardStatus.setCardBackgroundColor(
                    Color.parseColor(OrderStatus.DONE.color)
                )

                OrderStatus.PROCESSED -> cardStatus.setCardBackgroundColor(
                    Color.parseColor(OrderStatus.PROCESSED.color)
                )

                OrderStatus.CANCELLED -> cardStatus.setCardBackgroundColor(
                    Color.parseColor(OrderStatus.CANCELLED.color)
                )

                OrderStatus.PICKING_UP -> cardStatus.setCardBackgroundColor(
                    Color.parseColor(OrderStatus.PICKING_UP.color)
                )
            }
        }
    }

    private fun setUpWasteSold(waste: List<WasteBoxModel>) {
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