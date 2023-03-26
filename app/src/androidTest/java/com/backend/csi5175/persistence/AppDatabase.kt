package com.backend.csi5175.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.backend.csi5175.DAO.*
import com.backend.csi5175.converters.*
import com.backend.csi5175.model.*

@Database(
    entities = [Address::class, Merchant::class, Order::class, Product::class, User::class],
    version = 1,
    exportSchema = false)
@TypeConverters(DateConverter::class, ProductConverter::class, ImageConverter::class, OrderConverter::class, AddressConverter::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao() : UserDao
    abstract fun addressDao(): AddressDao
    abstract fun orderDao(): OrderDao
    abstract fun productDao(): ProductDao
    abstract fun merchantDao(): MerchantDao

    companion object {
        var instance: AppDatabase? = null

        fun getAppDatabase(context: Context): AppDatabase? {
            if (instance == null) {
                synchronized(AppDatabase::class) {
                    instance = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "app").allowMainThreadQueries().build()
                }
            }
            return instance
        }

        fun destroy() {
            if (instance?.isOpen == true) {
                instance?.close()
            }
        }
    }
}