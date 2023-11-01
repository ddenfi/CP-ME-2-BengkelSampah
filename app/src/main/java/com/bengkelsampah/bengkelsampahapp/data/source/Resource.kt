package com.bengkelsampah.bengkelsampahapp.data.source

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import java.lang.Exception
import java.security.interfaces.RSAKey

sealed class Resource<T> {
    class Success<T>(val data: T):Resource<T>()
    class Error<T>(val exception: Throwable? = null):Resource<T>()
    class Loading<T> : Resource<T>()
}

fun <T> Flow<T>.asResource(): Flow<Resource<T>> {
    return this
        .map<T, Resource<T>> {
            Resource.Success(it)
        }
        .onStart { emit(Resource.Loading()) }
        .catch { emit(Resource.Error(it)) }
}