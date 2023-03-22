package com.backend.csi5175.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.backend.csi5175.model.Merchant
import com.backend.csi5175.model.Order
import com.backend.csi5175.model.Product

@Dao
interface ProductDao {
    @Query("SELECT * FROM product WHERE pid = :pid LIMIT 1")
    fun findProductByPid(pid: Int): Product

    @Insert
    fun insert(vararg products: Product)

    @Delete
    fun delete(product: Product)
}