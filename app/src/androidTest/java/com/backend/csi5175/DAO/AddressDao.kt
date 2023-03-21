package com.backend.csi5175.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.backend.csi5175.model.Address

@Dao
interface AddressDao {

    @Query("SELECT * FROM Address")
    fun getAllAddress(): List<Address>

    @Query("SELECT * FROM Address WHERE zipcode LIKE :zipcode LIMIT 1")
    fun findByZipcode(zipcode: String): Address

    @Query("SELECT * FROM Address WHERE zipcode LIKE :zipcode AND country LIKE :country LIMIT 1")
    fun findByCountryAndZipcode(zipcode: String): Address

    @Insert
    fun insertAll(vararg addresses: Address)

    @Delete
    fun delete(address: Address)
}