package com.sobolev.bank_card_app_alfa_25.data.mapper

import com.sobolev.bank_card_app_alfa_25.data.local.models.BinInfoEntity
import com.sobolev.bank_card_app_alfa_25.domain.entitites.BankInfo
import com.sobolev.bank_card_app_alfa_25.domain.entitites.BinInfo
import com.sobolev.bank_card_app_alfa_25.domain.entitites.CountryInfo

// from Domain to Entity
fun BinInfo.toEntity(bin: String) = BinInfoEntity(
    id = bin,
    scheme = scheme,
    type = type,
    brand = brand,
    prepaid = prepaid,
    countryName = country?.name,
    countryEmoji = country?.emoji,
    latitude = country?.latitude,
    longitude = country?.longitude,
    bankName = bank?.name,
    bankUrl = bank?.url,
    bankPhone = bank?.phone,
    bankCity = bank?.city
)

// from Entity to Domain
fun BinInfoEntity.toDomain() = BinInfo(
    number = null,
    scheme = scheme,
    type = type,
    brand = brand,
    prepaid = prepaid,
    country = CountryInfo(countryName, countryEmoji, latitude, longitude),
    bank = BankInfo(bankName, bankUrl, bankPhone, bankCity)
)
