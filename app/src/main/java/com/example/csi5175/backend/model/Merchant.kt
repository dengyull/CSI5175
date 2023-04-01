package com.example.csi5175.backend.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "merchant")
data class Merchant (
    @PrimaryKey(autoGenerate = true)
    var mid: Int = 0,
    @ColumnInfo
    var products: List<Product>,
    @ColumnInfo("phone_number")
    var phoneNumber: Int,
    @ColumnInfo
    var rate: Double,
    @ColumnInfo
    var address: Address,
)