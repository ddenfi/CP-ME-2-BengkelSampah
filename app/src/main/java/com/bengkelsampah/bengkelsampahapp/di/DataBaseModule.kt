package com.bengkelsampah.bengkelsampahapp.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.bengkelsampah.bengkelsampahapp.data.source.local.room.BsDatabase
import com.bengkelsampah.bengkelsampahapp.data.source.local.room.MyBucketDao
import com.bengkelsampah.bengkelsampahapp.data.source.local.room.NewsResourceDao
import com.bengkelsampah.bengkelsampahapp.data.source.local.room.WasteBoxDao
import com.bengkelsampah.bengkelsampahapp.data.source.local.room.WasteOrderDao
import com.bengkelsampah.bengkelsampahapp.data.source.local.room.WasteResourceDao
import com.bengkelsampah.bengkelsampahapp.data.source.local.room.util.WasteOrderCallback
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Provider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {
    @Provides
    @Singleton
    fun providesBsDatabase(
        @ApplicationContext context: Context,
        provider: Provider<WasteOrderDao>
    ): BsDatabase = Room.databaseBuilder(
        context, BsDatabase::class.java, "bs-database"
    ).createFromAsset("database/BSDatabase.db").addCallback(WasteOrderCallback(provider)).build()

    @Provides
    fun providesMyBucketDao(
        database: BsDatabase
    ): MyBucketDao = database.myBucketDao()

    @Provides
    fun providesWasteBoxDao(
        database: BsDatabase
    ): WasteBoxDao = database.wasteBoxDao()

    @Provides
    fun providesWasteResourceDao(
        database: BsDatabase
    ): WasteResourceDao = database.wasteResourceDao()

    @Provides
    fun providesNewsResourceDao(
        database: BsDatabase
    ): NewsResourceDao = database.newsResourceDao()

    @Provides
    fun providesWasteOrderDao(
        database: BsDatabase
    ): WasteOrderDao = database.wasteOrderDao()

}