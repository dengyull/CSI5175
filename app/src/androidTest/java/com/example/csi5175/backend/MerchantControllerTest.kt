package com.example.csi5175.backend

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.csi5175.backend.dao.MerchantDao
import com.example.csi5175.backend.model.Address
import com.example.csi5175.backend.model.Merchant
import com.example.csi5175.backend.model.Product
import com.example.csi5175.backend.persistence.AppDatabase
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class MerchantControllerTest {
    lateinit var db: AppDatabase
    lateinit var merchantDao: MerchantDao

    @Before
    fun setup() {
        // Memory database is created for testing purpose only
        // In the real application, the db should be created by call AppDatabase.getAppDatabase(context = ...) in the main activity upon onCreate
        // Once db is created, to interact with db, it is needed to acquire the dao from the db.
        db = Room.inMemoryDatabaseBuilder(context = ApplicationProvider.getApplicationContext(), AppDatabase::class.java).build()
        merchantDao = db.merchantDao()
        val product1 = Product(mid = 0, image = null, pname = "camera", description = "good cam", category = "electronic", quantity = 20, price = 43123.1, calories = null, label = null, sold = 0)
        val l1 = ArrayList<Product>()
        val l2 = ArrayList<Product>()
        l1.add(product1)
        val product2 = Product(mid = 1, image = null, pname = "watch", description = "good watch", category = "electronic", quantity = 20, price = 123.2, calories = null, label = null, sold = 0)
        l2.add(product2)
        val address1 = Address(country = "USA", state = "PA", zipcode = "15222", city = "Pittsburgh", street = "316 4th Ave")
        val address2 = Address(country = "Canada", state = "ON", zipcode = "K1S0X7", city = "Ottawa", street = "47 Laurier St")
        val merchant1 = Merchant(
            phoneNumber = 12345,
            rate = 4.5,
            address = address1,
            products = l1
        )
        val merchant2 = Merchant(
            phoneNumber = 453543,
            rate = 3.2,
            address = address2,
            products = l2
        )
        merchantDao.insertAll(merchant1)
        merchantDao.insertAll(merchant2)
    }

    @After
    fun clean() {
        db.close()
    }

    @Test
    fun testAdd() {
        Assert.assertEquals(merchantDao.getAllMerchant().size, 2)
    }

    @Test
    fun testFindByMerchantId() {
        Assert.assertEquals(merchantDao.findMerchantById(1).phoneNumber, 12345)
        Assert.assertEquals(merchantDao.findMerchantById(2).phoneNumber, 453543)
    }

    @Test
    fun testDelete() {
        Assert.assertEquals(merchantDao.getAllMerchant().size, 2)
        val product1 = Product(pid = 12, mid = 1, image = null, pname = "camera", description = "good cam", category = "electronic", quantity = 20, price = 43123.1, calories = null, label = null, sold = 0)
        val l1 = ArrayList<Product>()
        l1.add(product1)
        merchantDao.delete(1)
        Assert.assertEquals(merchantDao.getAllMerchant().size, 1)
    }
}