package com.example.csi5175.backend.converter

import androidx.room.TypeConverter
import com.example.csi5175.backend.model.Address
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class AddressConverter {
    @TypeConverter
    fun addressToString(address: Address): String {
        val gson = Gson()
        return gson.toJson(address)
    }

    @TypeConverter
    fun stringToUser(value: String): Address {
        val type = object : TypeToken<Address>() {}.type
        return Gson().fromJson(value, type)
    }

}