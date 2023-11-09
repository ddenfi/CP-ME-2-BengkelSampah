package com.bengkelsampah.bengkelsampahapp.di

import android.content.Context
import androidx.room.Room
import com.bengkelsampah.bengkelsampahapp.data.source.local.room.BsDatabase
import com.bengkelsampah.bengkelsampahapp.data.source.local.room.KeranjangkuDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {
    @Provides
    @Singleton
    fun providesBsDatabase(@ApplicationContext context: Context): BsDatabase = Room.databaseBuilder(
        context, BsDatabase::class.java, "bs-database"
    ).build()

    @Provides
    fun providesKeranjangkuDao(
        database: BsDatabase
    ): KeranjangkuDao = database.keranjangkuDao()

}