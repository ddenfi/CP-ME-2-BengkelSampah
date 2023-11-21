package com.bengkelsampah.bengkelsampahapp.ui.jualsampah

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import cn.pedant.SweetAlert.SweetAlertDialog
import com.bengkelsampah.bengkelsampahapp.R
import com.bengkelsampah.bengkelsampahapp.data.source.remote.response.partner.GetPartnerItem
import com.bengkelsampah.bengkelsampahapp.databinding.ActivityPartnerBinding
import com.bengkelsampah.bengkelsampahapp.ui.adapter.PartnerAdapter
import com.bengkelsampah.bengkelsampahapp.utils.MarginItemDecoration
import com.bengkelsampah.bengkelsampahapp.utils.SweetAlertDialogUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PartnerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPartnerBinding
    private val viewModel: PartnerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPartnerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.topAppBar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        gettingPartnersData()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressedDispatcher.onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun gettingPartnersData() {
        lifecycleScope.launch {
            viewModel.getPartner().collect { partnerUiState ->
                when (partnerUiState) {
                    is PartnerUiState.Success -> {
                        binding.rvMitra.visibility = View.VISIBLE
                        binding.shimmerRvMitra.visibility = View.GONE
                        setUpPartner(partnerUiState.partners.data)
                    }

                    is PartnerUiState.Loading -> {
                        binding.rvMitra.visibility = View.GONE
                        binding.shimmerRvMitra.visibility = View.VISIBLE
                    }

                    is PartnerUiState.Error -> {
                        SweetAlertDialogUtils.showSweetAlertDialog(
                            this@PartnerActivity,
                            partnerUiState.message.toString(),
                            SweetAlertDialog.ERROR_TYPE,
                            hasConfirmationButton = false,
                            willFinishActivity = true
                        )
                    }
                }
            }
        }
    }

    private fun setUpPartner(partner: List<GetPartnerItem>) {
        val partnerAdapter = PartnerAdapter { uuid ->
            val addWasteIntent = Intent(this, AddWasteActivity::class.java)
            addWasteIntent.putExtra(AddWasteActivity.PARTNER_ID, uuid.toString())
            addWasteIntent.putExtra(AddWasteActivity.IS_FROM_BUCKET, false)
            startActivity(addWasteIntent)
        }

        binding.rvMitra.apply {
            layoutManager = LinearLayoutManager(this@PartnerActivity)
            adapter = partnerAdapter
            addItemDecoration(
                MarginItemDecoration(
                    resources.getDimensionPixelSize(R.dimen.rv_margin_vertical_8),
                    resources.getDimensionPixelSize(R.dimen.rv_margin_horizontal_16)
                )
            )
        }
        partnerAdapter.submitData(partner)
    }
}