package com.sobolev.bank_card_app_alfa_25.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sobolev.bank_card_app_alfa_25.data.local.models.BinInfoEntity

@Database(
    entities = [BinInfoEntity::class],
    version = 1,
    exportSchema = false
)
abstract class BinInfoDatabase : RoomDatabase() {
    abstract fun binInfoDao(): BinInfoDao
}