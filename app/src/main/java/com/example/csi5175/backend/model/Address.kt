package com.example.csi5175.backend.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "address")
data class Address (
    @ColumnInfo
    var country: String,
    @ColumnInfo
    var state: String,
    @ColumnInfo
    @PrimaryKey
    var zipcode: String,
    @ColumnInfo
    var city: String,
    @ColumnInfo
    var street: String,
)