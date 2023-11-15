package com.bengkelsampah.bengkelsampahapp.ui.jualsampah

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bengkelsampah.bengkelsampahapp.R
import com.bengkelsampah.bengkelsampahapp.databinding.ActivityWasteBoxBinding
import com.bengkelsampah.bengkelsampahapp.domain.model.WasteSoldModel
import com.bengkelsampah.bengkelsampahapp.domain.model.WasteUnit
import com.bengkelsampah.bengkelsampahapp.ui.adapter.WasteBoxAdapter
import com.bengkelsampah.bengkelsampahapp.utils.MarginItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WasteBoxActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWasteBoxBinding
    private val viewModel: WasteBoxViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWasteBoxBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.topAppBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        gettingData()

        binding.btnAdd.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.btnFinish.setOnClickListener {

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
                        binding.shimmerWasteBoxPage.visibility = View.GONE
                        binding.wasteBoxPage.visibility = View.VISIBLE

                        if (wasteBoxUiState.wasteBoxItems.isEmpty()) {
                            binding.tvWasteBoxEmpty.visibility = View.VISIBLE
                        } else {
                            binding.tvWasteBoxEmpty.visibility = View.GONE
                            setUpWasteSold(wasteBoxUiState.wasteBoxItems)
                        }
                        countTotalWeightAndPrice(wasteBoxUiState.wasteBoxItems)
                    }

                    is WasteBoxUiState.Loading -> {
                        binding.shimmerWasteBoxPage.visibility = View.VISIBLE
                        binding.wasteBoxPage.visibility = View.GONE
                        binding.tvWasteBoxEmpty.visibility = View.GONE
                    }

                    is WasteBoxUiState.Error -> {
                        Toast.makeText(
                            this@WasteBoxActivity,
                            wasteBoxUiState.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    private fun countTotalWeightAndPrice(wasteBoxItems: List<WasteSoldModel>) {
        var totalWeight = 0
        var totalPrice = 0
        for (waste in wasteBoxItems) {
            totalWeight += waste.amount
            totalPrice += waste.waste.pricePerUnit * waste.amount
        }

        binding.tvWasteTotalWeight.text = totalWeight.toString()
        binding.tvWasteUnit.text = WasteUnit.KG.abbreviation
        binding.tvEstimationPrice.text = getString(R.string.price_value, totalPrice)
    }

    private fun setUpWasteSold(wasteBoxItems: List<WasteSoldModel>) {
        val wasteBoxAdapter = WasteBoxAdapter()

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

        wasteBoxAdapter.submitList(wasteBoxItems)
    }

    companion object {
        const val PARTNER_ID = "partner_id"
    }
}