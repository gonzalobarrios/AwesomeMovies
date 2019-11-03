package com.barriosartola.awesomeapp.data.source

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.barriosartola.awesomeapp.data.dao.NoteDao
import com.barriosartola.awesomeapp.data.helper.converter.Converters
import com.barriosartola.awesomeapp.data.model.Note

@Database(entities = [Note::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao

    companion object {
        private val LOG_TAG = AppDatabase::class.java.canonicalName
        private val LOCK = Any()
        private val DATABASE_NAME = "personlist"
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