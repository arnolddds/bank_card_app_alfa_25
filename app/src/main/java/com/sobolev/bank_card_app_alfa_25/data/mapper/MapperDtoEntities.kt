package com.sobolev.bank_card_app_alfa_25.data.mapper

import com.sobolev.bank_card_app_alfa_25.data.network.dto.BinInfoDto
import com.sobolev.bank_card_app_alfa_25.domain.entitites.BankInfo
import com.sobolev.bank_card_app_alfa_25.domain.entitites.BinInfo
import com.sobolev.bank_card_app_alfa_25.domain.entitites.CardNumberInfo
import com.sobolev.bank_card_app_alfa_25.domain.entitites.CountryInfo


fun BinInfoDto.toDomain(): BinInfo {
    return BinInfo(
        number = number?.let {
            CardNumberInfo(it.length, it.luhn)
        },
        scheme = scheme,
        type = type,
        brand = brand,
        prepaid = prepaid,
        country = country?.let {
            CountryInfo(it.name, it.emoji, it.latitude, it.longitude)
        },
        bank = bank?.let {
            BankInfo(it.name, it.url, it.phone, it.city)
        }
    )
}