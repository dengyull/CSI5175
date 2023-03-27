package com.backend.csi5175.tests

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.backend.csi5175.DAO.OrderDao
import com.backend.csi5175.model.Order
import com.backend.csi5175.model.Product
import com.backend.csi5175.persistence.AppDatabase
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.util.*
import kotlin.collections.ArrayList

class OrderControllerTest {
    lateinit var db: AppDatabase
    lateinit var orderDao: OrderDao

    @Before
    fun setup() {
        // Memory database is created for testing purpose only
        // In the real application, the db should be created by call AppDatabase.getAppDatabase(context = ...) in the main activity upon onCreate
        // Once db is created, to interact with db, it is needed to acquire the dao from the db.
        db = Room.inMemoryDatabaseBuilder(context = ApplicationProvider.getApplicationContext(), AppDatabase::class.java).build()
        orderDao = db.orderDao()
        val product1 = Product(pid = 12, mid = 1, image = null, pname = "camera", description = "good cam", category = "electronic", quantity = 20, price = 43123.1, calories = null, label = null)
        val l1 = ArrayList<Product>()
        val l2 = ArrayList<Product>()
        l1.add(product1)
        val product2 = Product(pid = 34, mid = 2, image = null, pname = "watch", description = "good watch", category = "electronic", quantity = 20, price = 123.2, calories = null, label = null)
        l2.add(product2)
        val order1 = Order(oid = 12354, uid = 0, dateTime = Date(), price = 12312.23, list = l1)
        val order2 = Order(oid = 54355, uid = 1, dateTime = Date(), price = 3123.3, list = l2)
        orderDao.insert(order1)
        orderDao.insert(order2)
    }

    @After
    fun clean() {
        db.close()
    }

    @Test
    fun testAdd() {
        Assert.assertEquals(orderDao.getAllOrders().size, 2)
    }

    @Test
    fun testFindOrderByOid() {
        Assert.assertEquals(orderDao.findOrderByOid(12354).uid, 0)
        Assert.assertEquals(orderDao.findOrderByOid(54355).uid, 1)
    }

    @Test
    fun testFindAllOrdersByUser() {
        val order1 = orderDao.findAllOrdersByUser(0)
        val order2 = orderDao.findAllOrdersByUser(1)
        Assert.assertEquals(order1.size, 1)
        Assert.assertEquals(order2.size, 1)
        Assert.assertEquals(order1.first().oid, 12354)
        Assert.assertEquals(order2.first().oid, 54355)
    }

    @Test
    fun testDelete() {
        Assert.assertEquals(orderDao.getAllOrders().size, 2)
        orderDao.delete(12354)
        Assert.assertEquals(orderDao.getAllOrders().size, 1)
        orderDao.delete(54355)
        Assert.assertEquals(orderDao.getAllOrders().size, 0)
    }
}