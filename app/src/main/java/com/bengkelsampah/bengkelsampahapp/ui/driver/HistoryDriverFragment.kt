package com.bengkelsampah.bengkelsampahapp.ui.driver

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import cn.pedant.SweetAlert.SweetAlertDialog
import com.bengkelsampah.bengkelsampahapp.R
import com.bengkelsampah.bengkelsampahapp.data.source.Resource
import com.bengkelsampah.bengkelsampahapp.databinding.FragmentHistoryDriverBinding
import com.bengkelsampah.bengkelsampahapp.databinding.FragmentHomeDriverBinding
import com.bengkelsampah.bengkelsampahapp.domain.model.HistoryModel
import com.bengkelsampah.bengkelsampahapp.domain.model.WasteOrderModel
import com.bengkelsampah.bengkelsampahapp.ui.adapter.HistoryAdapter
import com.bengkelsampah.bengkelsampahapp.ui.adapter.HistoryDriverAdapter
import com.bengkelsampah.bengkelsampahapp.ui.driverhistory.HistoryDriverDetailActivity
import com.bengkelsampah.bengkelsampahapp.ui.history.HistoryDetailActivity
import com.bengkelsampah.bengkelsampahapp.ui.main.HistoryUiState
import com.bengkelsampah.bengkelsampahapp.utils.MarginItemDecoration
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HistoryDriverFragment : Fragment() {

    private var _binding: FragmentHistoryDriverBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DriverMainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryDriverBinding.inflate(inflater, container, false)

        binding.chipStatusFilter.text = getString(R.string.all_status)
        setUpFilterBottomSheet()
        setUpHistoryList()

        return binding.root
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
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
        val historyAdapter = HistoryDriverAdapter { history -> adapterOnClick(history) }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.orderHistory.collect { orderHistory ->
                    when (orderHistory) {
                        is Resource.Error -> {
                            val dialog = SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
                                .setTitleText(orderHistory.exception?.message.toString())
                                .hideConfirmButton()

                            dialog.show()
                            Handler(Looper.getMainLooper()).postDelayed(
                                { dialog.dismissWithAnimation() },
                                1500
                            )
                        }
                        is Resource.Loading -> {
                            binding.tvHistoryEmpty.visibility = View.GONE
                            binding.rvHistory.visibility = View.GONE
                            binding.shimmerHistory.visibility = View.VISIBLE
                        }

                        is Resource.Success -> {
                            binding.shimmerHistory.visibility = View.GONE

                            if (orderHistory.data.isEmpty()) {
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
                                historyAdapter.submitList(orderHistory.data)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun adapterOnClick(history: WasteOrderModel) {
        val intent = Intent(this.requireContext(), HistoryDriverDetailActivity::class.java)
        intent.putExtra(HistoryDetailActivity.HISTORY_ID, history.id)
        startActivity(intent)
    }

}