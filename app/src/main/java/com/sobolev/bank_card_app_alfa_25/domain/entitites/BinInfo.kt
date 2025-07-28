package com.sobolev.bank_card_app_alfa_25.domain.entitites


data class BinInfo(
    val number: CardNumberInfo?,
    val scheme: String?,
    val type: String?,
    val brand: String?,
    val prepaid: Boolean?,
    val country: CountryInfo?,
    val bank: BankInfo?
)

data class CardNumberInfo(
    val length: Int?,
    val luhn: Boolean?
)

data class CountryInfo(
    val name: String?,
    val emoji: String?,
    val latitude: Double?,
    val longitude: Double?
)

data class BankInfo(
    val name: String?,
    val url: String?,
    val phone: String?,
    val city: String?
)
