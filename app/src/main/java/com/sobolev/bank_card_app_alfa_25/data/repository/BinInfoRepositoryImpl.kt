package com.sobolev.bank_card_app_alfa_25.data.repository

import com.sobolev.bank_card_app_alfa_25.data.mapper.toDomain
import com.sobolev.bank_card_app_alfa_25.data.network.ApiService
import com.sobolev.bank_card_app_alfa_25.domain.entitites.BinInfo
import com.sobolev.bank_card_app_alfa_25.domain.repository.BinInfoRepository
import kotlinx.coroutines.flow.Flow

class BinInfoRepositoryImpl(
    private val apiService: ApiService
) : BinInfoRepository {

    override suspend fun getBinInfo(bin: String): BinInfo {
        val response = apiService.getBinInfo(bin)
        return response.toDomain()
    }

    override fun getSearchHistory(): Flow<List<BinInfo>> {
        TODO("Will be implemented with Room later")
    }

    override suspend fun saveBinInfoToHistory(binInfo: BinInfo) {
        TODO("Will be implemented with Room later")
    }
}