package com.example.csi5175.backend.model

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
    @PrimaryKey(autoGenerate = true)
    var pid: Int = 0,
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
    var label: List<String>?,
    @ColumnInfo
    var sold: Int
)