package com.bengkelsampah.bengkelsampahapp.ui.pickupwaste

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import cn.pedant.SweetAlert.SweetAlertDialog
import com.bengkelsampah.bengkelsampahapp.R
import com.bengkelsampah.bengkelsampahapp.data.source.Resource
import com.bengkelsampah.bengkelsampahapp.databinding.ActivityDetailPickupBinding
import com.bengkelsampah.bengkelsampahapp.domain.model.OrderStatus
import com.bengkelsampah.bengkelsampahapp.domain.model.WasteOrderModel
import com.bengkelsampah.bengkelsampahapp.ui.adapter.WasteListAdapter
import com.bengkelsampah.bengkelsampahapp.ui.driver.DriverMainActivity
import com.bengkelsampah.bengkelsampahapp.ui.main.MainActivity
import com.bengkelsampah.bengkelsampahapp.ui.pickupwaste.EditWasteBoxActivity.Companion.WASTE_BOX
import com.bengkelsampah.bengkelsampahapp.ui.pickupwaste.PickupActivity.Companion.ORDER_ID
import com.bengkelsampah.bengkelsampahapp.utils.SweetAlertDialogUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@AndroidEntryPoint
class DetailPickupActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailPickupBinding
    private val viewModel: PickupViewModel by viewModels()
    private var progressDialog: SweetAlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPickupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val orderId = intent.getStringExtra(ORDER_ID)!!

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getOrderById(orderId).collect {
                    when (it) {
                        is Resource.Error -> {
                            SweetAlertDialogUtils.showSweetAlertDialog(
                                this@DetailPickupActivity,
                                it.exception?.message.toString(),
                                SweetAlertDialog.ERROR_TYPE,
                                hasConfirmationButton = false,
                                willFinishActivity = true
                            )
                        }

                        is Resource.Loading -> {
                            showLoading(true)
                        }

                        is Resource.Success -> {
                            showLoading(false)
                            setupView(it.data)
                        }
                    }
                }
            }
        }

    }

    private fun setupView(wasteOrder: WasteOrderModel) {
        val wasteListAdapter = WasteListAdapter()
        binding.rvPickupDetailWasteList.adapter = wasteListAdapter
        binding.rvPickupDetailWasteList.layoutManager = LinearLayoutManager(this)
        wasteListAdapter.submitList(wasteOrder.wasteBox)


        with(binding) {
            tvPickupDetailDistance.text = wasteOrder.distance.toString()
            tvPickupDetailCustomerName.text = wasteOrder.consumerName
            tvPickupDetailPaymentMethod.text = "Cash"
            tvPickupDetailWeight.text = "3Kg"
            tvPickupDetailSum.text = getString(R.string.idr, 2000)
            tvPickupDetailPhoneNumber.text = wasteOrder.agentPhone
            tvPickupDetailAdress.text = wasteOrder.address

            if (wasteOrder.status == OrderStatus.PICKING_UP) {
                ctnPickupDetailStatus.visibility = View.VISIBLE
                btnDetailPickupEditOrder.visibility = View.VISIBLE
                btnDetailPickupPickupOrder.visibility = View.GONE
                btnDetailPickupFinishedOrder.visibility = View.VISIBLE
            } else {
                ctnPickupDetailStatus.visibility = View.GONE
                btnDetailPickupEditOrder.visibility = View.GONE
                btnDetailPickupPickupOrder.visibility = View.VISIBLE
                btnDetailPickupFinishedOrder.visibility = View.GONE
            }
        }

        pickupOrder(wasteOrder)
        finishOrder(wasteOrder)
        editOrder(wasteOrder)

        binding.btnPickupDetailOpenWith.setOnClickListener {
            openWithMap()
        }

    }

    private fun editOrder(wasteOrder: WasteOrderModel) {
        binding.btnDetailPickupEditOrder.setOnClickListener {
            val intent = Intent(this@DetailPickupActivity, EditWasteBoxActivity::class.java)
            intent.putExtra(ORDER_ID, wasteOrder.id)
            intent.putParcelableArrayListExtra(WASTE_BOX, ArrayList(wasteOrder.wasteBox))
            startActivity(intent)
        }
    }

    private fun finishOrder(wasteOrder: WasteOrderModel) {
        val updatedData = wasteOrder.copy(status = OrderStatus.DONE)

        binding.btnDetailPickupFinishedOrder.setOnClickListener {
            lifecycleScope.launch {
                viewModel.pickupOrder(updatedData).collect {
                    when (it) {
                        is Resource.Error -> {
                            progressDialog?.dismiss()
                            progressDialog = SweetAlertDialog(
                                this@DetailPickupActivity,
                                SweetAlertDialog.ERROR_TYPE
                            )
                                .setTitleText("Error ${it.exception?.message}")
                            progressDialog?.show()
                        }

                        is Resource.Loading -> {
                            progressDialog?.dismiss()
                            progressDialog = SweetAlertDialog(
                                this@DetailPickupActivity,
                                SweetAlertDialog.PROGRESS_TYPE
                            )
                                .setTitleText(getString(R.string.loading))
                            progressDialog?.show()
                        }

                        is Resource.Success -> {
                            progressDialog?.dismiss()
                            progressDialog = SweetAlertDialog(
                                this@DetailPickupActivity,
                                SweetAlertDialog.SUCCESS_TYPE
                            )
                                .setTitleText(it.data)
                            progressDialog?.hideConfirmButton()
                            progressDialog?.show()
                            delay(1000)
                            progressDialog?.dismissWithAnimation()

                            val intent =
                                Intent(this@DetailPickupActivity, DriverMainActivity::class.java)
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(intent)
                        }
                    }
                }
            }
        }
    }

    private fun pickupOrder(wasteOrderModel: WasteOrderModel) {
        val updatedData = wasteOrderModel.copy(status = OrderStatus.PICKING_UP)

        binding.btnDetailPickupPickupOrder.setOnClickListener {
            lifecycleScope.launch {
                viewModel.pickupOrder(updatedData).collect {
                    when (it) {
                        is Resource.Error -> {
                            progressDialog?.dismiss()
                            progressDialog = SweetAlertDialog(
                                this@DetailPickupActivity,
                                SweetAlertDialog.ERROR_TYPE
                            )
                                .setTitleText("Error ${it.exception?.message}")
                            progressDialog?.show()
                        }

                        is Resource.Loading -> {
                            progressDialog?.dismiss()
                            progressDialog = SweetAlertDialog(
                                this@DetailPickupActivity,
                                SweetAlertDialog.PROGRESS_TYPE
                            )
                                .setTitleText(getString(R.string.loading))
                            progressDialog?.show()
                        }

                        is Resource.Success -> {
                            progressDialog?.dismiss()
                            progressDialog = SweetAlertDialog(
                                this@DetailPickupActivity,
                                SweetAlertDialog.SUCCESS_TYPE
                            )
                                .setTitleText(it.data)
                            progressDialog?.hideConfirmButton()
                            progressDialog?.show()
                            delay(1000)
                            progressDialog?.dismissWithAnimation()
                        }
                    }
                }
            }
        }
    }

    private fun openWithMap() {
        val gmmIntentUri = Uri.parse("geo:-6.167765,106.833220?q=-6.167765,106.833220")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        try {
            startActivity(mapIntent)
        } catch (ex: ActivityNotFoundException) {
            Toast.makeText(this, "Google map not installed", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (!isLoading) {
            binding.apply {
                ctnDetailPickupDetailOrder.visibility = View.VISIBLE
                ctnDetailPickupActionMenu.visibility = View.VISIBLE
                ctnDetailPickupDetailOrderShimmer.visibility = View.GONE
                ctnDetailPickupActionMenuShimmer.visibility = View.GONE
            }
        } else {
            binding.apply {
                ctnDetailPickupDetailOrder.visibility = View.GONE
                ctnDetailPickupActionMenu.visibility = View.GONE
                ctnDetailPickupDetailOrderShimmer.visibility = View.VISIBLE
                ctnDetailPickupActionMenuShimmer.visibility = View.VISIBLE
            }
        }
    }
}