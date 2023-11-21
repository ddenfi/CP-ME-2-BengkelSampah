package com.bengkelsampah.bengkelsampahapp.ui.jualsampah

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import cn.pedant.SweetAlert.SweetAlertDialog
import com.bengkelsampah.bengkelsampahapp.R
import com.bengkelsampah.bengkelsampahapp.data.source.Resource
import com.bengkelsampah.bengkelsampahapp.databinding.ActivityAddWasteBinding
import com.bengkelsampah.bengkelsampahapp.databinding.DialogAddWasteBinding
import com.bengkelsampah.bengkelsampahapp.domain.model.WasteModel
import com.bengkelsampah.bengkelsampahapp.ui.adapter.WasteTypeAdapter
import com.bengkelsampah.bengkelsampahapp.utils.MarginItemDecoration
import com.bengkelsampah.bengkelsampahapp.utils.SweetAlertDialogUtils
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.badge.BadgeUtils
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AddWasteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddWasteBinding
    private val viewModel: WasteBoxViewModel by viewModels()
    private val wasteTypeAdapter = WasteTypeAdapter { wasteType -> adapterOnClick(wasteType) }
    private var isFromBucket = false

    @com.google.android.material.badge.ExperimentalBadgeUtils
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddWasteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.topAppBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        isFromBucket = intent.getBooleanExtra(IS_FROM_BUCKET, false)
        getWasteType()

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.countWasteBoxItems().collect { wasteBoxItemsNumber ->
                    val badgeDrawable = BadgeDrawable.create(this@AddWasteActivity)
                    badgeDrawable.number = wasteBoxItemsNumber
                    BadgeUtils.attachBadgeDrawable(badgeDrawable, binding.fabWasteBox, null)
                }
            }
        }

        if (isFromBucket) binding.fabWasteBox.visibility = View.GONE

        binding.fabWasteBox.setOnClickListener {
            val wasteBoxIntent = Intent(this@AddWasteActivity, WasteBoxActivity::class.java)
            wasteBoxIntent.putExtra(
                WasteBoxActivity.PARTNER_ID,
                intent.getStringExtra(PARTNER_ID)
            )
            startActivity(wasteBoxIntent)
        }

        binding.searchBarWaste.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.onSearchQueryChange(newText ?: "")
                return true
            }
        })

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.wasteSearchResult.collect { searchResult ->
                    when (searchResult) {
                        is Resource.Error -> {
                            SweetAlertDialogUtils.showSweetAlertDialog(
                                this@AddWasteActivity,
                                searchResult.exception?.message.toString(),
                                SweetAlertDialog.ERROR_TYPE,
                                hasConfirmationButton = false,
                                willFinishActivity = true
                            )
                        }

                        is Resource.Loading -> {
                            binding.shimmerWasteType.visibility = View.VISIBLE
                            binding.rvWasteType.visibility = View.GONE
                        }

                        is Resource.Success -> {
                            binding.rvWasteType.visibility = View.VISIBLE
                            binding.shimmerWasteType.visibility = View.GONE
                            wasteTypeAdapter.submitList(searchResult.data)
                        }
                    }
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressedDispatcher.onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getWasteType() {
        lifecycleScope.launch {
            viewModel.getAllWastes().collect { addWasteUiState ->
                when (addWasteUiState) {
                    is AddWasteUiState.Success -> {
                        binding.rvWasteType.visibility = View.VISIBLE
                        binding.shimmerWasteType.visibility = View.GONE
                        setUpWasteType(addWasteUiState.wasteType)
                    }

                    is AddWasteUiState.Loading -> {
                        binding.shimmerWasteType.visibility = View.VISIBLE
                        binding.rvWasteType.visibility = View.GONE
                    }

                    is AddWasteUiState.Error -> {
                        SweetAlertDialogUtils.showSweetAlertDialog(
                            this@AddWasteActivity,
                            addWasteUiState.message.toString(),
                            SweetAlertDialog.ERROR_TYPE,
                            hasConfirmationButton = false,
                            willFinishActivity = true
                        )
                    }
                }
            }
        }
    }

    private fun setUpWasteType(waste: List<WasteModel>) {
        binding.rvWasteType.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = wasteTypeAdapter
            addItemDecoration(
                MarginItemDecoration(
                    resources.getDimensionPixelSize(R.dimen.rv_margin_vertical_8),
                    resources.getDimensionPixelSize(R.dimen.rv_margin_horizontal_8),
                    3
                )
            )
        }
        wasteTypeAdapter.submitList(waste)
    }

    private fun adapterOnClick(wasteType: WasteModel) {
        val dialogBinding = DialogAddWasteBinding.inflate(layoutInflater)
        val dialog = MaterialAlertDialogBuilder(this)
            .setCancelable(false)
            .setView(dialogBinding.root)
            .create()

        dialogBinding.apply {
            tvDialogWasteName.text = wasteType.name
            lifecycleScope.launch {
                viewModel.getWasteItemById(wasteType.wasteId).collect { amount ->
                    edDialogWasteWeight.setText(amount.toString())
                    edDialogWasteWeight.addTextChangedListener(object : TextWatcher {
                        override fun beforeTextChanged(
                            p0: CharSequence?,
                            p1: Int,
                            p2: Int,
                            p3: Int
                        ) {
                        }

                        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                        override fun afterTextChanged(editable: Editable?) {
                            if (editable.toString().isNotEmpty()) {
                                edDialogWasteWeight.error = null
                                if (amount != 0.0 && editable.toString().toDouble() == 0.0) {
                                    btnDialogAdd.text = getString(R.string.remove_from_box)
                                    btnDialogAdd.isEnabled = true
                                } else if (amount != 0.0) {
                                    btnDialogAdd.text = getString(R.string.add_to_box)
                                    btnDialogAdd.isEnabled = true
                                } else {
                                    btnDialogAdd.isEnabled = editable.toString().toDouble() != 0.0
                                }
                            } else {
                                edDialogWasteWeight.error = getString(R.string.weight_error)
                                btnDialogAdd.isEnabled = false
                            }
                        }
                    })

                    btnDialogAdd.isEnabled = false
                    btnDialogAdd.setOnClickListener {
                        if (btnDialogAdd.text == getString(R.string.add_to_box)) {
                            if (isFromBucket) {
                                viewModel.addToWasteBucket(
                                    wasteType, edDialogWasteWeight.text.toString().toDouble()
                                )
                            } else {

                                viewModel.addToWasteBox(
                                    wasteType, edDialogWasteWeight.text.toString().toDouble()
                                )
                            }
                        } else if (btnDialogAdd.text == getString(R.string.remove_from_box)) {
                            if (isFromBucket) {
                                viewModel.deleteFromWasteBucket(
                                    wasteType, edDialogWasteWeight.text.toString().toDouble()
                                )
                            } else {

                                viewModel.deleteFromWasteBox(
                                    wasteType,
                                    edDialogWasteWeight.text.toString().toDouble()
                                )
                            }
                        }
                        dialog.dismiss()
                    }
                }
            }

            tvDialogPricePerUnit.text = getString(
                R.string.price_per_unit_value,
                wasteType.pricePerUnit.toString(),
                wasteType.unit
            )

            chipDialogAdd.setOnClickListener {
                var newValue = 0.0
                if (edDialogWasteWeight.text.isNotEmpty()) {
                    newValue = edDialogWasteWeight.text.toString().toDouble() + 1
                } else {
                    newValue++
                }
                edDialogWasteWeight.setText(newValue.toString())
            }

            chipDialogMinus.setOnClickListener {
                var newValue = 0.0
                if (edDialogWasteWeight.text.isNotEmpty()) {
                    newValue = edDialogWasteWeight.text.toString().toDouble() - 1
                }
                if (newValue < 0) {
                    edDialogWasteWeight.setText(getString(R.string.initial_weight))
                } else {
                    edDialogWasteWeight.setText(newValue.toString())
                }
            }

            btnCloseDialog.setOnClickListener {
                dialog.dismiss()
            }
        }
        dialog.show()
    }

    companion object {
        const val PARTNER_ID = "partner_id"
        const val IS_FROM_BUCKET = "is_from_bucket"
    }
}