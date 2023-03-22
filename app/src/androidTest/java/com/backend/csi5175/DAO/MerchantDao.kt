package com.backend.csi5175.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.backend.csi5175.model.Merchant

@Dao
interface MerchantDao {
    @Query("SELECT * FROM merchant")
    fun getAllMerchant(): List<Merchant>

    @Query("SELECT * FROM merchant WHERE mid = :mid LIMIT 1")
    fun findMerchantById(mid: Int): Merchant

    @Insert
    fun insertAll(vararg merchants: Merchant)

    @Delete
    fun delete(merchant: Merchant)
}