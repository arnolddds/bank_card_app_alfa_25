package com.sobolev.bank_card_app_alfa_25.data.network.dto

import com.google.gson.annotations.SerializedName


data class BinInfoDto(
    @SerializedName("number") val number: NumberDto?,
    @SerializedName("scheme") val scheme: String?,
    @SerializedName("type") val type: String?,
    @SerializedName("brand") val brand: String?,
    @SerializedName("prepaid") val prepaid: Boolean?,
    @SerializedName("country") val country: CountryDto?,
    @SerializedName("bank") val bank: BankDto?
)

data class NumberDto(
    @SerializedName("length") val length: Int?,
    @SerializedName("luhn") val luhn: Boolean?
)

data class CountryDto(
    @SerializedName("name") val name: String?,
    @SerializedName("emoji") val emoji: String?,
    @SerializedName("latitude") val latitude: Double?,
    @SerializedName("longitude") val longitude: Double?
)

data class BankDto(
    @SerializedName("name") val name: String?,
    @SerializedName("url") val url: String?,
    @SerializedName("phone") val phone: String?,
    @SerializedName("city") val city: String?
)
