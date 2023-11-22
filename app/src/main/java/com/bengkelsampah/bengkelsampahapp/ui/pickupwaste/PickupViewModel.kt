package com.bengkelsampah.bengkelsampahapp.ui.pickupwaste

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bengkelsampah.bengkelsampahapp.data.source.Resource
import com.bengkelsampah.bengkelsampahapp.data.source.asResource
import com.bengkelsampah.bengkelsampahapp.domain.model.WasteOrderModel
import com.bengkelsampah.bengkelsampahapp.domain.repository.PickupWasteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PickupViewModel @Inject constructor(
    private val pickupWasteRepository: PickupWasteRepository
) : ViewModel() {

    private val _searchText = MutableStateFlow("")
    private val searchText = _searchText.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    val wasteSearchResult = searchText.debounce(100).flatMapLatest {
        pickupWasteRepository.searchWaste(it)
    }.asResource().stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Resource.Loading())

    val wasteOrders = pickupWasteRepository.getAllOrders().asResource().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        initialValue = Resource.Loading()
    )

    val wasteResource = pickupWasteRepository.getAllWaste().asResource().stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5000),
        initialValue = Resource.Loading()
    )

    fun onSearchQueryChange(text: String) {
        _searchText.value = text
    }

    /**
     * Get order by id. Right now we use local database to save data locally
     */
    fun getOrderById(ids: String): StateFlow<Resource<WasteOrderModel>> =
        pickupWasteRepository.getOrderById(ids).asResource().stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000), Resource.Loading()
        )

    /**
     * Simulate pick up order where data store in local database
     */
    fun pickupOrder(wasteOrderModel: WasteOrderModel): Flow<Resource<String>> = flow {
        emit(Resource.Loading())
        delay(1000)
        try {
            viewModelScope.launch {
                pickupWasteRepository.updateOrder(wasteOrderModel)
            }
            emit(Resource.Success("Pickup Success"))
        } catch (e: Exception) {
            emit(Resource.Error(e))
        }
    }

    /**
     * Simulate update order where data store in local database
     */
    fun updateOrder(wasteOrderModel: WasteOrderModel): Flow<Resource<String>> = flow {
        emit(Resource.Loading())
        delay(1000)
        try {
            viewModelScope.launch {
                pickupWasteRepository.updateOrder(wasteOrderModel)
            }
            emit(Resource.Success("Data Saved"))
        } catch (e: Exception) {
            emit(Resource.Error(e))
        }
    }


}