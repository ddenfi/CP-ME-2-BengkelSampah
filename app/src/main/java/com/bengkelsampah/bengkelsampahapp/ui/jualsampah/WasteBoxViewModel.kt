package com.bengkelsampah.bengkelsampahapp.ui.jualsampah

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bengkelsampah.bengkelsampahapp.data.source.Resource
import com.bengkelsampah.bengkelsampahapp.data.source.asResource
import com.bengkelsampah.bengkelsampahapp.data.source.remote.response.partner.PartnerById
import com.bengkelsampah.bengkelsampahapp.domain.model.OrderStatus
import com.bengkelsampah.bengkelsampahapp.domain.model.WasteBoxModel
import com.bengkelsampah.bengkelsampahapp.domain.model.WasteModel
import com.bengkelsampah.bengkelsampahapp.domain.model.WasteOrderModel
import com.bengkelsampah.bengkelsampahapp.domain.repository.PartnerRepository
import com.bengkelsampah.bengkelsampahapp.domain.repository.WasteBoxRepository
import com.bengkelsampah.bengkelsampahapp.utils.DateHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class WasteBoxViewModel @Inject constructor(
    private val wasteBoxRepository: WasteBoxRepository,
    private val partnerRepository: PartnerRepository
) : ViewModel() {
    private val _searchText = MutableStateFlow("")
    private val searchText = _searchText.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    val wasteSearchResult = searchText.debounce(100).flatMapLatest {
        wasteBoxRepository.searchWaste(it)
    }.asResource().stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Resource.Loading())

    fun getWasteBoxItem(): Flow<WasteBoxUiState> =
        wasteBoxRepository.getWasteBoxItems().asResource().map { resourceWasteBox ->
            when (resourceWasteBox) {
                is Resource.Success -> WasteBoxUiState.Success(resourceWasteBox.data)
                is Resource.Loading -> WasteBoxUiState.Loading
                is Resource.Error -> WasteBoxUiState.Error(resourceWasteBox.exception?.message)
            }
        }

    fun getAllWastes(): Flow<AddWasteUiState> =
        wasteBoxRepository.getAllWastes().asResource().map { resourceAddWaste ->
            when (resourceAddWaste) {
                is Resource.Success -> AddWasteUiState.Success(resourceAddWaste.data)
                is Resource.Loading -> AddWasteUiState.Loading
                is Resource.Error -> AddWasteUiState.Error(resourceAddWaste.exception?.message)
            }
        }

    fun getWasteItemById(id: String): Flow<Double> =
        wasteBoxRepository.getWasteBoxItemById(id)
            .map { it?.amount ?: 0.0 }

    fun addToWasteBox(waste: WasteModel, amount: Double) =
        wasteBoxRepository.addToWasteBox(WasteBoxModel(waste, amount))

    fun onSearchQueryChange(text: String) {
        _searchText.value = text
    }

    fun deleteFromWasteBox(waste: WasteModel, amount: Double) =
        wasteBoxRepository.deleteFromWasteBox(WasteBoxModel(waste, amount))

    fun countWasteBoxItems() = wasteBoxRepository.countWasteBoxItems().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        0
    )

    fun updateWasteBoxItem(waste: WasteModel, amount: Double) =
        wasteBoxRepository.updateWasteBoxItem(WasteBoxModel(waste, amount))

    fun getPartnerById(id: String): Flow<FormOrderUIState> =
        partnerRepository.getPartnersById(id).asResource().map { resourcePartnerById ->
            when (resourcePartnerById) {
                is Resource.Success -> FormOrderUIState.Success(resourcePartnerById.data)
                is Resource.Loading -> FormOrderUIState.Loading
                is Resource.Error -> FormOrderUIState.Error(resourcePartnerById.exception?.message)
            }
        }

    fun insertOrder(
        waste: List<WasteBoxModel>,
        partner: PartnerById?,
        address: String,
        description: String,
        total: Int
    ) =
        wasteBoxRepository.insertOrder(
            WasteOrderModel(
                id = UUID.randomUUID().toString(),
                consumerName = null,
                date = DateHelper.getCurrentDate(),
                address = address,
                pickUpDate = DateHelper.get3DaysLaterDate(),
                status = OrderStatus.WAIT_CONFIRMATION,
                agent = partner?.data?.name.toString(),
                agentAddress = partner?.data?.address.toString(),
                agentPhone = partner?.data?.phoneNumber.toString(),
                wasteBox = waste,
                total = total,
                description = description,
                driverName = null,
                distance = 5.0,
            )
        )

    fun clearWasteBox() = wasteBoxRepository.clearWasteBox()

    fun getWasteBucketItems(): Flow<WasteBoxUiState> =
        wasteBoxRepository.getWasteBucketItems().asResource().map { resourceWasteBox ->
            when (resourceWasteBox) {
                is Resource.Success -> WasteBoxUiState.Success(resourceWasteBox.data)
                is Resource.Loading -> WasteBoxUiState.Loading
                is Resource.Error -> WasteBoxUiState.Error(resourceWasteBox.exception?.message)
            }

        }

    fun addToWasteBucket(waste: WasteModel, weight: Double) =
        wasteBoxRepository.addToWasteBucket(WasteBoxModel(waste, weight))

    fun deleteFromWasteBucket(waste: WasteModel, weight: Double) =
        wasteBoxRepository.deleteFromWasteBucket(WasteBoxModel(waste, weight))

    fun updateWasteBucketItem(waste: WasteModel, weight: Double) =
        wasteBoxRepository.updateWasteBucketItem(WasteBoxModel(waste, weight))
}