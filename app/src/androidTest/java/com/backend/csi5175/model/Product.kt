package com.backend.csi5175.model

import android.media.Image
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = [ForeignKey(
    entity = Merchant::class,
    childColumns = ["mid"],
    parentColumns = ["mid"]
)])
data class Product (
    @PrimaryKey val pid: Int,
    @ColumnInfo
    var mid: Int,
    @ColumnInfo(name = "product_name")
    var pname: String,
    @ColumnInfo
    var price: Double,
    @ColumnInfo
    var description: String,
    @ColumnInfo
    var image: Image,
    @ColumnInfo
    var category: String,
    @ColumnInfo
    var quantity: Int,
)