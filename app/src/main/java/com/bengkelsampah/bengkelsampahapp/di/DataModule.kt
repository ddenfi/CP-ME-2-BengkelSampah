package com.bengkelsampah.bengkelsampahapp.di

import com.bengkelsampah.bengkelsampahapp.data.repository.HistoryRepositoryImpl
import com.bengkelsampah.bengkelsampahapp.data.repository.NewsRepositoryImpl
import com.bengkelsampah.bengkelsampahapp.data.repository.UserRepositoryImpl
import com.bengkelsampah.bengkelsampahapp.domain.repository.HistoryRepository
import com.bengkelsampah.bengkelsampahapp.domain.repository.NewsRepository
import com.bengkelsampah.bengkelsampahapp.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    //TODO: Make this as binds
    @Provides
    fun bindsNewsRepository():NewsRepository{
        return NewsRepositoryImpl()
    }

    @Provides
    fun bindsUserRepository():UserRepository{
        return UserRepositoryImpl()
    }

    @Provides
    fun bindsHistoryRepository():HistoryRepository = HistoryRepositoryImpl()

}