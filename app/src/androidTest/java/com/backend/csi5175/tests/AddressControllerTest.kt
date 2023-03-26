package com.backend.csi5175.tests

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.backend.csi5175.DAO.AddressDao
import com.backend.csi5175.model.Address
import com.backend.csi5175.persistence.AppDatabase
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
class AddressControllerTest {
    // lateinit var addressController: AddressController
    lateinit var db: AppDatabase
    lateinit var addressDao: AddressDao
    @Before
    fun setup() {
        // Memory database is created for testing purpose only
        // In the real application, the db should be created by call AppDatabase.getAppDatabase(context = ...) in the main activity upon onCreate
        // Once db is created, to interact with db, it is needed to acquire the dao from the db.
        db = Room.inMemoryDatabaseBuilder(context = ApplicationProvider.getApplicationContext(), AppDatabase::class.java).build()
        addressDao = db.addressDao()
        val address1 = Address(country = "USA", state = "PA", zipcode = "15222", city = "Pittsburgh", street = "316 4th Ave")
        val address2 = Address(country = "Canada", state = "ON", zipcode = "K1S0X7", city = "Ottawa", street = "47 Laurier St")
        addressDao.insertAll(address1)
        addressDao.insertAll(address2)
    }

    @After
    fun clean() {
        db.close()
    }

    @Test
    fun testAdd() {
        Assert.assertEquals(addressDao.getAllAddress().size, 2)
    }

    @Test
    fun testFindAll() {
        Assert.assertEquals(addressDao.getAllAddress().size, 2)
        Assert.assertEquals(addressDao.getAllAddress().get(0).zipcode, "15222")
        Assert.assertEquals(addressDao.getAllAddress().get(1).zipcode, "K1S0X7")
    }

    @Test
    fun testFindByZipCode() {
        Assert.assertEquals(addressDao.findByZipcode("15222").city, "Pittsburgh")
        Assert.assertEquals(addressDao.findByZipcode("K1S0X7").city, "Ottawa")
    }

    @Test
    fun testFindByCountryAndZipCode() {
        Assert.assertEquals(addressDao.findByCountryAndZipcode("15222", "USA").state, "PA")
    }

    @Test
    fun testDelete() {
        Assert.assertEquals(addressDao.getAllAddress().size, 2)
        addressDao.delete("15222")
        Assert.assertEquals(addressDao.getAllAddress().size, 1)
    }

}