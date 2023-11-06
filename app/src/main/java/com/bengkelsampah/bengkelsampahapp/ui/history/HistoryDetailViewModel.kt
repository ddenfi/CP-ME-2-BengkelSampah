package com.bengkelsampah.bengkelsampahapp.ui.history

import androidx.lifecycle.ViewModel
import com.bengkelsampah.bengkelsampahapp.data.source.Resource
import com.bengkelsampah.bengkelsampahapp.data.source.asResource
import com.bengkelsampah.bengkelsampahapp.domain.repository.HistoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class HistoryDetailViewModel @Inject constructor(
    private val historyRepository: HistoryRepository
) : ViewModel() {
    fun getHistoryDetail(id: Int): Flow<HistoryDetailUiState> =
        historyRepository.getHistoryById(id).asResource().map { resourceHistoryDetail ->
            when (resourceHistoryDetail) {
                is Resource.Success -> HistoryDetailUiState.Success(resourceHistoryDetail.data)
                is Resource.Loading -> HistoryDetailUiState.Loading
                is Resource.Error -> HistoryDetailUiState.Error(resourceHistoryDetail.exception?.message)
            }
        }
}