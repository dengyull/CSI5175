package com.backend.csi5175.DAO

import androidx.room.*
import com.backend.csi5175.model.Order

@Dao
interface OrderDao {
    @Query("SELECT * FROM order_table WHERE oid = :oid LIMIT 1")
    fun findOrderByOid(oid: Int): Order

    @Update
    fun updateOrder(order: Order)

    @Insert
    fun insert(vararg orders: Order)

    @Delete
    fun delete(order: Order)
}