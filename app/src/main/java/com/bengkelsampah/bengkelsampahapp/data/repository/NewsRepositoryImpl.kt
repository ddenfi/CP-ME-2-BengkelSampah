package com.bengkelsampah.bengkelsampahapp.data.repository

import com.bengkelsampah.bengkelsampahapp.domain.model.NewsResourceModel
import com.bengkelsampah.bengkelsampahapp.domain.repository.NewsRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Singleton

@Singleton
class NewsRepositoryImpl() : NewsRepository {
    /**
     * Get news from data dummy due API still on dev
     */
    override fun getNews(): Flow<List<NewsResourceModel>> = flow {
        //Simulate API Call
        delay(2000)
        emit(NewsResourceModel.dummyData)
    }

    override fun getNewsById(ids: String): Flow<NewsResourceModel> {
        TODO("Not yet implemented")
    }
}