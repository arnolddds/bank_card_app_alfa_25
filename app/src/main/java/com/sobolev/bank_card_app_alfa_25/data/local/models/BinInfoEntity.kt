package com.sobolev.bank_card_app_alfa_25.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bin_info")
data class BinInfoEntity(
    @PrimaryKey val id: String,
    val scheme: String?,
    val type: String?,
    val brand: String?,
    val prepaid: Boolean?,
    val countryName: String?,
    val countryEmoji: String?,
    val latitude: Double?,
    val longitude: Double?,
    val bankName: String?,
    val bankUrl: String?,
    val bankPhone: String?,
    val bankCity: String?
)