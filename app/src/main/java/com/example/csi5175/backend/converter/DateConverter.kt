package com.example.csi5175.backend.converter

import androidx.room.TypeConverter
import java.util.Date

class DateConverter {
    @TypeConverter
    fun convertDate(value: Date): Long {
        return value.time
    }

    @TypeConverter
    fun revertDate(value: Long): Date {
        return Date(value)
    }
}