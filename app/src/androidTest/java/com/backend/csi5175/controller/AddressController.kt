package com.backend.csi5175.controller

import com.backend.csi5175.model.Address
import com.backend.csi5175.persistence.AppDatabase;
import com.backend.csi5175.persistence.DatabaseManager.db


class AddressController {

    fun addAddress(address: Address) {
        db.addressDao().insertAll(address)
    }

    fun findAllAddress(): List<Address> {
        return db.addressDao().getAllAddress();
    }

    fun findByZipcode(zipcode: String): Address {
        return db.addressDao().findByZipcode(zipcode);
    }

    fun findByCountryAndZipcode(zipcode: String, country: String): Address {
        return db.addressDao().findByCountryAndZipcode(zipcode, country)
    }

    fun delete(address: Address) {
        db.addressDao().delete(address)
    }



}