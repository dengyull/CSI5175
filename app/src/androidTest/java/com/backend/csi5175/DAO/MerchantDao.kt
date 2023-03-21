package com.backend.csi5175.DAO

import androidx.room.Dao
import androidx.room.Query
import com.backend.csi5175.model.Merchant

@Dao
interface MerchantDao {
    @Query("SELECT * FROM Merchant")
    fun getAllMerchant(): List<Merchant>

    @Query("SELECT * FROM Merchant WHERE mid LIKE :mid LIMIT 1")
    fun findMerchantById(mid: Int): Merchant
}