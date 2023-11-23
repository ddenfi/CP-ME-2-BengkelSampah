package com.bengkelsampah.bengkelsampahapp.ui.pickupwaste

import android.content.Intent
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
import com.bengkelsampah.bengkelsampahapp.databinding.ActivityPickupBinding
import com.bengkelsampah.bengkelsampahapp.domain.model.WasteOrderModel
import com.bengkelsampah.bengkelsampahapp.ui.adapter.PickupOrderAdapter
import com.bengkelsampah.bengkelsampahapp.utils.MarginItemDecoration
import com.bengkelsampah.bengkelsampahapp.utils.SweetAlertDialogUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PickupActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPickupBinding
    private val viewModel: PickupViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPickupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.topAppBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setupView()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressedDispatcher.onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupView() {
        val pickupAdapter = PickupOrderAdapter()

        binding.rvPickup.apply {
            layoutManager = LinearLayoutManager(this@PickupActivity)
            adapter = pickupAdapter
            addItemDecoration(
                MarginItemDecoration(
                    resources.getDimensionPixelSize(R.dimen.rv_margin_vertical_16),
                    resources.getDimensionPixelSize(R.dimen.rv_margin_horizontal_24)
                )
            )
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.wasteOrders.collect {
                    when (it) {
                        is Resource.Error -> {
                            SweetAlertDialogUtils.showSweetAlertDialog(
                                this@PickupActivity,
                                it.exception?.message.toString(),
                                SweetAlertDialog.ERROR_TYPE,
                                hasConfirmationButton = true,
                                willFinishActivity = false
                            )
                        }

                        is Resource.Loading -> {
                            showLoading(true)
                        }

                        is Resource.Success -> {
                            showLoading(false)
                            pickupAdapter.submitList(it.data)
                        }
                    }
                }
            }
        }

        pickupAdapter.setOnItemClickCallback(object : PickupOrderAdapter.OnItemClickCallback {
            override fun onItemClicked(data: WasteOrderModel) {
                val intent = Intent(this@PickupActivity, DetailPickupActivity::class.java)
                intent.putExtra(ORDER_ID, data.id)
                startActivity(intent)
            }

        })
    }

    private fun showLoading(isLoading: Boolean) {
        if (!isLoading) {
            binding.rvPickup.visibility = View.VISIBLE
            binding.ctnPickupShimmer.visibility = View.GONE
        } else {
            binding.rvPickup.visibility = View.GONE
            binding.ctnPickupShimmer.visibility = View.VISIBLE
        }

    }

    companion object {
        const val ORDER_ID = "ORDER ID"
    }
}