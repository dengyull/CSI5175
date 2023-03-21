package com.backend.csi5175.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User (
    @PrimaryKey
    var uid: Int,
    @ColumnInfo
    var email: String,
    @ColumnInfo
    var password: String,
    @ColumnInfo(name = "first_name")
    var firstName: String,
    @ColumnInfo(name = "last_name")
    var lastName: String,
    @ColumnInfo
    var phone: Int,
    @ColumnInfo
    var address: Address,
    @ColumnInfo
    var history: List<Order>,
    @ColumnInfo
    var favorite: List<Product>,
    @ColumnInfo
    var cart: List<Product>,
)