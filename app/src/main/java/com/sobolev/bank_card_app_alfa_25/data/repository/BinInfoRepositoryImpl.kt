package com.sobolev.bank_card_app_alfa_25.data.repository

import com.sobolev.bank_card_app_alfa_25.data.local.db.BinInfoDao
import com.sobolev.bank_card_app_alfa_25.data.mapper.toDomain
import com.sobolev.bank_card_app_alfa_25.data.mapper.toEntity
import com.sobolev.bank_card_app_alfa_25.data.network.ApiService
import com.sobolev.bank_card_app_alfa_25.domain.entitites.BinInfo
import com.sobolev.bank_card_app_alfa_25.domain.repository.BinInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BinInfoRepositoryImpl(
    private val apiService: ApiService,
    private val binInfoDao: BinInfoDao
) : BinInfoRepository {

    override suspend fun getBinInfo(bin: String): BinInfo {
        val response = apiService.getBinInfo(bin)
        val domainModel = response.toDomain()
        binInfoDao.insert(domainModel.toEntity(bin))
        return domainModel
    }

    override fun getSearchHistory(): Flow<List<BinInfo>> {
        return binInfoDao.getAll().map { list -> list.map { it.toDomain() } }
    }

    override suspend fun saveBinInfoToHistory(binInfo: BinInfo) {
        binInfoDao.insert(binInfo.toEntity(bin = binInfo.scheme ?: System.currentTimeMillis().toString()))
    }
}
