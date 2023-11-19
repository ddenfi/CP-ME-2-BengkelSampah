package com.bengkelsampah.bengkelsampahapp.ui.pickupwaste

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import cn.pedant.SweetAlert.SweetAlertDialog
import com.bengkelsampah.bengkelsampahapp.R
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
import kotlinx.coroutines.launch

class PickupAddWasteActivity:AppCompatActivity() {
    private lateinit var binding: ActivityAddWasteBinding
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        binding = ActivityAddWasteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.topAppBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        getWasteType()

        binding.fabWasteBox.setOnClickListener {
            val wasteBoxIntent = Intent(this, WasteBoxActivity::class.java)
            wasteBoxIntent.putExtra(
                WasteBoxActivity.PARTNER_ID, intent.getStringExtra(
                    AddWasteActivity.PARTNER_ID
                ))
            startActivity(wasteBoxIntent)
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

        }
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