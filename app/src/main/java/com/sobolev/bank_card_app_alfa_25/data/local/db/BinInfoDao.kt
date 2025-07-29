package com.sobolev.bank_card_app_alfa_25.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sobolev.bank_card_app_alfa_25.data.local.models.BinInfoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BinInfoDao {

    @Query("SELECT * FROM bin_info ORDER BY id DESC")
    fun getAll(): Flow<List<BinInfoEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(binInfo: BinInfoEntity)

    @Query("DELETE FROM bin_info")
    suspend fun clearHistory()

}