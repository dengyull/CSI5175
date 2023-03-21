package com.backend.csi5175.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.backend.csi5175.DAO.*
import com.backend.csi5175.model.*

@Database(
    entities = [Address::class, Merchant::class, Order::class, Product::class, User::class],
    version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao() : UserDao
    abstract fun addressDao(): AddressDao
    abstract fun orderDao(): OrderDao
    abstract fun productDao(): ProductDao
    abstract fun merchantDao(): MerchantDao
}