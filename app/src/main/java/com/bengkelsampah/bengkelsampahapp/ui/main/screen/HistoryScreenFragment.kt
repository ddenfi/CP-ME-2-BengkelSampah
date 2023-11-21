package com.bengkelsampah.bengkelsampahapp.ui.main.screen

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import cn.pedant.SweetAlert.SweetAlertDialog
import com.bengkelsampah.bengkelsampahapp.R
import com.bengkelsampah.bengkelsampahapp.databinding.FragmentHistoryScreenBinding
import com.bengkelsampah.bengkelsampahapp.domain.model.HistoryModel
import com.bengkelsampah.bengkelsampahapp.domain.model.WasteOrderModel
import com.bengkelsampah.bengkelsampahapp.ui.adapter.HistoryAdapter
import com.bengkelsampah.bengkelsampahapp.ui.history.HistoryDetailActivity
import com.bengkelsampah.bengkelsampahapp.ui.main.HistoryUiState
import com.bengkelsampah.bengkelsampahapp.ui.main.MainViewModel
import com.bengkelsampah.bengkelsampahapp.utils.MarginItemDecoration
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

        binding.chipStatusFilter.text = getString(R.string.all_status)
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

        binding.chipStatusFilter.setOnClickListener {
            bottomSheetDialog.show()
            filterChipGroup.setOnCheckedStateChangeListener { _, _ ->
                val checkedId = filterChipGroup.checkedChipId
                if (checkedId != View.NO_ID) {
                    val checkedText = bottomSheetView.findViewById<Chip>(checkedId).text.toString()
                    binding.chipStatusFilter.text = checkedText
                }
                bottomSheetDialog.dismiss()
            }

            bottomSheetView.findViewById<Button>(R.id.btn_reset_filter).setOnClickListener {
                filterChipGroup.clearCheck()
                binding.chipStatusFilter.text = getString(R.string.all_status)
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

                        if (historyUiState.history.isEmpty()) {
                            binding.tvHistoryEmpty.visibility = View.VISIBLE
                        } else {
                            binding.tvHistoryEmpty.visibility = View.GONE
                            binding.rvHistory.visibility = View.VISIBLE
                            binding.rvHistory.apply {
                                layoutManager = LinearLayoutManager(context)
                                adapter = historyAdapter
                                addItemDecoration(
                                    MarginItemDecoration(
                                        resources.getDimensionPixelSize(R.dimen.rv_margin_vertical_8),
                                        resources.getDimensionPixelSize(R.dimen.rv_margin_horizontal_16)
                                    )
                                )
                            }
                            historyAdapter.submitList(historyUiState.history)
                        }
                    }

                    is HistoryUiState.Loading -> {
                        binding.tvHistoryEmpty.visibility = View.GONE
                        binding.rvHistory.visibility = View.GONE
                        binding.shimmerHistory.visibility = View.VISIBLE
                    }

                    is HistoryUiState.Error -> {
                        val dialog = SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText(historyUiState.message)
                            .hideConfirmButton()

                        dialog.show()
                        Handler(Looper.getMainLooper()).postDelayed(
                            { dialog.dismissWithAnimation() },
                            1500
                        )
                    }
                }
            }
        }
    }

    private fun adapterOnClick(history: WasteOrderModel) {
        val intent = Intent(this.requireContext(), HistoryDetailActivity::class.java)
        intent.putExtra(HistoryDetailActivity.HISTORY_ID, history.id)
        startActivity(intent)
    }
}