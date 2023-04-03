package com.example.csi5175.backend.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.csi5175.backend.model.Product
import java.util.Date

@Entity(tableName = "order_table")
data class Order (
    @PrimaryKey(autoGenerate = true)
    var oid: Int = 0,
    @ColumnInfo
    var uid: Int,
    @ColumnInfo("date_time")
    var dateTime: Date,
    @ColumnInfo
    var list: List<Product>,
    @ColumnInfo
    var price: Double,
)