package com.backend.csi5175.converters

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