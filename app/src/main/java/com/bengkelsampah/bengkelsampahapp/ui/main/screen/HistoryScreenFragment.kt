package com.bengkelsampah.bengkelsampahapp.ui.main.screen

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bengkelsampah.bengkelsampahapp.R
import com.bengkelsampah.bengkelsampahapp.databinding.FragmentHistoryScreenBinding
import com.bengkelsampah.bengkelsampahapp.domain.model.DummyData
import com.bengkelsampah.bengkelsampahapp.ui.adapter.HistoryAdapter
import com.google.android.material.bottomsheet.BottomSheetDialog

class HistoryScreenFragment : Fragment() {
    private var _binding: FragmentHistoryScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryScreenBinding.inflate(inflater, container, false)

        binding.btnStatusFilter.text = getString(R.string.all_status)
        setUpFilterBottomSheet()
        setUpHistoryList()

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    @SuppressLint("InflateParams")
    private fun setUpFilterBottomSheet() {
        val bottomSheetView = layoutInflater.inflate(R.layout.bottom_sheet_filter, null)
        val bottomSheetDialog = BottomSheetDialog(this.requireContext())
        bottomSheetDialog.setContentView(bottomSheetView)

        binding.btnStatusFilter.setOnClickListener {
            bottomSheetDialog.show()
        }
    }

    private fun setUpHistoryList() {
        val historyAdapter = HistoryAdapter()
        val dummyHistoryData = DummyData.generateDummyData()

        binding.rvHistory.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = historyAdapter
        }

        historyAdapter.submitList(dummyHistoryData)
    }
}