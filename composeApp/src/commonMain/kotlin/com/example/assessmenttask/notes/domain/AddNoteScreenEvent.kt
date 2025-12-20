package org.example.project.notes.domain

sealed class AddNoteScreenEvent {
    data class TitleChange(val title: String) : AddNoteScreenEvent()
    data class SubtitleChange(val subtitle: String) : AddNoteScreenEvent()

    data object OnSaveButtonClick : AddNoteScreenEvent()
    data class OnEditClick(val id:Int) : AddNoteScreenEvent()
    data object OnColorChange : AddNoteScreenEvent()
    data class OnDeleteClick(val id:Int) : AddNoteScreenEvent()
    data class OnDateSelected(val date:String) : AddNoteScreenEvent()
}