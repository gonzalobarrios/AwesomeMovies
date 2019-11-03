package com.barriosartola.awesomeapp.data.repository

import com.barriosartola.awesomeapp.data.model.Note
import com.barriosartola.awesomeapp.data.repository.notes.NotesDataStoreFactory

class NotesSourceDataRepository(var factory: NotesDataStoreFactory) : NotesSourceRepository {

    override suspend fun getNotes(): List<Note> {
        return factory.notesDataStoreFactory.getNotes()
    }

}