package com.backend.csi5175.persistence

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

object DatabaseManager {
    private const val DB_NAME = "app_database.db"
    private lateinit var application: Application
    val db: AppDatabase by lazy{
        Room.databaseBuilder(application.applicationContext, AppDatabase::class.java, DB_NAME)
            .addCallback(CreateCallBack).build()
    }

    private object CreateCallBack: RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {

        }
    }
}