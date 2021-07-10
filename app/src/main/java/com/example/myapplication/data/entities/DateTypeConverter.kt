package com.example.myapplication.data.entities

import androidx.room.TypeConverter
import java.sql.Timestamp
import java.util.*

class DateTypeConverter {
    @TypeConverter
    fun fromTime(time: Timestamp): Calendar{
        var calendar = GregorianCalendar()
        calendar.timeInMillis = time.time
        return calendar
    }

    @TypeConverter
    fun toTime(date: Calendar): Timestamp {
        return Timestamp(date.timeInMillis)
    }
}