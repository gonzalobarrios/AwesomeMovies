package com.barriosartola.awesomeapp.data.repository.notes

import com.barriosartola.awesomeapp.data.model.Note

interface NotesDataStore {

    suspend fun getNotes(): List<Note>
}