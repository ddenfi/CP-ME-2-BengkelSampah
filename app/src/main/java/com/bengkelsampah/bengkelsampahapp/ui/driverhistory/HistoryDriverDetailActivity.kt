package com.bengkelsampah.bengkelsampahapp.ui.driverhistory

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import cn.pedant.SweetAlert.SweetAlertDialog
import com.bengkelsampah.bengkelsampahapp.R
import com.bengkelsampah.bengkelsampahapp.data.source.Resource
import com.bengkelsampah.bengkelsampahapp.databinding.ActivityHistoryDriverDetailBinding
import com.bengkelsampah.bengkelsampahapp.domain.model.OrderStatus
import com.bengkelsampah.bengkelsampahapp.domain.model.OrderStatus.*
import com.bengkelsampah.bengkelsampahapp.domain.model.WasteOrderModel
import com.bengkelsampah.bengkelsampahapp.ui.adapter.WasteSoldAdapter
import com.bengkelsampah.bengkelsampahapp.utils.CurrencyNumberFormat
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HistoryDriverDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHistoryDriverDetailBinding
    private val viewModel: DriverHistoryDetailViewModel by viewModels()
    private val wasteSoldAdapter = WasteSoldAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryDriverDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.topAppBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val historyId = intent.getStringExtra(HISTORY_ID)

        historyId?.let {
            gettingDetailData(historyId)
        }

        setUpWasteSold()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressedDispatcher.onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun gettingDetailData(historyId: String) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getHistoryById(historyId).collect { orderHistory ->
                    when (orderHistory) {
                        is Resource.Error -> {
                            val dialog = SweetAlertDialog(
                                this@HistoryDriverDetailActivity,
                                SweetAlertDialog.ERROR_TYPE
                            )
                                .setTitleText(orderHistory.exception?.message.toString())
                                .hideConfirmButton()

                            dialog.show()
                            Handler(Looper.getMainLooper()).postDelayed(
                                { dialog.dismissWithAnimation() },
                                1500
                            )
                        }

                        is Resource.Loading -> {
                            binding.historyDetail.visibility = View.GONE
                            binding.shimmerHistoryDetail.visibility = View.VISIBLE
                        }

                        is Resource.Success -> {
                            binding.shimmerHistoryDetail.visibility = View.GONE
                            binding.historyDetail.visibility = View.VISIBLE
                            setUpDetailPage(orderHistory.data)
                        }
                    }
                }
            }
        }
    }

    private fun setUpDetailPage(history: WasteOrderModel) {
        binding.apply {
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

            wasteSoldAdapter.submitList(history.wasteBox)
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
//                    HistoryDetailPdfFile().generatePdfFile(
//                        this@HistoryDriverDetailActivity,
//                        history
//                    )
                    //TODO Make conversion to history model
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

    private fun setDownloadFileVisibility(historyStatus: OrderStatus) {
        if (historyStatus == DONE) {
            binding.btnDownloadTransaction.visibility = View.VISIBLE
        } else {
            binding.btnDownloadTransaction.visibility = View.GONE
        }
    }

    private fun setCancelButtonVisibility(historyStatus: OrderStatus) {
        binding.apply {
            when (historyStatus) {
                WAIT_CONFIRMATION -> btnCancelOrder.visibility =
                    View.VISIBLE

                else -> btnCancelOrder.visibility = View.GONE
            }
        }
    }

    private fun setHistoryStatusColor(historyStatus: OrderStatus) {
        binding.apply {
            when (historyStatus) {
                WAIT_CONFIRMATION -> cardStatus.setCardBackgroundColor(
                    Color.parseColor(WAIT_CONFIRMATION.color)
                )

                DONE -> cardStatus.setCardBackgroundColor(
                    Color.parseColor(DONE.color)
                )

                PROCESSED -> cardStatus.setCardBackgroundColor(
                    Color.parseColor(PROCESSED.color)
                )

                CANCELLED -> cardStatus.setCardBackgroundColor(
                    Color.parseColor(CANCELLED.color)
                )

                PICKING_UP -> cardStatus.setCardBackgroundColor(
                    Color.parseColor(PICKING_UP.color)
                )
            }
        }
    }

    private fun setUpWasteSold() {
        binding.rvWasteSold.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = wasteSoldAdapter
        }
    }

    companion object {
        const val HISTORY_ID = "history_id"
    }
}