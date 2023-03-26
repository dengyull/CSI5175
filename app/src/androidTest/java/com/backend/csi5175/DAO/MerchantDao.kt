package com.backend.csi5175.DAO

import androidx.room.*
import com.backend.csi5175.model.Merchant
import com.backend.csi5175.model.Order

@Dao
interface MerchantDao {
    @Query("SELECT * FROM merchant")
    fun getAllMerchant(): List<Merchant>

    @Query("SELECT * FROM merchant WHERE mid = :mid LIMIT 1")
    fun findMerchantById(mid: Int): Merchant

    @Update
    fun updateMerchantInfo(merchant: Merchant)

    @Insert
    fun insertAll(vararg merchants: Merchant)

    @Query("DELETE FROM merchant where mid = :mid")
    fun delete(mid: Int)
}