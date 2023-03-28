package com.backend.csi5175.converters

import androidx.room.TypeConverter
import com.backend.csi5175.model.Order
import com.backend.csi5175.model.Product
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ListOfDoubleConverter {
    @TypeConverter
    fun convertDoubleList(list: List<Double>?): String {
        val gson = Gson()

        return gson.toJson(list)
    }

    @TypeConverter
    fun convertStringToDoubleList(value: String): List<Double>? {
        val listType = object : TypeToken<ArrayList<Double>?>() {}.type
        return Gson().fromJson(value, listType)
    }
}