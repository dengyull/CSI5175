package com.backend.csi5175.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.backend.csi5175.model.Address

@Dao
interface AddressDao {

    @Query("SELECT * FROM address")
    fun getAllAddress(): List<Address>

    @Query("SELECT * FROM address WHERE zipcode = :zipcode LIMIT 1")
    fun findByZipcode(zipcode: String): Address

    @Query("SELECT * FROM address WHERE zipcode = :zipcode AND country = :country LIMIT 1")
    fun findByCountryAndZipcode(zipcode: String, country: String): Address

    @Insert
    fun insertAll(vararg addresses: Address)

    @Delete
    fun delete(address: Address)
}