package com.example.csi5175

import android.media.Image


class Product (
    val pid: Int,
    val mid: Int,
    val pname: String,
    val price: Double,
    val description: String,
    val image: Image,
    val category: String,
    var quantity: Int
)
