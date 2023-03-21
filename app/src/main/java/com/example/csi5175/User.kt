package com.example.csi5175



class User (
    val uid: Int,
    val email: String,
    val password: String,
    val firstName: String,
    val lastName: String,
    val phone: Int,
    val address: Address,
    val history: List<Order>,
    val favorite: List<Product>,
    val cart: MutableList<Product>
)