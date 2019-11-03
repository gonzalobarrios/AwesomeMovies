package com.barriosartola.awesomeapp.data.repository.notes

import com.barriosartola.awesomeapp.data.dao.NoteDao
import com.barriosartola.awesomeapp.data.model.Note

class DatabaseNotesDataStore(private val noteDao: NoteDao) : NotesDataStore {

    override suspend fun getNotes(): List<Note> {
        return noteDao.getAll()
    }
}