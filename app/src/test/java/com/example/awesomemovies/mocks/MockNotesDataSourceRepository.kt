package com.barriosartola.awesomeapp.mocks

import com.barriosartola.awesomeapp.data.model.Note
import com.barriosartola.awesomeapp.data.repository.NotesSourceRepository

class MockNotesDataSourceRepository : NotesSourceRepository {
    override suspend fun getNotes(): List<Note> {
        return listOf()
    }
}