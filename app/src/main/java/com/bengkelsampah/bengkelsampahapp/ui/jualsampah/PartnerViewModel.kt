package com.bengkelsampah.bengkelsampahapp.ui.jualsampah

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.bengkelsampah.bengkelsampahapp.data.source.remote.response.Resource
import com.bengkelsampah.bengkelsampahapp.domain.repository.PartnerRepository
import kotlinx.coroutines.Dispatchers
import java.util.UUID

class PartnerViewModel(private val repository: PartnerRepository) : ViewModel() {
    fun getPartner() = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        try {
            emit(Resource.success(repository.getPartner()))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error Occurred!"))
        }
    }

    fun getPartnerById(partnerId: UUID) = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        try {
            emit(Resource.success(repository.getPartnerById(partnerId)))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error Occurred!"))
        }
    }
}