package com.bengkelsampah.bengkelsampahapp.ui.main.screen

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
import com.bengkelsampah.bengkelsampahapp.databinding.FragmentHomeScreenBinding
import com.bengkelsampah.bengkelsampahapp.ui.adapter.NewsAdapter
import com.bengkelsampah.bengkelsampahapp.ui.main.DashboardUiState
import com.bengkelsampah.bengkelsampahapp.ui.main.MainViewModel
import com.bengkelsampah.bengkelsampahapp.ui.main.NewsUiState
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
        }

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
                                binding.tvHomeBalance.text = dashboardData.user.balance.toString()
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

                            is DashboardUiState.Error -> Log.d(
                                "Dashboard",
                                dashboardData.toString()
                            )
                        }
                    }
                }

                launch {
                    viewModel.newsUiState.collect { newsUiState ->
                        when (newsUiState) {
                            is NewsUiState.Success -> {
                                binding.homeNewsShimmer.visibility = View.GONE
                                newsAdapter.submitList(newsUiState.news)
                            }

                            is NewsUiState.Loading -> {
                                binding.homeNewsShimmer.visibility = View.VISIBLE
                            }

                            is NewsUiState.Error ->{
                                Log.d("Home",newsUiState.toString())
                            }
                        }
                    }
                }

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}