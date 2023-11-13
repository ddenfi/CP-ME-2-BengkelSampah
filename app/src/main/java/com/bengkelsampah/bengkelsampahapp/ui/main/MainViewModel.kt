package com.bengkelsampah.bengkelsampahapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bengkelsampah.bengkelsampahapp.data.source.Resource
import com.bengkelsampah.bengkelsampahapp.data.source.asResource
import com.bengkelsampah.bengkelsampahapp.domain.repository.HistoryRepository
import com.bengkelsampah.bengkelsampahapp.domain.repository.NewsRepository
import com.bengkelsampah.bengkelsampahapp.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
    private val userRepository: UserRepository,
    private val historyRepository: HistoryRepository
) : ViewModel() {

    val dashboardUiState = dashboardUiState().catch {
        emit(DashboardUiState.Error(it.toString()))
    }.stateIn(
        viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = DashboardUiState.Loading
    )

    val newsUiState = newsUiState().stateIn(
        viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = NewsUiState.Loading
    )

    val historyUiState = historyUiState().stateIn(
        viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = HistoryUiState.Loading
    )

    private fun newsUiState(): Flow<NewsUiState> =
        newsRepository.getNews().asResource().map { resourceNews ->
            when (resourceNews) {
                is Resource.Success -> NewsUiState.Success(resourceNews.data)
                is Resource.Loading -> NewsUiState.Loading
                is Resource.Error -> NewsUiState.Error(resourceNews.exception?.message)
            }
        }

    private fun dashboardUiState(): Flow<DashboardUiState> {
        return userRepository.userData.combine(historyRepository.getActiveTransaction()) { userData, activeTransaction ->
            DashboardUiState.Success(userData, activeTransaction)
        }
    }

    private fun historyUiState(): Flow<HistoryUiState> =
        historyRepository.getActiveTransaction().asResource().map { resourceHistory ->
            when (resourceHistory) {
                is Resource.Success -> HistoryUiState.Success(resourceHistory.data)
                is Resource.Loading -> HistoryUiState.Loading
                is Resource.Error -> HistoryUiState.Error(resourceHistory.exception?.message)
            }
        }

    fun logout() {
        viewModelScope.launch {
            userRepository.setLoginStatus(false)
        }
    }
}