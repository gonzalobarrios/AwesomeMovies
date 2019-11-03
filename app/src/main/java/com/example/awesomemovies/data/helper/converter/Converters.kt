package com.barriosartola.awesomeapp.data.helper.converter

import androidx.room.TypeConverter
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter

class Converters {
    @TypeConverter
    fun fromTimestamp(value: String) = ZonedDateTime.parse(value)

    @TypeConverter
    fun dateToTimestamp(date: ZonedDateTime) = DateTimeFormatter.ISO_ZONED_DATE_TIME.format(date)
}
