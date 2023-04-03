package com.example.csi5175.backend.dao

import androidx.room.*
import com.example.csi5175.backend.model.Address

@Dao
interface AddressDao {

    @Query("SELECT * FROM address")
    fun getAllAddress(): List<Address>

    @Query("SELECT * FROM address WHERE zipcode = :zipcode LIMIT 1")
    fun findByZipcode(zipcode: String): Address

    @Query("SELECT * FROM address WHERE zipcode = :zipcode AND country = :country LIMIT 1")
    fun findByCountryAndZipcode(zipcode: String, country: String): Address

    @Update
    fun updateAddress(address: Address)

    @Insert
    fun insertAll(vararg addresses: Address)

    @Query("DELETE FROM address WHERE zipcode = :zipcode")
    fun delete(zipcode: String)
}