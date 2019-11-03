package com.barriosartola.awesomeapp.data.repository.notes

import com.barriosartola.awesomeapp.data.model.Note
import com.barriosartola.awesomeapp.data.service.NoteService

class CloudNotesDataStore(private var noteService: NoteService) : NotesDataStore {

    override suspend fun getNotes(): List<Note> {
        return noteService.getNotes()
    }

}