package com.bengkelsampah.bengkelsampahapp.ui.pickupwaste

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
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
import com.bengkelsampah.bengkelsampahapp.ui.jualsampah.AddWasteActivity
import com.bengkelsampah.bengkelsampahapp.ui.jualsampah.AddWasteUiState
import com.bengkelsampah.bengkelsampahapp.ui.jualsampah.WasteBoxActivity
import com.bengkelsampah.bengkelsampahapp.utils.MarginItemDecoration
import com.bengkelsampah.bengkelsampahapp.utils.SweetAlertDialogUtils
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PickupAddWasteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddWasteBinding
    private val viewModel: PickupViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        binding = ActivityAddWasteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.topAppBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.fabWasteBox.setOnClickListener {

        }

//        binding.searchBarWaste.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                return false
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                viewModel.onSearchQueryChange(newText ?: "")
//                return true
//            }
//        })

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.wasteSearchResult.collect { searchResult ->
                    when (searchResult) {
                        is Resource.Error -> {
                            SweetAlertDialogUtils.showSweetAlertDialog(
                                this@PickupAddWasteActivity,
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
                            setUpWasteType(searchResult.data)
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

    private fun setUpWasteType(waste: List<WasteModel>) {
        val wasteTypeAdapter = WasteTypeAdapter { wasteType -> adapterOnClick(wasteType) }

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
            tvDialogWasteWeight.text = getString(R.string.initial_weight)
            tvDialogPricePerUnit.text = getString(
                R.string.price_per_unit_value,
                wasteType.pricePerUnit.toString(),
                wasteType.unit
            )

            chipDialogAdd.setOnClickListener {
                val newValue = tvDialogWasteWeight.text.toString().toInt() + 1
                tvDialogWasteWeight.text = newValue.toString()
            }

            chipDialogMinus.setOnClickListener {
                val newValue = tvDialogWasteWeight.text.toString().toInt() - 1
                tvDialogWasteWeight.text = newValue.toString()
            }

            btnCloseDialog.setOnClickListener {
                dialog.dismiss()
            }

            btnDilaogAdd.setOnClickListener {
                dialog.dismiss()
            }
        }

        dialog.show()
    }
}