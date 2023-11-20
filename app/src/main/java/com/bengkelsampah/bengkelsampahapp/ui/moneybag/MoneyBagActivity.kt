package com.bengkelsampah.bengkelsampahapp.ui.moneybag

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import cn.pedant.SweetAlert.SweetAlertDialog
import com.bengkelsampah.bengkelsampahapp.data.source.Resource
import com.bengkelsampah.bengkelsampahapp.databinding.ActivityMoneyBagBinding
import com.bengkelsampah.bengkelsampahapp.ui.adapter.MoneybagHistoryAdapter
import com.bengkelsampah.bengkelsampahapp.utils.SweetAlertDialogUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MoneyBagActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMoneyBagBinding
    private val viewModel: MoneybagViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoneyBagBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.userMoneyBagData.collect { userMoneybag ->
                        when (userMoneybag) {
                            is Resource.Error -> {
                                moneybagLoading(false)
                                SweetAlertDialogUtils.showSweetAlertDialog(
                                    this@MoneyBagActivity,
                                    userMoneybag.exception?.message.toString(),
                                    SweetAlertDialog.ERROR_TYPE,
                                    hasConfirmationButton = true,
                                    willFinishActivity = false
                                )
                            }

                            is Resource.Loading -> moneybagLoading(true)
                            is Resource.Success -> {
                                moneybagLoading(false)
                                binding.tvMoneybagBalancePoint.text =
                                    userMoneybag.data.balance.toString()
                            }
                        }
                    }
                }

                launch {
                    viewModel.getUseMoneybagHistory().collect { userMoneybagHistory ->
                        when (userMoneybagHistory) {
                            is Resource.Error -> {
                                moneybagHistoryLoading(false)
                                SweetAlertDialogUtils.showSweetAlertDialog(
                                    this@MoneyBagActivity,
                                    userMoneybagHistory.exception?.message.toString(),
                                    SweetAlertDialog.ERROR_TYPE,
                                    hasConfirmationButton = true,
                                    willFinishActivity = false
                                )
                            }

                            is Resource.Loading -> moneybagHistoryLoading(true)
                            is Resource.Success -> {
                                val moneybagHistoryAdapter = MoneybagHistoryAdapter()
                                binding.rvMoneybagHistory.layoutManager =
                                    LinearLayoutManager(this@MoneyBagActivity)
                                binding.rvMoneybagHistory.adapter = moneybagHistoryAdapter
                                moneybagHistoryLoading(false)

                                moneybagHistoryAdapter.submitList(userMoneybagHistory.data)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun moneybagLoading(isLoading: Boolean) {
        if (!isLoading) {
            binding.apply {
                moneybagBalanceShimmer.visibility = View.GONE
                shimmerMoneybagWithdraw.stopShimmer()
                tvMoneybagBalancePoint.visibility = View.VISIBLE
            }
        } else {
            binding.apply {
                shimmerMoneybagWithdraw.startShimmer()
                moneybagBalanceShimmer.visibility = View.VISIBLE
                tvMoneybagBalancePoint.visibility = View.GONE
            }
        }
    }

    private fun moneybagHistoryLoading(isLoading: Boolean) {
        if (!isLoading) {
            binding.apply {
                rvMoneybagHistory.visibility = View.VISIBLE
                moneybagHistoryShimmer.visibility = View.GONE
            }
        } else {
            binding.apply {
                rvMoneybagHistory.visibility = View.GONE
                moneybagHistoryShimmer.visibility = View.VISIBLE
            }
        }
    }
}