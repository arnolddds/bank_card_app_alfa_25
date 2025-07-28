package com.sobolev.bank_card_app_alfa_25.di

import android.content.Context
import androidx.room.Room
import com.sobolev.bank_card_app_alfa_25.data.local.db.BinInfoDao
import com.sobolev.bank_card_app_alfa_25.data.local.db.BinInfoDatabase
import com.sobolev.bank_card_app_alfa_25.data.repository.BinInfoRepositoryImpl
import com.sobolev.bank_card_app_alfa_25.domain.repository.BinInfoRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    @Singleton
    fun bindBinInfoRepository(
        impl: BinInfoRepositoryImpl
    ): BinInfoRepository

    companion object {

        @Provides
        @Singleton
        fun provideDatabase(@ApplicationContext context: Context): BinInfoDatabase {
            return Room.databaseBuilder(
                context,
                BinInfoDatabase::class.java,
                "bin_info.db"
            ).build()
        }

        @Provides
        @Singleton
        fun provideBinInfoDao(database: BinInfoDatabase): BinInfoDao {
            return database.binInfoDao()
        }
    }
}
