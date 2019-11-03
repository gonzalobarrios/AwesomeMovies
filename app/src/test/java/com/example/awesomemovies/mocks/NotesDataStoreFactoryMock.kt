package com.barriosartola.awesomeapp.mocks

import com.barriosartola.awesomeapp.data.dao.NoteDao
import com.barriosartola.awesomeapp.data.helper.networking.NetworkingManager
import com.barriosartola.awesomeapp.data.repository.notes.NotesDataStoreFactory
import com.barriosartola.awesomeapp.data.service.NoteService

class NotesDataStoreFactoryMock(
    service: NoteService,
    dao: NoteDao,
    networkingManager: NetworkingManager
) : NotesDataStoreFactory(service, dao, networkingManager) {


}