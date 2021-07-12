package com.example.myapplication.data.entities

import androidx.room.TypeConverter
import java.util.*

class DateTypeConverter {
    @TypeConverter
    fun fromTimestamp(time: Long): Calendar{
        var calendar = GregorianCalendar()
        calendar.timeInMillis = time
        return calendar
    }

    @TypeConverter
    fun toTimestamp(date: Calendar): Long {
        return date.timeInMillis
    }
}
