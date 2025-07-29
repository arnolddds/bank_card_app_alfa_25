package com.sobolev.bank_card_app_alfa_25.domain.usecases

import com.sobolev.bank_card_app_alfa_25.domain.repository.BinInfoRepository
import javax.inject.Inject

class ClearHistoryUseCase @Inject constructor(
    private val repository: BinInfoRepository
){

    suspend operator fun invoke() = repository.clearHistory()
}