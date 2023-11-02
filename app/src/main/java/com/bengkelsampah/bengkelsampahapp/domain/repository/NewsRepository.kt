package com.bengkelsampah.bengkelsampahapp.domain.repository

import com.bengkelsampah.bengkelsampahapp.domain.model.NewsResourceModel
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getNews(): Flow<List<NewsResourceModel>>

    fun getNewsById(ids:String): Flow<NewsResourceModel>
}