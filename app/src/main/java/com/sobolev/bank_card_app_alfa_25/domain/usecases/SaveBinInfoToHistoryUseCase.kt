package com.sobolev.bank_card_app_alfa_25.domain.usecases

import com.sobolev.bank_card_app_alfa_25.domain.entitites.BinInfo
import com.sobolev.bank_card_app_alfa_25.domain.repository.BinInfoRepository

class SaveBinInfoToHistoryUseCase(private val repository: BinInfoRepository) {
    suspend operator fun invoke(binInfo: BinInfo) {
        repository.saveBinInfoToHistory(binInfo)
    }
}