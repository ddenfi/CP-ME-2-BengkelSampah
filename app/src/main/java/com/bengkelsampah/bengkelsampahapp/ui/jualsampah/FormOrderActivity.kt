package com.bengkelsampah.bengkelsampahapp.ui.jualsampah

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import cn.pedant.SweetAlert.SweetAlertDialog
import com.bengkelsampah.bengkelsampahapp.R
import com.bengkelsampah.bengkelsampahapp.data.source.remote.response.partner.PartnerById
import com.bengkelsampah.bengkelsampahapp.databinding.ActivityFormOrderBinding
import com.bengkelsampah.bengkelsampahapp.domain.model.WasteBoxModel
import com.bengkelsampah.bengkelsampahapp.domain.model.WasteUnit
import com.bengkelsampah.bengkelsampahapp.ui.adapter.WasteFormAdapter
import com.bengkelsampah.bengkelsampahapp.ui.main.MainActivity
import com.bengkelsampah.bengkelsampahapp.utils.CurrencyNumberFormat
import com.bengkelsampah.bengkelsampahapp.utils.MarginItemDecoration
import com.bengkelsampah.bengkelsampahapp.utils.SweetAlertDialogUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FormOrderActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFormOrderBinding
    private val viewModel: WasteBoxViewModel by viewModels()
    private var wasteData: List<WasteBoxModel> = listOf()
    private var partnerData: PartnerById? = null
    private var total: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.topAppBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        gettingData()

        binding.btnAddWasteFormOrder.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.radioCash.isChecked = true

        binding.btnRequestPickUpFormOrder.setOnClickListener {
            if (binding.tietPickUpAddressFormOrder.text.toString().isEmpty()) {
                binding.tilPickUpAddressFormOrder.error = getString(R.string.address_error)
            } else {
                binding.tilPickUpAddressFormOrder.error = null

                SweetAlertDialogUtils.showSweetAlertDialog(
                    this@FormOrderActivity,
                    getString(R.string.request_pick_up_success),
                    SweetAlertDialog.SUCCESS_TYPE,
                    hasConfirmationButton = false,
                    willFinishActivity = false
                )

                viewModel.insertOrder(
                    wasteData,
                    partnerData,
                    binding.tietPickUpAddressFormOrder.text.toString(),
                    binding.tietAddressDescFormOrder.text.toString(),
                    total
                )

                viewModel.clearWasteBox()

                Handler(Looper.getMainLooper()).postDelayed({
                    val intent = Intent(this, MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                }, 1500)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressedDispatcher.onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun gettingData() {
        lifecycleScope.launch {
            viewModel.getWasteBoxItem().collect { wasteBoxUiState ->
                when (wasteBoxUiState) {
                    is WasteBoxUiState.Success -> {
                        binding.shimmerFromOrderOrderPage.visibility = View.GONE
                        binding.shimmerFromOrderBottomActionMenu.visibility = View.GONE
                        binding.formOrderOrderPage.visibility = View.VISIBLE
                        binding.formOrderBottomActionMenu.visibility = View.VISIBLE

                        setUpWasteSold(wasteBoxUiState.wasteBoxItems)
                        countTotalWeightAndPrice(wasteBoxUiState.wasteBoxItems)
                        wasteData = wasteBoxUiState.wasteBoxItems
                    }

                    is WasteBoxUiState.Loading -> {
                        binding.shimmerFromOrderOrderPage.visibility = View.VISIBLE
                        binding.shimmerFromOrderBottomActionMenu.visibility = View.VISIBLE
                        binding.formOrderOrderPage.visibility = View.GONE
                        binding.formOrderBottomActionMenu.visibility = View.GONE
                    }

                    is WasteBoxUiState.Error -> {
                        SweetAlertDialogUtils.showSweetAlertDialog(
                            this@FormOrderActivity,
                            wasteBoxUiState.message.toString(),
                            SweetAlertDialog.ERROR_TYPE,
                            hasConfirmationButton = false,
                            willFinishActivity = true
                        )
                    }
                }
            }
        }

        lifecycleScope.launch {
            viewModel.getPartnerById(intent.getStringExtra(PARTNER_ID).toString())
                .collect { formOrderUiState ->
                    when (formOrderUiState) {
                        is FormOrderUIState.Success -> {
                            binding.shimmerFromOrderOrderPage.visibility = View.GONE
                            binding.shimmerFromOrderBottomActionMenu.visibility = View.GONE
                            binding.formOrderOrderPage.visibility = View.VISIBLE
                            binding.formOrderBottomActionMenu.visibility = View.VISIBLE

                            setUpAgentData(formOrderUiState.partnerById)
                            partnerData = formOrderUiState.partnerById
                        }

                        is FormOrderUIState.Loading -> {
                            binding.shimmerFromOrderOrderPage.visibility = View.VISIBLE
                            binding.shimmerFromOrderBottomActionMenu.visibility = View.VISIBLE
                            binding.formOrderOrderPage.visibility = View.GONE
                            binding.formOrderBottomActionMenu.visibility = View.GONE
                        }

                        is FormOrderUIState.Error -> {
                            SweetAlertDialogUtils.showSweetAlertDialog(
                                this@FormOrderActivity,
                                formOrderUiState.message.toString(),
                                SweetAlertDialog.ERROR_TYPE,
                                hasConfirmationButton = false,
                                willFinishActivity = true
                            )
                        }
                    }
                }
        }
    }

    private fun setUpAgentData(agent: PartnerById) {
        binding.apply {
            tvAgentNameFormOrder.text = agent.data.name
            tvAgentAddressFormOrder.text = agent.data.address
            tvAgentPhoneNumberFormOrder.text = agent.data.phoneNumber
        }
    }

    private fun countTotalWeightAndPrice(wasteBoxItems: List<WasteBoxModel>) {
        var totalWeight = 0.0
        var totalPrice = 0.0
        for (waste in wasteBoxItems) {
            totalWeight += waste.amount
            totalPrice += waste.waste.pricePerUnit * waste.amount
        }

        total = totalPrice.toInt()
        binding.tvTotalWeight.text =
            getString(R.string.waste_weight, totalWeight, WasteUnit.KG.abbreviation)
        binding.tvEstimationPrice.text = getString(
            R.string.price_value,
            CurrencyNumberFormat.convertToCurrencyFormat(totalPrice.toInt())
        )
    }

    private fun setUpWasteSold(wasteBoxItems: List<WasteBoxModel>) {
        val wasteFormAdapter = WasteFormAdapter()
        binding.rvWasteSold.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = wasteFormAdapter
            addItemDecoration(
                MarginItemDecoration(
                    resources.getDimensionPixelSize(R.dimen.rv_margin_vertical_8),
                    resources.getDimensionPixelSize(R.dimen.rv_margin_horizontal_16)
                )
            )
        }
        wasteFormAdapter.submitList(wasteBoxItems)
    }

    companion object {
        const val PARTNER_ID = "partner_id"
    }
}