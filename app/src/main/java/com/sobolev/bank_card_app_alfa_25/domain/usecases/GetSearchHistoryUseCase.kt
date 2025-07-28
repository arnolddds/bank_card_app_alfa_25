package com.sobolev.bank_card_app_alfa_25.domain.usecases

import com.sobolev.bank_card_app_alfa_25.domain.entitites.BinInfo
import com.sobolev.bank_card_app_alfa_25.domain.repository.BinInfoRepository
import kotlinx.coroutines.flow.Flow

class GetSearchHistoryUseCase(private val repository: BinInfoRepository) {
    operator fun invoke(): Flow<List<BinInfo>> {
        return repository.getSearchHistory()
    }
}