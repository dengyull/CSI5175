package com.backend.csi5175.tests

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.backend.csi5175.DAO.ProductDao
import com.backend.csi5175.model.Product
import com.backend.csi5175.persistence.AppDatabase
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ProductControllerTest {
    lateinit var db: AppDatabase
    lateinit var productDao: ProductDao

    @Before
    fun setup() {
        // Memory database is created for testing purpose only
        // In the real application, the db should be created by call AppDatabase.getAppDatabase(context = ...) in the main activity upon onCreate
        // Once db is created, to interact with db, it is needed to acquire the dao from the db.
        db = Room.inMemoryDatabaseBuilder(context = ApplicationProvider.getApplicationContext(), AppDatabase::class.java).build()
        productDao = db.productDao()
        val product1 = Product(pid = 12, mid = 1, image = null, pname = "camera", description = "good cam", category = "electronic", quantity = 20, price = 43123.1)
        val product2 = Product(pid = 34, mid = 2, image = null, pname = "watch", description = "good watch", category = "electronic", quantity = 20, price = 123.2)
        productDao.insert(product1)
        productDao.insert(product2)
    }

    @After
    fun clean() {
        db.close()
    }

    @Test
    fun testFindByPid() {
        Assert.assertEquals(productDao.findProductByPid(12).pname, "camera")
        Assert.assertEquals(productDao.findProductByPid(34).pname, "watch")
    }

    @Test
    fun testUpdate() {
        productDao.updateProduct(Product(pid = 12, mid = 1, image = null, pname = "wallet", description = "good wallet", category = "personal item", quantity = 20, price = 43123.1))
        Assert.assertEquals(productDao.findProductByPid(12).pname, "wallet")
    }

    @Test
    fun testDelete() {
        Assert.assertEquals(productDao.getAllProduct().size, 2)
        productDao.delete(12)
        Assert.assertEquals(productDao.getAllProduct().size, 1)
        productDao.delete(34)
        Assert.assertEquals(productDao.getAllProduct().size, 0)
    }
}