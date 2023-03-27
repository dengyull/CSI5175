package com.backend.csi5175.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ListOfStringConverter {
    @TypeConverter
    fun convertStringList(list: List<String>?): String {
        val gson = Gson()

        return gson.toJson(list)
    }

    @TypeConverter
    fun convertStringToDoubleList(value: String): List<String>? {
        val listType = object : TypeToken<ArrayList<String>?>() {}.type
        return Gson().fromJson(value, listType)
    }
}