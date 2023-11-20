package com.bengkelsampah.bengkelsampahapp.ui.jualsampah

import android.os.Bundle
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
import com.bengkelsampah.bengkelsampahapp.utils.MarginItemDecoration
import com.bengkelsampah.bengkelsampahapp.utils.SweetAlertDialogUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FormOrderActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFormOrderBinding
    private val viewModel: WasteBoxViewModel by viewModels()

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
                // TODO add order to database
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

            viewModel.getPartnerById(intent.getStringExtra(PARTNER_ID).toString())
                .collect { formOrderUiState ->
                    when (formOrderUiState) {
                        is FormOrderUIState.Success -> {
                            binding.shimmerFromOrderOrderPage.visibility = View.GONE
                            binding.shimmerFromOrderBottomActionMenu.visibility = View.GONE
                            binding.formOrderOrderPage.visibility = View.VISIBLE
                            binding.formOrderBottomActionMenu.visibility = View.VISIBLE

                            setUpAgentData(formOrderUiState.partnerById)
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

        binding.tvTotalWeight.text =
            getString(R.string.waste_weight, totalWeight, WasteUnit.KG.abbreviation)
        binding.tvEstimationPrice.text = getString(R.string.price_value, totalPrice.toInt())
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