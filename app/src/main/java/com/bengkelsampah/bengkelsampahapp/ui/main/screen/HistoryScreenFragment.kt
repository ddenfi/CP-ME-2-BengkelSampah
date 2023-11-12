package com.bengkelsampah.bengkelsampahapp.ui.main.screen

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bengkelsampah.bengkelsampahapp.R
import com.bengkelsampah.bengkelsampahapp.databinding.FragmentHistoryScreenBinding
import com.bengkelsampah.bengkelsampahapp.domain.model.HistoryModel
import com.bengkelsampah.bengkelsampahapp.ui.adapter.HistoryAdapter
import com.bengkelsampah.bengkelsampahapp.ui.history.HistoryDetailActivity
import com.bengkelsampah.bengkelsampahapp.ui.main.HistoryUiState
import com.bengkelsampah.bengkelsampahapp.ui.main.MainViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import kotlinx.coroutines.launch

class HistoryScreenFragment : Fragment() {
    private var _binding: FragmentHistoryScreenBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels()

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

        val filterChipGroup = bottomSheetView.findViewById<ChipGroup>(R.id.chip_group_status)

        binding.btnStatusFilter.setOnClickListener {
            bottomSheetDialog.show()
            filterChipGroup.setOnCheckedStateChangeListener { _, _ ->
                val checkedId = filterChipGroup.checkedChipId
                if (checkedId != View.NO_ID) {
                    val checkedText = bottomSheetView.findViewById<Chip>(checkedId).text.toString()
                    binding.btnStatusFilter.text = checkedText
                }
                bottomSheetDialog.dismiss()
            }

            bottomSheetView.findViewById<Button>(R.id.btn_reset_filter).setOnClickListener {
                filterChipGroup.clearCheck()
                binding.btnStatusFilter.text = getString(R.string.all_status)
                bottomSheetDialog.dismiss()
            }
        }
    }

    private fun setUpHistoryList() {
        val historyAdapter = HistoryAdapter { history -> adapterOnClick(history) }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.historyUiState.collect { historyUiState ->
                when (historyUiState) {
                    is HistoryUiState.Success -> {
                        binding.shimmerHistory.visibility = View.GONE
                        binding.rvHistory.visibility = View.VISIBLE
                        binding.rvHistory.apply {
                            layoutManager = LinearLayoutManager(context)
                            adapter = historyAdapter
                        }
                        historyAdapter.submitList(historyUiState.history)
                    }

                    is HistoryUiState.Loading -> {
                        binding.rvHistory.visibility = View.GONE
                        binding.shimmerHistory.visibility = View.VISIBLE
                    }

                    is HistoryUiState.Error -> {
                        Toast.makeText(context, historyUiState.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun adapterOnClick(history: HistoryModel) {
        val intent = Intent(this.requireContext(), HistoryDetailActivity::class.java)
        intent.putExtra(HistoryDetailActivity.HISTORY_ID, history.id)
        startActivity(intent)
    }
}