package com.barriosartola.awesomeapp.data.service

import com.barriosartola.awesomeapp.data.model.Note
import retrofit2.http.GET

interface NoteService {
    @GET("notes")
    suspend fun getNotes(): List<Note>
}
