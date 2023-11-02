package com.bengkelsampah.bengkelsampahapp.domain.repository

import com.bengkelsampah.bengkelsampahapp.domain.model.UserDataModel
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    val userData:Flow<UserDataModel>

    fun updateUser(data:UserDataModel)
}