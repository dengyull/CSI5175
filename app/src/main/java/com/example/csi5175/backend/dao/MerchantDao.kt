package com.example.csi5175.backend.dao

import androidx.room.*
import com.example.csi5175.backend.model.Merchant

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