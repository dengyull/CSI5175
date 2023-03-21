package com.backend.csi5175.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Order (
    @PrimaryKey
    @ColumnInfo
    var oid: Int,
    @ColumnInfo
    var user: User,
    @ColumnInfo("date_time")
    var dateTime: Date,
    @ColumnInfo
    var list: List<Product>,
    @ColumnInfo
    var price: Double,
)