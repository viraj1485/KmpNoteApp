package org.example.project.notes.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assessmenttask.snackbarsetup.SnackbarAction
import com.example.assessmenttask.snackbarsetup.SnackbarController
import com.example.assessmenttask.snackbarsetup.SnackbarEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.project.database.NoteDao
import org.example.project.database.NoteEntity
import org.example.project.notes.data.AddNoteScreenState
import org.example.project.notes.domain.AddNoteScreenEvent

class NoteViewModel(
    private val noteDao: NoteDao
) : ViewModel() {

    private val _addNoteScreenState = MutableStateFlow(AddNoteScreenState())
    val addNoteScreenState = _addNoteScreenState.asStateFlow()

    val notes = noteDao.getAllNotes().stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5000),
        emptyList()
    )


    fun onEvent(event: AddNoteScreenEvent) {
        when (event) {
            AddNoteScreenEvent.OnColorChange -> {

            }

            is AddNoteScreenEvent.OnEditClick -> {
                if (_addNoteScreenState.value.title.isNotEmpty() && _addNoteScreenState.value.subtitle.isNotEmpty()) {
                    println("note edited")
                    updateNote(
                        event.id, _addNoteScreenState.value.title,
                        _addNoteScreenState.value.subtitle,
                        _addNoteScreenState.value.date
                    )
                }
            }

            AddNoteScreenEvent.OnSaveButtonClick -> {
                if (_addNoteScreenState.value.title.isNotEmpty() &&
                    _addNoteScreenState.value.subtitle.isNotEmpty()
                ) {
                    saveNote(
                        _addNoteScreenState.value.title,
                        _addNoteScreenState.value.subtitle,
                        _addNoteScreenState.value.date
                    )
                }
            }

            is AddNoteScreenEvent.SubtitleChange -> {
                _addNoteScreenState.update {
                    it.copy(
                        subtitle = event.subtitle
                    )
                }
            }

            is AddNoteScreenEvent.TitleChange -> {
                _addNoteScreenState.update {
                    it.copy(
                        title = event.title
                    )
                }
            }

            is AddNoteScreenEvent.OnDeleteClick -> {
                deleteNote(event.id)
            }


            is AddNoteScreenEvent.OnDateSelected -> {
                println("dsgndslfg ${event.date}")
                _addNoteScreenState.update {
                    it.copy(
                        date = event.date
                    )
                }
            }
        }
    }

    private fun saveNote(title: String, subtitle: String, date: String) {
        viewModelScope.launch {
            noteDao.insetNote(
                NoteEntity(title = title, subtitle = subtitle, date = date)
            )
            _addNoteScreenState.update {
                AddNoteScreenState()
            }
            showSnackbar()
        }
    }

    fun deleteNote(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val note = notes.value.firstOrNull { it.id == id }
            note?.let { note ->
                noteDao.deleteNote(
                    note
                )
                showSnackbar("Note Deleted")
            } ?: run { return@launch }
        }
    }

    fun getNote(noteId: Int?) {
        viewModelScope.launch {
            val note = notes.value.firstOrNull { it.id == noteId }
            note?.let { note ->
                _addNoteScreenState.update {
                    it.copy(
                        title = note.title,
                        subtitle = note.subtitle,
                        date = note.date
                    )
                }
            }
        }
    }

    private fun updateNote(id: Int, title: String, subtitle: String, date: String) {
        viewModelScope.launch {
            noteDao.updateNoteById(
                id = id,
                title = title,
                subtitle = subtitle,
                date = date
            )
            _addNoteScreenState.update {
                AddNoteScreenState()
            }
            showSnackbar("Note Updated")
        }
    }

    fun clearState() {
        _addNoteScreenState.update {
            AddNoteScreenState()
        }
    }

    fun showSnackbar(msg: String = "Note saved successfully") {
        viewModelScope.launch {
            SnackbarController.sendEvent(
                event = SnackbarEvent(
                    message = msg,
                    action = null
                )
            )
        }
    }

}