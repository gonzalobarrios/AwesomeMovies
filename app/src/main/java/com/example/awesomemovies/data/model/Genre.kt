package com.example.awesomemovies.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = arrayOf(
    Index(value = ["id"],
        unique = true)
))
data class Genre(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String
) {

}