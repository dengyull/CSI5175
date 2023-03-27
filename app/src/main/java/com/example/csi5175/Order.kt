package com.example.csi5175

import java.time.LocalDateTime

class Order (
    val oid: Int,
    val user: User,
    val date: LocalDateTime,
    val list: List<Product>,
    val price: Double
)
