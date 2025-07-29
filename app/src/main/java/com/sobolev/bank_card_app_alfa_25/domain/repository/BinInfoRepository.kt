package com.sobolev.bank_card_app_alfa_25.domain.repository

import com.sobolev.bank_card_app_alfa_25.domain.entitites.BinInfo
import kotlinx.coroutines.flow.Flow


interface BinInfoRepository {

    suspend fun getBinInfo(bin: String): BinInfo

    fun getSearchHistory(): Flow<List<BinInfo>>

    suspend fun saveBinInfoToHistory(binInfo: BinInfo)

    suspend fun clearHistory()

}