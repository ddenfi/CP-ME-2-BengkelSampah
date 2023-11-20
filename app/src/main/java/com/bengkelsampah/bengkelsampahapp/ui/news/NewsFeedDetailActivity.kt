package com.bengkelsampah.bengkelsampahapp.ui.news

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import cn.pedant.SweetAlert.SweetAlertDialog
import com.bengkelsampah.bengkelsampahapp.R
import com.bengkelsampah.bengkelsampahapp.databinding.ActivityNewsFeedDetailBinding
import com.bengkelsampah.bengkelsampahapp.domain.model.NewsResourceModel
import com.bengkelsampah.bengkelsampahapp.ui.news.NewsFeedActivity.Companion.NEWS_RESOURCE
import com.bengkelsampah.bengkelsampahapp.utils.SweetAlertDialogUtils

class NewsFeedDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewsFeedDetailBinding

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsFeedDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val getIntentData = intent.getParcelableExtra(NEWS_RESOURCE, NewsResourceModel::class.java)

        setupView(getIntentData)
    }

    private fun setupView(newsResource: NewsResourceModel?) {
        if (newsResource != null) {
            binding.tvNewsDetailEmpty.visibility = View.GONE
            binding.wvNewsDetail.visibility = View.VISIBLE
            try {
                binding.wvNewsDetail.loadUrl(newsResource.url)
            } catch (e: Exception) {
                SweetAlertDialogUtils.showSweetAlertDialog(
                    this,
                    e.message.toString(),
                    SweetAlertDialog.ERROR_TYPE,
                    hasConfirmationButton = true,
                    willFinishActivity = false
                )
            }
        } else {
            binding.tvNewsDetailEmpty.visibility = View.VISIBLE
            binding.wvNewsDetail.visibility = View.GONE
        }
    }
}