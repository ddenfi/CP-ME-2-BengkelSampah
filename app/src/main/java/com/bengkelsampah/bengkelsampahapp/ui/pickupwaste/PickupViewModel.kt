package com.bengkelsampah.bengkelsampahapp.ui.pickupwaste

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bengkelsampah.bengkelsampahapp.data.source.Resource
import com.bengkelsampah.bengkelsampahapp.data.source.asResource
import com.bengkelsampah.bengkelsampahapp.domain.model.WasteOrderModel
import com.bengkelsampah.bengkelsampahapp.domain.repository.PickupWasteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class PickupViewModel @Inject constructor(
    private val pickupWasteRepository: PickupWasteRepository
) : ViewModel() {

    val wasteOrders = pickupWasteRepository.getAllOrders().asResource().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        initialValue = Resource.Loading()
    )

    fun getOrderById(ids: Int): StateFlow<Resource<WasteOrderModel>> =
        pickupWasteRepository.getOrderById(ids).asResource().stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000), Resource.Loading()
        )

}