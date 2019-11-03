package com.barriosartola.awesomeapp.mocks

import com.barriosartola.awesomeapp.data.dao.NoteDao
import com.barriosartola.awesomeapp.data.model.Note

class NoteDaoMock : NoteDao {

    override suspend fun getAll(): List<Note> {
        return listOf()
    }

    override suspend fun insertAll(vararg notes: Note) {

    }

    override suspend fun delete(note: Note) {

    }

}