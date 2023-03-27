package com.backend.csi5175.DAO

import androidx.room.*
import com.backend.csi5175.model.Address
import com.backend.csi5175.model.Merchant
import com.backend.csi5175.model.Order
import com.backend.csi5175.model.Product

@Dao
interface ProductDao {
    @Query("SELECT * FROM product WHERE pid = :pid LIMIT 1")
    fun findProductByPid(pid: Int): Product

    @Query("SELECT * FROM product")
    fun getAllProduct(): List<Product>

    @Query("SELECT * FROM product WHERE mid = :mid")
    fun findAllProductsByMerchant(mid: Int): List<Product>

    @Update
    fun updateProduct(product: Product)

    @Insert
    fun insert(vararg products: Product)

    @Query("DELETE FROM product WHERE pid = :pid")
    fun delete(pid: Int)
}