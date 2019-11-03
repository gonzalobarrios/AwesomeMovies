package com.barriosartola.awesomeapp.data.repository.notes

import com.barriosartola.awesomeapp.data.dao.NoteDao
import com.barriosartola.awesomeapp.data.helper.networking.NetworkingManager
import com.barriosartola.awesomeapp.data.service.NoteService

@Suppress("UNUSED_PARAMETER")
open class NotesDataStoreFactory(
    var service: NoteService,
    var dao: NoteDao,
    var networkingManager: NetworkingManager
) {

    open var notesDataStoreFactory: NotesDataStore
        get() {
            return createDataSourceFactory()
        }
        set(value) {}

    private fun createDataSourceFactory() = if (networkingManager.isNetworkOnline()) {
        CloudNotesDataStore(service)
    } else {
        DatabaseNotesDataStore(dao)
    }
}