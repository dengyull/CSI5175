package com.example.csi5175.backend

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.csi5175.backend.dao.UserDao
import com.example.csi5175.backend.model.*
import com.example.csi5175.backend.model.Address
import com.example.csi5175.backend.model.User
import com.example.csi5175.backend.persistence.AppDatabase
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class UserControllerTest {
    lateinit var db: AppDatabase
    lateinit var userDao: UserDao

    @Before
    fun setup() {
        // Memory database is created for testing purpose only
        // In the real application, the db should be created by call AppDatabase.getAppDatabase(context = ...) in the main activity upon onCreate
        // Once db is created, to interact with db, it is needed to acquire the dao from the db.
        db = Room.inMemoryDatabaseBuilder(context = ApplicationProvider.getApplicationContext(), AppDatabase::class.java).build()
        userDao = db.userDao()
        val address1 = Address(country = "USA", state = "PA", zipcode = "15222", city = "Pittsburgh", street = "316 4th Ave")
        val address2 = Address(country = "Canada", state = "ON", zipcode = "K1S0X7", city = "Ottawa", street = "47 Laurier St")
        val u1 = User(email = "123@gmail.com", password = "123", firstName = "uz", lastName = "aa", phone = 123123, address = address1, history = null, favorite = null, cart = null)
        val u2 = User(email = "112321323@outlook.com", password = "123sa", firstName = "fs", lastName = "xc", phone = 4343524, address = address2, history = null, favorite = null, cart = null)
        userDao.insert(u1)
        userDao.insert(u2)

    }

    @After
    fun clean() {
        db.close()
    }

    @Test
    fun testAdd() {
        Assert.assertEquals(userDao.getAllUsers().size, 2)
    }

    @Test
    fun testFindByMerchantId() {
        Assert.assertEquals(userDao.findUserByUid(1).lastName, "aa")
        Assert.assertEquals(userDao.findUserByUid(2).lastName, "xc")
    }

    @Test
    fun testFindUserById() {
        Assert.assertEquals(userDao.findUserByUid(1).lastName, "aa")
        Assert.assertEquals(userDao.findUserByUid(2).lastName, "xc")
    }

    @Test
    fun testUpdateUserInfo() {
        val address1 = Address(country = "USA", state = "CA", zipcode = "97123", city = "San Jose", street = "312 El Camino Ave")
        userDao.updateUserInfo(
            User(uid = 1, email = "123@gmail.com", password = "123", firstName = "uz", lastName = "aa", phone = 123123, address = address1, history = null, favorite = null, cart = null)
        )
        Assert.assertEquals(userDao.findUserByUid(1).address.state, "CA")
    }

    @Test
    fun testDelete() {
        Assert.assertEquals(userDao.getAllUsers().size, 2)
        userDao.delete(1)
        Assert.assertEquals(userDao.getAllUsers().size, 1)
        userDao.delete(2)
        Assert.assertEquals(userDao.getAllUsers().size, 0)
    }

    @Test
    fun testGetUserByEmailAndPassword() {
        Assert.assertEquals(userDao.getUserByEmailAndPassword("123@gmail.com", "123").uid, 1)
        Assert.assertEquals(userDao.getUserByEmailAndPassword("112321323@outlook.com", "123sa").uid, 2)
    }

    @Test
    fun testCleanCart() {
        val product1 = Product(pid = 56, mid = 1, image = null, pname = "cap", description = "good cam", category = "electronic", quantity = 20, price = 43123.1, label = null, calories = null, sold = 0)
        val product2 = Product(pid = 5, mid = 1, image = null, pname = "care", description = "good cam", category = "electronic", quantity = 20, price = 43123.1, label = null, calories = null, sold = 0)
        val l1 = ArrayList<Product>()
        l1.add(product1)
        l1.add(product2)
        val address1 = Address(country = "USA", state = "PA", zipcode = "15222", city = "Pittsburgh", street = "316 4th Ave")
        val u1 = User(email = "456@gmail.com", password = "123", firstName = "u1z", lastName = "aa4", phone = 123123, address = address1, history = null, favorite = null, cart = l1)
        userDao.insert(u1)
        val u = userDao.getUserByEmailAndPassword("456@gmail.com", "123")
        Assert.assertEquals(userDao.findUserByUid(u.uid).cart?.size, 2)
        userDao.cleanCart(u.uid)
        Assert.assertEquals(userDao.findUserByUid(u.uid).cart?.size, 0)
    }

    @Test
    fun testAddCart() {
        val product1 = Product(pid = 56, mid = 1, image = null, pname = "cap", description = "good cam", category = "electronic", quantity = 20, price = 43123.1, label = null, calories = null, sold = 0)
        val product2 = Product(pid = 5, mid = 1, image = null, pname = "care", description = "good cam", category = "electronic", quantity = 20, price = 43123.1, label = null, calories = null, sold = 0)
        val l1 = ArrayList<Product>()
        l1.add(product1)
        l1.add(product2)
        val u = userDao.getUserByEmailAndPassword("123@gmail.com", "123")
        userDao.updateCart(u.uid, l1)
        Assert.assertEquals(userDao.findUserByUid(u.uid).cart?.size, 2)
    }

}