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
)], tableName = "product")
data class Product (
    @PrimaryKey
    @ColumnInfo
    var pid: Int,
    @ColumnInfo(index = true)
    var mid: Int,
    @ColumnInfo(name = "product_name")
    var pname: String,
    @ColumnInfo
    var price: Double,
    @ColumnInfo
    var description: String,
    @ColumnInfo
    var image: Image?,
    @ColumnInfo
    var category: String,
    @ColumnInfo
    var quantity: Int,
    @ColumnInfo
    var calories: List<Double>?,
    @ColumnInfo
    var label: List<String>?
)