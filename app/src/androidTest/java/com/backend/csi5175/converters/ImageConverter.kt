package com.backend.csi5175.converters

import android.media.Image
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ImageConverter {
    @TypeConverter
    fun imageToString(value: Image?): String {
        val gson = Gson()
        return gson.toJson(value)
    }

    @TypeConverter
    fun stringToImage(value: String): Image? {
        val type = object : TypeToken<Image>(){}.type
        return Gson().fromJson(value, type)
    }
}