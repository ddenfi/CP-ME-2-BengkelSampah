package com.bengkelsampah.bengkelsampahapp.ui.driver

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bengkelsampah.bengkelsampahapp.R
import com.bengkelsampah.bengkelsampahapp.databinding.FragmentHomeDriverBinding
import com.bengkelsampah.bengkelsampahapp.ui.adapter.NewsAdapter
import com.bengkelsampah.bengkelsampahapp.ui.main.DashboardUiState
import com.bengkelsampah.bengkelsampahapp.ui.main.NewsUiState
import com.bengkelsampah.bengkelsampahapp.ui.moneybag.MoneyBagActivity
import com.bengkelsampah.bengkelsampahapp.ui.pickupwaste.PickupActivity
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class HomeDriverFragment : Fragment() {
    private var _binding: FragmentHomeDriverBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DriverMainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeDriverBinding.inflate(inflater, container, false)

        setupNavigation()
        setupView()

        return binding.root
    }

    private fun setupView() {
        val newsAdapter = NewsAdapter()
        binding.rvDriverHomeNews.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = newsAdapter
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.dashboardUiState.collect { uiState ->
                        when (uiState) {
                            is DashboardUiState.Error -> TODO()
                            DashboardUiState.Loading -> {
                                showLoading(
                                    true,
                                    binding.driverHomeShimmer,
                                    binding.driverHomeNameShimmer
                                )
                                binding.driverHomeMenuShimmer.startShimmer()
                            }

                            is DashboardUiState.Success -> {
                                showLoading(
                                    false,
                                    binding.driverHomeShimmer,
                                    binding.driverHomeNameShimmer
                                )
                                binding.homeDashboard.visibility = View.VISIBLE
                                binding.driverHomeMenuShimmer.stopShimmer()

                                binding.tvDriverHomeName.text = uiState.user.name
                                binding.tvDriverHomeBalance.text =
                                    getString(R.string.idr, uiState.user.balance)
                            }
                        }
                    }
                }

                launch {
                    viewModel.newsUiState.collect { uiState ->
                        when (uiState) {
                            is NewsUiState.Error -> TODO()
                            NewsUiState.Loading ->{
                                showLoading(true,binding.driverHomeNewsShimmer)
                            }
                            is NewsUiState.Success -> {
                                showLoading(false,binding.driverHomeNewsShimmer)
                                newsAdapter.submitList(uiState.news)
                            }
                        }

                    }
                }
            }
        }
    }

    private fun showLoading(isLoading: Boolean, vararg loadingView: View) {
        if (isLoading) {
            loadingView.forEach {
                it.visibility = View.VISIBLE
            }
        } else {
            loadingView.forEach {
                it.visibility = View.GONE
            }
        }
    }

    private fun setupNavigation() {
        binding.btnDriverHomeJemputSampah.setOnClickListener {
            val intent = Intent(activity, PickupActivity::class.java)
            startActivity(intent)
        }

        binding.btnDriverHomeArtikel.setOnClickListener {
            //TODO Connect to article list
        }
        binding.btnDriverHomeMoneyBag.setOnClickListener {
            val intent = Intent(activity, MoneyBagActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}