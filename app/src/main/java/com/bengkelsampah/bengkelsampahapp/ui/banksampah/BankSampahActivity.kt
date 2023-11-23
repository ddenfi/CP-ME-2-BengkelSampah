package com.bengkelsampah.bengkelsampahapp.ui.banksampah

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bengkelsampah.bengkelsampahapp.R
import com.bengkelsampah.bengkelsampahapp.data.source.Resource
import com.bengkelsampah.bengkelsampahapp.databinding.ActivityBankSampahBinding
import com.bengkelsampah.bengkelsampahapp.ui.adapter.MoneybagHistoryAdapter
import com.bengkelsampah.bengkelsampahapp.utils.CurrencyNumberFormat
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BankSampahActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBankSampahBinding
    private val viewModel: BankSampahViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBankSampahBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.userBankSampahData.collect { userBankSampahData ->
                        when (userBankSampahData) {
                            is Resource.Error -> {
                                userDataLoading(false)
                            }

                            is Resource.Loading -> userDataLoading(true)
                            is Resource.Success -> {
                                userDataLoading(false)

                                binding.apply {
                                    tvBsName.text = userBankSampahData.data.name
                                    tvBsBankSampah.text = userBankSampahData.data.bankSampahName
                                    tvBsBalance.text =
                                        getString(
                                            R.string.idr,
                                            CurrencyNumberFormat.convertToCurrencyFormat(
                                                userBankSampahData.data.balance
                                            )
                                        )
                                    tvBsCustomerId.text = userBankSampahData.data.customerId
                                }

                                Glide.with(this@BankSampahActivity)
                                    .load(userBankSampahData.data.photoUrl).circleCrop()
                                    .into(binding.ivBsPhoto)
                            }
                        }
                    }
                }

                launch {
                    viewModel.getUserBankSampahHistory().collect { userBankSampahHistory ->
                        when (userBankSampahHistory) {
                            is Resource.Error -> {
                                historyDataLoading(false)
                            }

                            is Resource.Loading -> historyDataLoading(true)
                            is Resource.Success -> {
                                historyDataLoading(false)

                                val moneybagHistoryAdapter = MoneybagHistoryAdapter()
                                binding.rvBsHistory.layoutManager =
                                    LinearLayoutManager(this@BankSampahActivity)
                                binding.rvBsHistory.adapter = moneybagHistoryAdapter

                                moneybagHistoryAdapter.submitList(userBankSampahHistory.data)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun userDataLoading(isLoading: Boolean) {
        if (!isLoading) {
            binding.apply {
                shimmerBsUserData.visibility = View.GONE
                shimmerBsWithdrawMenu.stopShimmer()
                ctnBsUserData.visibility = View.VISIBLE
            }
        } else {
            binding.apply {
                shimmerBsUserData.visibility = View.VISIBLE
                ctnBsUserData.visibility = View.GONE
                shimmerBsWithdrawMenu.startShimmer()
            }
        }
    }

    private fun historyDataLoading(isLoading: Boolean) {
        if (!isLoading) {
            binding.apply {
                rvBsHistory.visibility = View.VISIBLE
                shimmerBsHistory.visibility = View.GONE
            }
        } else {
            binding.apply {
                rvBsHistory.visibility = View.GONE
                shimmerBsHistory.visibility = View.VISIBLE
            }

        }
    }
}