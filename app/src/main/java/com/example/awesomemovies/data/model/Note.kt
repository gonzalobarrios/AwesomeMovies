package com.barriosartola.awesomeapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import org.threeten.bp.ZonedDateTime

@Entity
data class Note(
    @PrimaryKey val id: Int,
    @ColumnInfo val content: String,
    @ColumnInfo(name = "created_by") val createdBy: String,
    @ColumnInfo(name = "created_at") val createdAt: ZonedDateTime,
    @ColumnInfo(name = "updated_at") val updatedAt: ZonedDateTime
) {
    constructor(id: Int, content: String, createdBy: String) : this(
        id,
        content,
        createdBy,
        ZonedDateTime.now(),
        ZonedDateTime.now()
    )
}
