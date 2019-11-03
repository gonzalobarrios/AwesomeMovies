package com.barriosartola.awesomeapp.presentation.view.home.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.barriosartola.awesomeapp.data.model.Note
import com.barriosartola.awesomeapp.data.repository.NotesSourceRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class NotesViewModel(private val repository: NotesSourceRepository) : ViewModel(), CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    val notes: LiveData<List<Note>>
        get() = localNotes
    val isLoading: LiveData<Boolean>
        get() = localIsLoading

    private val localNotes = MutableLiveData<List<Note>>()
    private val localIsLoading = MutableLiveData<Boolean>()

    fun loadNotes() {
        localIsLoading.postValue(true)
        launch(Dispatchers.IO) {
            try {
                val notes = repository.getNotes()
                localNotes.postValue(notes)
            } catch (error: Exception) {

            } finally {
                localIsLoading.postValue(false)
            }
        }
    }
}
