package com.backend.csi5175.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "merchant")
data class Merchant (
    @PrimaryKey
    @ColumnInfo
    var mid: Int,
    @ColumnInfo
    var products: List<Product>,
    @ColumnInfo("phone_number")
    var phoneNumber: Int,
    @ColumnInfo
    var rate: Double,
    @ColumnInfo
    var address: Address,
)