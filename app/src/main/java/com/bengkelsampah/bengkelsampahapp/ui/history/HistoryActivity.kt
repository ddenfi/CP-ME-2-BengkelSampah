package com.bengkelsampah.bengkelsampahapp.ui.history

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bengkelsampah.bengkelsampahapp.R
import com.bengkelsampah.bengkelsampahapp.databinding.ActivityHistoryBinding
import com.bengkelsampah.bengkelsampahapp.domain.model.DummyData
import com.bengkelsampah.bengkelsampahapp.ui.adapter.HistoryAdapter
import com.google.android.material.bottomsheet.BottomSheetDialog

class HistoryActivity : AppCompatActivity() {
    private lateinit var historyBinding: ActivityHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        historyBinding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(historyBinding.root)

        historyBinding.btnStatusFilter.text = getString(R.string.all_status)
        setUpFilterBottomSheet()
        setUpHistoryList()
        setUpBottomNavigation()
    }

    @SuppressLint("InflateParams")
    private fun setUpFilterBottomSheet() {
        val bottomSheetView = layoutInflater.inflate(R.layout.bottom_sheet_filter, null)
        val bottomSheetDialog = BottomSheetDialog(this)
        bottomSheetDialog.setContentView(bottomSheetView)

        historyBinding.btnStatusFilter.setOnClickListener {
            bottomSheetDialog.show()
        }
    }

    private fun setUpHistoryList() {
        val historyAdapter = HistoryAdapter()
        val dummyHistoryData = DummyData.generateDummyData()

        historyBinding.rvHistory.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = historyAdapter
        }

        historyAdapter.submitList(dummyHistoryData)
    }

    private fun setUpBottomNavigation() {
        historyBinding.bottomNavigation.selectedItemId = R.id.menu_history
        historyBinding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_home -> {
                    true
                }

                R.id.menu_history -> {
                    true
                }

                R.id.menu_cart -> {
                    true
                }

                R.id.menu_money_bag -> {
                    true
                }

                R.id.menu_profile -> {
                    true
                }

                else -> false
            }
        }
    }
}