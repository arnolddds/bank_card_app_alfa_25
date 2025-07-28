package com.sobolev.bank_card_app_alfa_25.domain.usecases

import com.sobolev.bank_card_app_alfa_25.domain.entitites.BinInfo
import com.sobolev.bank_card_app_alfa_25.domain.repository.BinInfoRepository

class GetBinInfoUseCase(private val repository: BinInfoRepository) {
    suspend operator fun invoke(bin: String): BinInfo {
        return repository.getBinInfo(bin)
    }
}