package com.backend.csi5175.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "order_table")
data class Order (
    @PrimaryKey
    @ColumnInfo
    var oid: Int,
    @ColumnInfo
    var uid: Int,
    @ColumnInfo("date_time")
    var dateTime: Date,
    @ColumnInfo
    var list: List<Product>,
    @ColumnInfo
    var price: Double,
)