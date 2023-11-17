package com.bengkelsampah.bengkelsampahapp.ui.pickupwaste

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import cn.pedant.SweetAlert.SweetAlertDialog
import com.bengkelsampah.bengkelsampahapp.R
import com.bengkelsampah.bengkelsampahapp.data.source.Resource
import com.bengkelsampah.bengkelsampahapp.databinding.ActivityDetailPickupBinding
import com.bengkelsampah.bengkelsampahapp.ui.adapter.WasteListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
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

        setupView()

        binding.btnDetailPickupPickupOrder.setOnClickListener {
            lifecycleScope.launch {
                viewModel.pickupOrder().collect{
                    when (it){
                        is Resource.Error -> {

                        }
                        is Resource.Loading -> {
                            progressDialog = SweetAlertDialog(this@DetailPickupActivity, SweetAlertDialog.PROGRESS_TYPE)
                                .setTitleText(getString(R.string.loading))
                            progressDialog?.show()
                        }
                        is Resource.Success -> {
                            progressDialog?.dismiss()
                            progressDialog = SweetAlertDialog(this@DetailPickupActivity, SweetAlertDialog.SUCCESS_TYPE)
                                .setTitleText(it.data)
                            progressDialog?.hideConfirmButton()
                            progressDialog?.show()
                            delay(800)
                            progressDialog?.dismissWithAnimation()
                            onBackPressedDispatcher.onBackPressed()
                        }
                    }
                }
            }

        }
    }

    private fun setupView() {
        val wasteListAdapter = WasteListAdapter()
        binding.rvPickupDetailWasteList.adapter = wasteListAdapter
        binding.rvPickupDetailWasteList.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getOrderById(1).collect {
                    when (it) {
                        is Resource.Error -> {

                        }

                        is Resource.Loading -> {
                            showLoading(true)
                        }

                        is Resource.Success -> {
                            showLoading(false)

                            with(binding) {
                                tvPickupDetailDistance.text = it.data.distance.toString()
                                tvPickupDetailCustomerName.text = it.data.consumerName
                                tvPickupDetailPaymentMethod.text = "Cash"
                                tvPickupDetailWeight.text = "30Kg"
                                tvPickupDetailPaymentMethod.text = "Cash"
                                tvPickupDetailSum.text = getString(R.string.idr, 2000)
                                tvPickupDetailPhoneNumber.text = it.data.agentPhone
                            }
                            wasteListAdapter.submitList(it.data.waste)
                        }
                    }
                }
            }
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