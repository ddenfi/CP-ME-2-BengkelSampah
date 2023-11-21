package com.bengkelsampah.bengkelsampahapp.ui.jualsampah

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import cn.pedant.SweetAlert.SweetAlertDialog
import com.bengkelsampah.bengkelsampahapp.R
import com.bengkelsampah.bengkelsampahapp.databinding.ActivityWasteBucketBinding
import com.bengkelsampah.bengkelsampahapp.domain.model.WasteBoxModel
import com.bengkelsampah.bengkelsampahapp.domain.model.WasteUnit
import com.bengkelsampah.bengkelsampahapp.ui.adapter.WasteBoxAdapter
import com.bengkelsampah.bengkelsampahapp.utils.MarginItemDecoration
import com.bengkelsampah.bengkelsampahapp.utils.SweetAlertDialogUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WasteBucketActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWasteBucketBinding
    private val viewModel: WasteBoxViewModel by viewModels()
    private val wasteBoxAdapter = WasteBoxAdapter(
        onClickAdd = { wasteModel, weight, edWasteWeight ->
            binding.apply {
                lifecycleScope.launch {
                    if (edWasteWeight.text.isNotEmpty()) {
                        val newWeight = weight + 1
                        edWasteWeight.setText(newWeight.toString())
                        viewModel.updateWasteBucketItem(wasteModel, newWeight)
                    }
                }
            }
        },
        onClickSubtract = { wasteModel, weight, edWasteWeight ->
            binding.apply {
                lifecycleScope.launch {
                    if (edWasteWeight.text.isNotEmpty()) {
                        val newWeight = weight - 1
                        edWasteWeight.setText(newWeight.toString())
                        if (newWeight == 0.0) {
                            viewModel.deleteFromWasteBucket(wasteModel, weight)
                        } else {
                            viewModel.updateWasteBucketItem(wasteModel, newWeight)
                        }
                    }
                }
            }
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWasteBucketBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.topAppBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        gettingData()
        setUpWasteSold()

        binding.btnAdd.setOnClickListener {
            val intent = Intent(this, AddWasteActivity::class.java)
            intent.putExtra(AddWasteActivity.IS_FROM_BUCKET, true)
            startActivity(intent)
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
            viewModel.getWasteBucketItems().collect { wasteBoxUiState ->
                when (wasteBoxUiState) {
                    is WasteBoxUiState.Success -> {
                        binding.shimmerWasteBoxPage.visibility = View.GONE
                        binding.wasteBoxPage.visibility = View.VISIBLE

                        if (wasteBoxUiState.wasteBoxItems.isEmpty()) {
                            binding.tvWasteBoxEmpty.visibility = View.VISIBLE
                            binding.rvWasteBox.visibility = View.GONE
                        } else {
                            binding.tvWasteBoxEmpty.visibility = View.GONE
                            binding.rvWasteBox.visibility = View.VISIBLE
                            wasteBoxAdapter.submitList(wasteBoxUiState.wasteBoxItems)
                        }
                        countTotalWeightAndPrice(wasteBoxUiState.wasteBoxItems)
                    }

                    is WasteBoxUiState.Loading -> {
                        binding.shimmerWasteBoxPage.visibility = View.VISIBLE
                        binding.wasteBoxPage.visibility = View.GONE
                        binding.tvWasteBoxEmpty.visibility = View.GONE
                    }

                    is WasteBoxUiState.Error -> {
                        SweetAlertDialogUtils.showSweetAlertDialog(
                            this@WasteBucketActivity,
                            wasteBoxUiState.message.toString(),
                            SweetAlertDialog.ERROR_TYPE,
                            hasConfirmationButton = false,
                            willFinishActivity = true
                        )
                    }
                }
            }
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

    private fun setUpWasteSold() {
        binding.rvWasteBox.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = wasteBoxAdapter
            addItemDecoration(
                MarginItemDecoration(
                    resources.getDimensionPixelSize(R.dimen.rv_margin_vertical_8),
                    resources.getDimensionPixelSize(R.dimen.rv_margin_horizontal_16)
                )
            )
        }
    }
}