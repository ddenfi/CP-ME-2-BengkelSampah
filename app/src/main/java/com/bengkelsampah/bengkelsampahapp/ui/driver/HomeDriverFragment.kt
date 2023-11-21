package com.bengkelsampah.bengkelsampahapp.ui.driver

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
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
import cn.pedant.SweetAlert.SweetAlertDialog
import com.bengkelsampah.bengkelsampahapp.R
import com.bengkelsampah.bengkelsampahapp.databinding.FragmentHomeDriverBinding
import com.bengkelsampah.bengkelsampahapp.domain.model.NewsResourceModel
import com.bengkelsampah.bengkelsampahapp.ui.adapter.NewsAdapter
import com.bengkelsampah.bengkelsampahapp.ui.main.DashboardUiState
import com.bengkelsampah.bengkelsampahapp.ui.main.NewsUiState
import com.bengkelsampah.bengkelsampahapp.ui.moneybag.MoneyBagActivity
import com.bengkelsampah.bengkelsampahapp.ui.news.NewsFeedActivity
import com.bengkelsampah.bengkelsampahapp.ui.news.NewsFeedActivity.Companion.NEWS_RESOURCE
import com.bengkelsampah.bengkelsampahapp.ui.news.NewsFeedDetailActivity
import com.bengkelsampah.bengkelsampahapp.ui.pickupwaste.PickupActivity
import com.bengkelsampah.bengkelsampahapp.utils.MarginItemDecoration
import com.bengkelsampah.bengkelsampahapp.utils.SweetAlertDialogUtils
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

        val newsAdapter = NewsAdapter()
        binding.rvDriverHomeNews.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = newsAdapter
            addItemDecoration(
                MarginItemDecoration(
                    resources.getDimensionPixelSize(R.dimen.rv_margin_vertical_8),
                    resources.getDimensionPixelSize(R.dimen.rv_margin_horizontal_0)
                )
            )
        }

        setupNavigation()

        setupView(newsAdapter)

        return binding.root
    }

    private fun setupView(newsAdapter: NewsAdapter) {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.dashboardUiState.collect { uiState ->
                        when (uiState) {
                            is DriverDashboardUiState.Error -> {
                                val dialog = SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
                                    .setTitleText(uiState.message)
                                    .hideConfirmButton()

                                dialog.show()
                                Handler(Looper.getMainLooper()).postDelayed(
                                    { dialog.dismissWithAnimation() },
                                    1500
                                )
                            }
                            is DriverDashboardUiState.Loading -> {
                                showLoading(
                                    true,
                                    binding.driverHomeShimmer,
                                    binding.driverHomeNameShimmer
                                )
                                binding.driverHomeMenuShimmer.startShimmer()
                            }

                            is DriverDashboardUiState.Success -> {
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
                            is NewsUiState.Error -> {
                                val sweetAlertDialog = SweetAlertDialog(
                                    context,
                                    SweetAlertDialog.ERROR_TYPE
                                ).setTitleText(uiState.message)
                                sweetAlertDialog.show()
                            }

                            NewsUiState.Loading -> {
                                showLoading(true, binding.driverHomeNewsShimmer)
                            }

                            is NewsUiState.Success -> {
                                showLoading(false, binding.driverHomeNewsShimmer)

                                newsAdapter.submitList(uiState.news)

                                newsAdapter.setOnItemClickCallback(object :
                                    NewsAdapter.OnItemClickCallback {
                                    override fun onItemClicked(data: NewsResourceModel) {
                                        val intent =
                                            Intent(activity, NewsFeedDetailActivity::class.java)
                                        intent.putExtra(NEWS_RESOURCE, data)
                                        startActivity(intent)
                                    }

                                })
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
            val intent = Intent(activity, NewsFeedActivity::class.java)
            startActivity(intent)
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