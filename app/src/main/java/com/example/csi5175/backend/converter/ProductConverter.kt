package com.example.csi5175.backend.converter

import androidx.room.TypeConverter
import com.example.csi5175.backend.model.Product
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlin.collections.ArrayList

class ProductConverter {

    @TypeConverter
    fun convertProductList(list: List<Product>): String {
        val gson = Gson()

        return gson.toJson(list)
    }

    @TypeConverter
    fun revertProductList(value: String): List<Product> {
        val listType = object : TypeToken<ArrayList<Product?>?>() {}.type
        return Gson().fromJson(value, listType)
    }




}