package com.bengkelsampah.bengkelsampahapp.ui.pickupwaste

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
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
    private lateinit var mWasteOrder: WasteOrderModel
    private lateinit var sweetAlertDialog: SweetAlertDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityWasteBoxBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.topAppBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val orderId = intent.getStringExtra(ORDER_ID) ?: ""

        setupView(orderId)

        binding.btnAdd.setOnClickListener {

        }

        binding.btnFinish.setOnClickListener {
//            updateOrder(mWasteOrder.copy())
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressedDispatcher.onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupView(orderId:String) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.getOrderById(orderId).collect{ wasteOrder->
                    when (wasteOrder) {
                        is Resource.Success -> {
                            mWasteOrder = wasteOrder.data
                            binding.shimmerWasteBoxPage.visibility = View.GONE
                            binding.wasteBoxPage.visibility = View.VISIBLE

                            if (wasteOrder.data.wasteBox.isEmpty()) {
                                binding.tvWasteBoxEmpty.visibility = View.VISIBLE
                            } else {
                                binding.tvWasteBoxEmpty.visibility = View.GONE
                                setUpWasteSold(wasteOrder.data.wasteBox)
                            }
                            countTotalWeightAndPrice(wasteOrder.data.wasteBox)
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
                                hasConfirmationButton = true,
                                willFinishActivity = false
                            )
                        }
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

        binding.tvWasteTotalWeight.text = totalWeight.toString()
        binding.tvWasteUnit.text = WasteUnit.KG.abbreviation
        binding.tvEstimationPrice.text = getString(R.string.price_value, totalPrice.roundToInt())
    }

    private fun setUpWasteSold(wasteBoxItems: List<WasteBoxModel>) {
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

    private fun updateOrder(wasteOrder:WasteOrderModel){
        lifecycleScope.launch {
            viewModel.updateOrder(wasteOrder).collect{
                when(it){
                    is Resource.Error -> {
                        //TODO Snackbar error
                    }
                    is Resource.Loading -> {
                        sweetAlertDialog.hide()
                        sweetAlertDialog = SweetAlertDialog(this@EditWasteBoxActivity,SweetAlertDialog.PROGRESS_TYPE)
                        sweetAlertDialog.show()
                    }
                    is Resource.Success -> {
                        sweetAlertDialog.hide()
                        sweetAlertDialog = SweetAlertDialog(this@EditWasteBoxActivity,SweetAlertDialog.SUCCESS_TYPE)
                        sweetAlertDialog.show()
                        delay(1000)
                        finish()
                    }
                }
            }
        }
    }

    companion object {
        const val PARTNER_ID = "partner_id"
    }
}