package com.example.awesomemovies.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Genre(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val title: String
) {

}