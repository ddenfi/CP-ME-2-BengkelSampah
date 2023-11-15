package com.bengkelsampah.bengkelsampahapp.di

import com.bengkelsampah.bengkelsampahapp.data.repository.DriverHistoryRepositoryImpl
import com.bengkelsampah.bengkelsampahapp.data.repository.HistoryRepositoryImpl
import com.bengkelsampah.bengkelsampahapp.data.repository.NewsRepositoryImpl
import com.bengkelsampah.bengkelsampahapp.data.repository.PickupWasteRepositoryImpl
import com.bengkelsampah.bengkelsampahapp.data.repository.UserRepositoryImpl
import com.bengkelsampah.bengkelsampahapp.domain.repository.DriverHistoryRepository
import com.bengkelsampah.bengkelsampahapp.domain.repository.HistoryRepository
import com.bengkelsampah.bengkelsampahapp.domain.repository.NewsRepository
import com.bengkelsampah.bengkelsampahapp.domain.repository.PickupWasteRepository
import com.bengkelsampah.bengkelsampahapp.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [DataStoreModule::class])
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    fun bindsNewsRepository(
        newsRepositoryImpl: NewsRepositoryImpl = NewsRepositoryImpl()
    ): NewsRepository

    @Binds
    fun bindsUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): UserRepository

    @Binds
    fun bindsHistoryRepository(
        historyRepositoryImpl: HistoryRepositoryImpl = HistoryRepositoryImpl()
    ): HistoryRepository

    @Binds
    fun bindsDriverHistoryRepository(
        driverHistoryRepository: DriverHistoryRepositoryImpl = DriverHistoryRepositoryImpl()
    ): DriverHistoryRepository

    @Binds
    fun bindsPickupWasteRepository(pickupWasteRepository: PickupWasteRepositoryImpl = PickupWasteRepositoryImpl()): PickupWasteRepository

}