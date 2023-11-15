package com.bengkelsampah.bengkelsampahapp.ui.pickupwaste

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bengkelsampah.bengkelsampahapp.R
import com.bengkelsampah.bengkelsampahapp.data.source.Resource
import com.bengkelsampah.bengkelsampahapp.databinding.ActivityDetailPickupBinding
import com.bengkelsampah.bengkelsampahapp.ui.adapter.WasteListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailPickupActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailPickupBinding
    private val viewModel: PickupViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPickupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
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
                            Log.d("Pickup", "setupView: Loading")
                        }

                        is Resource.Success -> {
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
}