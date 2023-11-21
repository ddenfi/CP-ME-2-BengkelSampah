package com.bengkelsampah.bengkelsampahapp.ui.pickupwaste

import android.app.Activity
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import cn.pedant.SweetAlert.SweetAlertDialog
import com.bengkelsampah.bengkelsampahapp.R
import com.bengkelsampah.bengkelsampahapp.data.source.Resource
import com.bengkelsampah.bengkelsampahapp.databinding.ActivityWasteBoxBinding
import com.bengkelsampah.bengkelsampahapp.domain.model.WasteBoxModel
import com.bengkelsampah.bengkelsampahapp.domain.model.WasteOrderModel
import com.bengkelsampah.bengkelsampahapp.domain.model.WasteUnit
import com.bengkelsampah.bengkelsampahapp.ui.adapter.WasteBoxAdapter
import com.bengkelsampah.bengkelsampahapp.ui.jualsampah.WasteBoxUiState
import com.bengkelsampah.bengkelsampahapp.ui.pickupwaste.PickupActivity.Companion.ORDER_ID
import com.bengkelsampah.bengkelsampahapp.utils.MarginItemDecoration
import com.bengkelsampah.bengkelsampahapp.utils.SweetAlertDialogUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@AndroidEntryPoint
class EditWasteBoxActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWasteBoxBinding
    private val viewModel: PickupViewModel by viewModels()
    private var mWasteBox: List<WasteBoxModel> = listOf()
    private lateinit var wasteBoxAdapter: WasteBoxAdapter

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityWasteBoxBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.topAppBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val edWasteWeight = binding.rvWasteBox.findViewById<EditText>(R.id.ed_waste_weight)
        wasteBoxAdapter = WasteBoxAdapter(
            onClickAdd = { wasteModel, weight ->
                binding.apply {
                    lifecycleScope.launch {
                        if (edWasteWeight.text.isNotEmpty()) {
                            val newWeight = weight + 1
                            edWasteWeight.setText(newWeight.toString())
                        }
                    }
                }
            },
            onClickSubtract = { wasteModel, weight ->
                binding.apply {
                    lifecycleScope.launch {
                        if (edWasteWeight.text.isNotEmpty()) {
                            val newWeight = weight - 1
                            edWasteWeight.setText(newWeight.toString())
                        }
                    }
                }
            }
        )

        val orderId = intent.getStringExtra(ORDER_ID) ?: ""

        val getAddWasteResult =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == ADD_WASTE_RESULT_CODE) {
                    val wasteBox =
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) it.data?.getParcelableArrayListExtra(
                            WASTE_BOX,
                            WasteBoxModel::class.java
                        ) else it.data?.getParcelableArrayListExtra(WASTE_BOX)
                    mWasteBox = wasteBox ?: listOf()
                    wasteBoxAdapter.submitList(mWasteBox)
                }
            }

        binding.btnAdd.setOnClickListener {
            val intent = Intent(this@EditWasteBoxActivity, PickupAddWasteActivity::class.java)
            intent.putParcelableArrayListExtra(WASTE_BOX, ArrayList(mWasteBox))
            getAddWasteResult.launch(intent)
        }

        setupView(orderId)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressedDispatcher.onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupView(orderId: String) {
        lifecycleScope.launch {
            viewModel.getOrderById(orderId).collect { wasteOrder ->
                when (wasteOrder) {
                    is Resource.Success -> {
                        mWasteBox = wasteOrder.data.wasteBox
                        wasteBoxAdapter.submitList(mWasteBox)

                        binding.shimmerWasteBoxPage.visibility = View.GONE
                        binding.wasteBoxPage.visibility = View.VISIBLE

                        if (mWasteBox.isEmpty()) {
                            binding.tvWasteBoxEmpty.visibility = View.VISIBLE
                        } else {
                            binding.tvWasteBoxEmpty.visibility = View.GONE
                            setUpWasteSold()
                        }
                        countTotalWeightAndPrice(mWasteBox)

                        binding.btnFinish.setOnClickListener {
                            updateOrder(wasteOrder.data.copy(wasteBox = mWasteBox))
                        }
                    }

                    is Resource.Loading -> {
                        binding.shimmerWasteBoxPage.visibility = View.VISIBLE
                        binding.wasteBoxPage.visibility = View.GONE
                        binding.tvWasteBoxEmpty.visibility = View.GONE
                    }

                    is Resource.Error -> {
                        SweetAlertDialogUtils.showSweetAlertDialog(
                            this@EditWasteBoxActivity,
                            wasteOrder.exception?.message.toString(),
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
        binding.tvEstimationPrice.text = getString(R.string.price_value, totalPrice.roundToInt())
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

    private fun updateOrder(wasteOrder: WasteOrderModel) {
        lifecycleScope.launch {
            viewModel.updateOrder(wasteOrder).collect {
                when (it) {
                    is Resource.Error -> {
                        SweetAlertDialogUtils.showSweetAlertDialog(
                            this@EditWasteBoxActivity,
                            it.exception?.message.toString(),
                            SweetAlertDialog.ERROR_TYPE,
                            hasConfirmationButton = true,
                            willFinishActivity = true
                        )
                    }

                    is Resource.Loading -> {
                        SweetAlertDialogUtils.showSweetAlertDialog(
                            this@EditWasteBoxActivity,
                            "Saving",
                            SweetAlertDialog.PROGRESS_TYPE,
                            hasConfirmationButton = false,
                            willFinishActivity = false
                        )
                    }

                    is Resource.Success -> {
                        SweetAlertDialogUtils.showSweetAlertDialog(
                            this@EditWasteBoxActivity,
                            "Saving",
                            SweetAlertDialog.SUCCESS_TYPE,
                            hasConfirmationButton = false,
                            willFinishActivity = true
                        )
                    }
                }
            }
        }
    }

    companion object {
        const val PARTNER_ID = "partner_id"
        const val ADD_WASTE_RESULT_CODE = 123
        const val WASTE_BOX = "waste_box"
    }
}