package com.bengkelsampah.bengkelsampahapp.di

import com.bengkelsampah.bengkelsampahapp.data.repository.BankSampahRepositoryImpl
import com.bengkelsampah.bengkelsampahapp.data.repository.DriverHistoryRepositoryImpl
import com.bengkelsampah.bengkelsampahapp.data.repository.HistoryRepositoryImpl
import com.bengkelsampah.bengkelsampahapp.data.repository.MoneybagRepositoryImpl
import com.bengkelsampah.bengkelsampahapp.data.repository.NewsRepositoryImpl
import com.bengkelsampah.bengkelsampahapp.data.repository.PickupWasteRepositoryImpl
import com.bengkelsampah.bengkelsampahapp.data.repository.PartnerRepositoryImpl
import com.bengkelsampah.bengkelsampahapp.data.repository.UserRepositoryImpl
import com.bengkelsampah.bengkelsampahapp.data.repository.WasteBoxRepositoryImpl
import com.bengkelsampah.bengkelsampahapp.domain.repository.BankSampahRepository
import com.bengkelsampah.bengkelsampahapp.domain.repository.DriverHistoryRepository
import com.bengkelsampah.bengkelsampahapp.domain.repository.HistoryRepository
import com.bengkelsampah.bengkelsampahapp.domain.repository.MoneybagRepository
import com.bengkelsampah.bengkelsampahapp.domain.repository.NewsRepository
import com.bengkelsampah.bengkelsampahapp.domain.repository.PickupWasteRepository
import com.bengkelsampah.bengkelsampahapp.domain.repository.PartnerRepository
import com.bengkelsampah.bengkelsampahapp.domain.repository.UserRepository
import com.bengkelsampah.bengkelsampahapp.domain.repository.WasteBoxRepository
import dagger.Binds
import dagger.Module
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
        historyRepositoryImpl: HistoryRepositoryImpl
    ): HistoryRepository

    @Binds
    fun bindsWasteBoxRepository(wasteBoxRepositoryImpl: WasteBoxRepositoryImpl): WasteBoxRepository

    @Binds
    fun bindsDriverHistoryRepository(
        driverHistoryRepository: DriverHistoryRepositoryImpl
    ): DriverHistoryRepository

    @Binds
    fun bindsPickupWasteRepository(
        pickupWasteRepository: PickupWasteRepositoryImpl
    ): PickupWasteRepository


    @Binds
    fun bindsPartnerRepository(
        partnerRepositoryImpl: PartnerRepositoryImpl = PartnerRepositoryImpl()
    ): PartnerRepository

    @Binds
    fun bindsMoneybagRepository(
        moneybagRepositoryImpl: MoneybagRepositoryImpl = MoneybagRepositoryImpl()
    ): MoneybagRepository

    @Binds
    fun bindsBankSampahRepository(
        bankSampahRepositoryImpl: BankSampahRepositoryImpl = BankSampahRepositoryImpl()
    ): BankSampahRepository
}