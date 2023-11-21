package com.bengkelsampah.bengkelsampahapp.ui.news

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import cn.pedant.SweetAlert.SweetAlertDialog
import com.bengkelsampah.bengkelsampahapp.R
import com.bengkelsampah.bengkelsampahapp.data.source.Resource
import com.bengkelsampah.bengkelsampahapp.databinding.ActivityNewsFeedBinding
import com.bengkelsampah.bengkelsampahapp.domain.model.NewsResourceModel
import com.bengkelsampah.bengkelsampahapp.ui.adapter.NewsAdapter
import com.bengkelsampah.bengkelsampahapp.utils.MarginItemDecoration
import com.bengkelsampah.bengkelsampahapp.utils.SweetAlertDialogUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NewsFeedActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewsFeedBinding
    private val viewModel: NewsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsFeedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val newsAdapter = NewsAdapter()
        binding.rvNewsFeed.apply {
            layoutManager = LinearLayoutManager(this@NewsFeedActivity)
            adapter = newsAdapter
            addItemDecoration(
                MarginItemDecoration(
                    resources.getDimensionPixelSize(R.dimen.rv_margin_vertical_8),
                    resources.getDimensionPixelSize(R.dimen.rv_margin_horizontal_24)
                )
            )
        }


        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.newsResource.collect { newsResource ->
                    when (newsResource) {
                        is Resource.Error -> {
                            showLoading(false)
                            SweetAlertDialogUtils.showSweetAlertDialog(
                                this@NewsFeedActivity,
                                newsResource.exception?.message.toString(),
                                SweetAlertDialog.ERROR_TYPE,
                                hasConfirmationButton = true,
                                willFinishActivity = false
                            )
                        }

                        is Resource.Loading -> showLoading(true)
                        is Resource.Success -> {
                            showLoading(false)
                            newsAdapter.submitList(newsResource.data)

                            newsAdapter.setOnItemClickCallback(object : NewsAdapter.OnItemClickCallback {
                                override fun onItemClicked(data: NewsResourceModel) {
                                    val intent = Intent(this@NewsFeedActivity, NewsFeedDetailActivity::class.java)
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


    private fun showLoading(isLoading: Boolean) {
        if (!isLoading) {
            binding.apply {
                shimmerNewsFeedPage.visibility = View.GONE
                newsFeedPage.visibility = View.VISIBLE
            }
        } else {
            binding.apply {
                shimmerNewsFeedPage.visibility = View.VISIBLE
                newsFeedPage.visibility = View.GONE
            }
        }
    }

    companion object {
        const val NEWS_RESOURCE = "news_resource"
    }
}