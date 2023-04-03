package com.example.csi5175.backend.dao

import androidx.room.*
import com.example.csi5175.backend.model.Order
import com.example.csi5175.backend.model.Product


@Dao
interface OrderDao {
    @Query("SELECT * FROM order_table WHERE oid = :oid LIMIT 1")
    fun findOrderByOid(oid: Int): Order

    @Query("SELECT * FROM order_table")
    fun getAllOrders(): List<Order>

    @Query("SELECT * FROM order_table WHERE uid = :uid")
    fun findAllOrdersByUser(uid: Int): List<Order>

    @Query("UPDATE order_table SET list =:newList WHERE oid =:oid")
    fun updateProductList(oid: Int, newList: List<Product>)

    @Update
    fun updateOrder(order: Order)

    @Insert
    fun insert(vararg orders: Order)

    @Query("DELETE FROM order_table WHERE oid = :oid")
    fun delete(oid: Int)
}