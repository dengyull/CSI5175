package com.example.csi5175.backend

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import com.example.csi5175.backend.dao.MerchantDao
import com.example.csi5175.backend.dao.ProductDao
import com.example.csi5175.backend.model.Address
import com.example.csi5175.backend.model.Merchant
import com.example.csi5175.backend.model.Product
import com.example.csi5175.backend.persistence.AppDatabase
import com.example.csi5175.test.R
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.io.ByteArrayOutputStream

class ProductControllerTest {
    lateinit var db: AppDatabase
    lateinit var productDao: ProductDao
    lateinit var merchantDao: MerchantDao

    @Before
    fun setup() {
        // Memory database is created for testing purpose only
        // In the real application, the db should be created by call AppDatabase.getAppDatabase(context = ...) in the main activity upon onCreate
        // Once db is created, to interact with db, it is needed to acquire the dao from the db.
        db = Room.inMemoryDatabaseBuilder(context = ApplicationProvider.getApplicationContext(), AppDatabase::class.java).build()
        productDao = db.productDao()
        val labelList1 = ArrayList<String>()
        val labelList2 = ArrayList<String>()
        labelList1.add("good")
        labelList1.add("electronic")
        labelList2.add("watch")
        labelList2.add("usa")
        val calories1 = ArrayList<Double>()
        val calories2 = ArrayList<Double>()
        calories1.add(123.0)
        calories1.add(350.0)
        calories2.add(0.0)
        calories2.add(0.0)
        val product1 = Product(pid = 12, mid = 1, image = null, pname = "camera", description = "good cam", category = "electronic", quantity = 20, price = 43123.1, label = labelList1, calories = calories1, sold = 1)
        val product2 = Product(pid = 34, mid = 2, image = null, pname = "watch", description = "good watch", category = "electronic", quantity = 20, price = 123.2, label = labelList2, calories = calories2, sold = 2)
        merchantDao = db.merchantDao()
        val l1 = ArrayList<Product>()
        val l2 = ArrayList<Product>()
        l1.add(product1)
        l2.add(product2)
        val address1 = Address(country = "USA", state = "PA", zipcode = "15222", city = "Pittsburgh", street = "316 4th Ave")
        val address2 = Address(country = "Canada", state = "ON", zipcode = "K1S0X7", city = "Ottawa", street = "47 Laurier St")
        val merchant1 = Merchant(
            mid = 1,
            phoneNumber = 12345,
            rate = 4.5,
            address = address1,
            products = l1
        )
        val merchant2 = Merchant(
            mid = 2,
            phoneNumber = 453543,
            rate = 3.2,
            address = address2,
            products = l2
        )
        merchantDao.insertAll(merchant1)
        merchantDao.insertAll(merchant2)
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
    fun testFindAllProductsByMerchant() {
        val product1 = productDao.findAllProductsByMerchant(1)
        val product2 = productDao.findAllProductsByMerchant(2)
        Assert.assertEquals(product1.size, 1)
        Assert.assertEquals(product2.size, 1)
        Assert.assertEquals(product1.first().pid, 12)
        Assert.assertEquals(product2.first().pid, 34)
    }

    @Test
    fun testGetProductWithPrefix() {
        val product1 = Product(pid = 56, mid = 1, image = null, pname = "cap", description = "good cam", category = "electronic", quantity = 20, price = 43123.1, label = null, calories = null, sold = 0)
        productDao.insert(product1)
        val product2 = Product(pid = 5, mid = 1, image = null, pname = "care", description = "good cam", category = "electronic", quantity = 20, price = 43123.1, label = null, calories = null, sold = 0)
        productDao.insert(product2)
        Assert.assertEquals(productDao.getProductsWithPrefix("ca").size, 3)
        Assert.assertEquals(productDao.getProductsWithPrefix("wa").size, 1)
    }

    @Test
    fun testUpdate() {
        productDao.updateProduct(Product(pid = 12, mid = 1, image = null, pname = "wallet", description = "good wallet", category = "personal item", quantity = 20, price = 43123.1, calories = null, label = null, sold = 0))
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

    @Test
    fun testGetFirstKSoldItems() {
        val product3 = Product(pid = 78, mid = 1, image = null, pname = "camera", description = "good cam", category = "electronic", quantity = 20, price = 43123.1, label = null, calories = null, sold = 3)
        val product4 = Product(pid = 9, mid = 1, image = null, pname = "camera", description = "good cam", category = "electronic", quantity = 20, price = 43123.1, label = null, calories = null, sold = 4)
        productDao.insert(product3, product4)
        Assert.assertEquals(productDao.getFirstMostPopularProduct(2).size, 2)
        Assert.assertEquals(productDao.getFirstMostPopularProduct(2).first().pid, 9)
        Assert.assertEquals(productDao.getFirstMostPopularProduct(2).get(1).pid, 78)
    }

    @Test
    fun testAddSold() {
        Assert.assertEquals(productDao.findProductByPid(34).sold, 2)
        productDao.addSoldById(34)
        Assert.assertEquals(productDao.findProductByPid(34).sold, 3)
    }

}