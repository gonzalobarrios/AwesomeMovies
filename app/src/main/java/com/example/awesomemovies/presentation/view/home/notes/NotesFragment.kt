package com.barriosartola.awesomeapp.presentation.view.home.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.barriosartola.awesomeapp.R
import com.barriosartola.awesomeapp.data.model.Note
import com.barriosartola.awesomeapp.presentation.helper.visibleIf
import kotlinx.android.synthetic.main.fragment_notes.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class NotesFragment : Fragment() {
    private val adapter = NotesAdapter()
    private val notesViewModel: NotesViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_notes, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = this@NotesFragment.adapter
        }

        notesViewModel.loadNotes()
        notesViewModel.notes.observe(viewLifecycleOwner, Observer(this::notesLoaded))
        notesViewModel.isLoading.observe(viewLifecycleOwner, Observer(this::loadingStateChanged))
    }

    private fun notesLoaded(notes: List<Note>) {
        adapter.notes = notes
    }

    private fun loadingStateChanged(isLoading: Boolean) {
        progressBar.visibleIf(isLoading)
        recyclerView.visibleIf(!isLoading)
    }
}
