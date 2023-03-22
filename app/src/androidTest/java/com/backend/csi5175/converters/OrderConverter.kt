package com.backend.csi5175.converters

import androidx.room.TypeConverter
import com.backend.csi5175.model.Order
import com.backend.csi5175.model.Product
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class OrderConverter {
    @TypeConverter
    fun convertOrderList(list: List<Order>): String {
        val gson = Gson()

        return gson.toJson(list)
    }

    @TypeConverter
    fun revertOrderList(value: String): List<Order> {
        val listType = object : TypeToken<ArrayList<Order?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

}