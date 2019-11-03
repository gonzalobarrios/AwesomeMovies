package com.barriosartola.awesomeapp.data.repository

import com.barriosartola.awesomeapp.data.model.Note

interface NotesSourceRepository {

    suspend fun getNotes(): List<Note>
}