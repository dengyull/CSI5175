package com.example.csi5175.backend.dao

import androidx.room.*
import com.example.csi5175.backend.model.Product

@Dao
interface ProductDao {
    @Query("SELECT * FROM product WHERE pid = :pid LIMIT 1")
    fun findProductByPid(pid: Int): Product

    @Query("SELECT * FROM product")
    fun getAllProduct(): List<Product>

    @Query("SELECT * FROM product WHERE product_name LIKE :prefix || '%'")
    fun getProductsWithPrefix(prefix: String): List<Product>

    @Query("SELECT * FROM product ORDER BY sold DESC LIMIT :k ")
    fun getFirstMostPopularProduct(k: Int): List<Product>

    @Query("SELECT * FROM product WHERE mid = :mid")
    fun findAllProductsByMerchant(mid: Int): List<Product>

    @Query("UPDATE product SET calories =:newCalories WHERE pid =:pid")
    fun updateCalories(pid: Int, newCalories: List<Double>)

    @Query("UPDATE product SET label =:newLabel WHERE pid =:pid")
    fun updateLabel(pid: Int, newLabel: List<Double>)

    @Update
    fun updateProduct(product: Product)

    @Query("UPDATE product SET sold = sold + 1 WHERE pid =:pid")
    fun addSoldById(pid: Int)

    @Insert
    fun insert(vararg products: Product)

    @Query("DELETE FROM product WHERE pid = :pid")
    fun delete(pid: Int)
}