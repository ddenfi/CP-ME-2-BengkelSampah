package com.bengkelsampah.bengkelsampahapp.ui.driver

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bengkelsampah.bengkelsampahapp.data.source.Resource
import com.bengkelsampah.bengkelsampahapp.data.source.asResource
import com.bengkelsampah.bengkelsampahapp.domain.repository.DriverHistoryRepository
import com.bengkelsampah.bengkelsampahapp.domain.repository.NewsRepository
import com.bengkelsampah.bengkelsampahapp.domain.repository.PickupWasteRepository
import com.bengkelsampah.bengkelsampahapp.domain.repository.UserRepository
import com.bengkelsampah.bengkelsampahapp.ui.main.DashboardUiState
import com.bengkelsampah.bengkelsampahapp.ui.main.NewsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DriverMainViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val driverHistoryRepository: DriverHistoryRepository,
    private val pickupWasteRepository: PickupWasteRepository,
    private val newsRepository: NewsRepository
) : ViewModel() {

    val dashboardUiState = dashboardUiState().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        DriverDashboardUiState.Loading
    )

    val newsUiState = newsUiState().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        NewsUiState.Loading
    )

    val orderHistory = driverHistoryRepository.getOrdersHistory().map {
        it.reversed()
    }.asResource().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000), Resource.Loading()
    )

    private fun dashboardUiState(): Flow<DriverDashboardUiState> {
        return userRepository.userData.combine(pickupWasteRepository.getAllOrders()) { userData, activeTransaction ->
            DriverDashboardUiState.Success(userData, activeTransaction)
        }
    }

    private fun newsUiState(): Flow<NewsUiState> {
        return newsRepository.getNews().asResource().map { newsRes ->
            when (newsRes) {
                is Resource.Error -> NewsUiState.Error(newsRes.exception?.message)
                is Resource.Loading -> NewsUiState.Loading
                is Resource.Success -> NewsUiState.Success(newsRes.data)
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            userRepository.setLoginStatus(false)
        }
    }
}