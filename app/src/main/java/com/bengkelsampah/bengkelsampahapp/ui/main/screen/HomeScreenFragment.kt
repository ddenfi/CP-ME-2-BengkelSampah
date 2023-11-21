package com.bengkelsampah.bengkelsampahapp.ui.main.screen

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import cn.pedant.SweetAlert.SweetAlertDialog
import com.bengkelsampah.bengkelsampahapp.R
import com.bengkelsampah.bengkelsampahapp.databinding.FragmentHomeScreenBinding
import com.bengkelsampah.bengkelsampahapp.domain.model.NewsResourceModel
import com.bengkelsampah.bengkelsampahapp.ui.adapter.NewsAdapter
import com.bengkelsampah.bengkelsampahapp.ui.banksampah.BankSampahActivity
import com.bengkelsampah.bengkelsampahapp.ui.jualsampah.PartnerActivity
import com.bengkelsampah.bengkelsampahapp.ui.jualsampah.WasteBucketActivity
import com.bengkelsampah.bengkelsampahapp.ui.main.DashboardUiState
import com.bengkelsampah.bengkelsampahapp.ui.main.MainViewModel
import com.bengkelsampah.bengkelsampahapp.ui.main.NewsUiState
import com.bengkelsampah.bengkelsampahapp.ui.moneybag.MoneyBagActivity
import com.bengkelsampah.bengkelsampahapp.ui.news.NewsFeedActivity
import com.bengkelsampah.bengkelsampahapp.ui.news.NewsFeedActivity.Companion.NEWS_RESOURCE
import com.bengkelsampah.bengkelsampahapp.ui.news.NewsFeedDetailActivity
import com.bengkelsampah.bengkelsampahapp.utils.MarginItemDecoration
import com.bengkelsampah.bengkelsampahapp.utils.SweetAlertDialogUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HomeScreenFragment : Fragment() {
    private var _binding: FragmentHomeScreenBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeScreenBinding.inflate(inflater, container, false)
        val newsAdapter = NewsAdapter()
        binding.rvHomeNews.apply {
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

        setUpView(newsAdapter)

        return binding.root
    }

    private fun setUpView(newsAdapter: NewsAdapter) {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.dashboardUiState.collect { dashboardData ->
                        when (dashboardData) {
                            is DashboardUiState.Success -> {
                                binding.tvHomeName.text = dashboardData.user.name
                                binding.tvHomeBalance.text =
                                    getString(R.string.idr, dashboardData.user.balance)
                                binding.apply {
                                    homeDashboard.visibility = View.VISIBLE
                                    homeMenuShimmer.stopShimmer()
                                    homeNewsShimmer.visibility = View.GONE
                                    homeNameShimmer.visibility = View.GONE
                                    homeDashboardShimmer.visibility = View.GONE
                                }
                            }

                            is DashboardUiState.Loading -> {
                                binding.apply {
                                    homeNameShimmer.visibility = View.VISIBLE
                                    homeMenuShimmer.startShimmer()
                                }
                            }

                            is DashboardUiState.Error -> {
                                val context = activity?.parent
                                context?.let {
                                    SweetAlertDialogUtils.showSweetAlertDialog(
                                        it,
                                        dashboardData.message.toString(),
                                        SweetAlertDialog.ERROR_TYPE,
                                        willFinishActivity = false,
                                        hasConfirmationButton = true
                                    )
                                }
                            }
                        }
                    }
                }

                launch {
                    viewModel.newsUiState.collect { newsUiState ->
                        when (newsUiState) {
                            is NewsUiState.Success -> {
                                binding.homeNewsShimmer.visibility = View.GONE
                                binding.rvHomeNews.visibility = View.VISIBLE
                                newsAdapter.submitList(newsUiState.news)
                                newsAdapter.setOnItemClickCallback(object :NewsAdapter.OnItemClickCallback{
                                    override fun onItemClicked(data: NewsResourceModel) {
                                        val intent = Intent(activity,NewsFeedDetailActivity::class.java)
                                        intent.putExtra(NEWS_RESOURCE,data)
                                        startActivity(intent)
                                    }

                                })
                            }

                            is NewsUiState.Loading -> {
                                binding.homeNewsShimmer.visibility = View.VISIBLE
                                binding.rvHomeNews.visibility = View.GONE
                            }

                            is NewsUiState.Error -> {
                                val context = activity?.parent
                                context?.let {
                                    SweetAlertDialogUtils.showSweetAlertDialog(
                                        it,
                                        newsUiState.message.toString(),
                                        SweetAlertDialog.ERROR_TYPE,
                                        willFinishActivity = false,
                                        hasConfirmationButton = true
                                    )
                                }
                            }
                        }
                    }
                }

            }
        }
    }

    private fun setupNavigation() {

        binding.btnHomeJualSampah.setOnClickListener {
            val intent = Intent(activity, PartnerActivity::class.java)
            startActivity(intent)
        }

        binding.btnHomeBankSampah.setOnClickListener {
            val intent = Intent(activity, BankSampahActivity::class.java)
            startActivity(intent)
        }

        binding.btnHomeKeranjangKu.setOnClickListener {
            val intent = Intent(activity, WasteBucketActivity::class.java)
            startActivity(intent)
        }

        binding.btnHomeMoneyBag.setOnClickListener {
            val intent = Intent(activity, MoneyBagActivity::class.java)
            startActivity(intent)
        }
        binding.btnHomeNews.setOnClickListener {
            val intent = Intent(activity, NewsFeedActivity::class.java)
            startActivity(intent)
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}