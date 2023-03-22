package com.backend.csi5175.tests

import com.backend.csi5175.controller.AddressController
import com.backend.csi5175.model.Address
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class AddressControllerTest {
    lateinit var addressController: AddressController
    @Before
    fun setup() {
        addressController = AddressController()
    }

    @Test
    fun testAdd() {
        val address1 = Address(country = "USA", state = "PA", zipcode = "15222", city = "Pittsburgh", street = "316 4th Ave")
        val address2 = Address(country = "Canada", state = "ON", zipcode = "K1S0X7", city = "Ottawa", street = "47 Laurier St")
        addressController.addAddress(address1)
        addressController.addAddress(address2)
        Assert.assertEquals(addressController.findAllAddress().size, 2)
    }

    @Test
    fun testFindAll() {
        Assert.assertEquals(addressController.findAllAddress().size, 2)
        Assert.assertEquals(addressController.findAllAddress().get(0).zipcode, "15222")
        Assert.assertEquals(addressController.findAllAddress().get(1).zipcode, "K1S0X7")
    }

}