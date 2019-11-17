package com.barriosartola.awesomeapp.data.source

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.barriosartola.awesomeapp.data.helper.converter.Converters
import com.example.awesomemovies.data.dao.GenreDao
import com.example.awesomemovies.data.dao.MovieDao
import com.example.awesomemovies.data.dao.MovieGenreJoinDao
import com.example.awesomemovies.data.model.Genre
import com.example.awesomemovies.data.model.Movie
import com.example.awesomemovies.data.model.MovieGenreJoin

@Database(entities = [Movie::class, Genre::class, MovieGenreJoin::class], version = 2, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract  fun movieDao(): MovieDao
    abstract  fun genreDao(): GenreDao
    abstract  fun movieGenreJoinDao(): MovieGenreJoinDao

//    abstract fun noteDao(): NoteDao

//    companion object {
//        private val LOG_TAG = AppDatabase::class.java.canonicalName
//        private val LOCK = Any()
//        private val DATABASE_NAME = "personlist"
//        private var instance: AppDatabase? = null
//
//        fun getInstance(context: Context): AppDatabase {
//            synchronized(LOCK) {
//                if (instance == null) {
//                    instance = Room.databaseBuilder<AppDatabase>(
//                        context.applicationContext,
//                        AppDatabase::class.java, DATABASE_NAME
//                    )
//                        .fallbackToDestructiveMigration()
//                        .build()
//                }
//            }
//            return instance!!
//        }
//    }

    companion object {
        private val LOG_TAG = AppDatabase::class.java.canonicalName
        private val LOCK = Any()
        private val DATABASE_NAME = "moviedb"
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            synchronized(LOCK) {
                if (instance == null) {
                    instance = Room.databaseBuilder<AppDatabase>(
                        context.applicationContext,
                        AppDatabase::class.java, DATABASE_NAME
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return instance!!
        }
    }
}