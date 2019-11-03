package com.barriosartola.awesomeapp.mocks

import com.barriosartola.awesomeapp.data.model.Note
import com.barriosartola.awesomeapp.data.service.NoteService

open class NotesServiceMock : NoteService {

    override suspend fun getNotes(): List<Note> {
        return listOf()
    }
}