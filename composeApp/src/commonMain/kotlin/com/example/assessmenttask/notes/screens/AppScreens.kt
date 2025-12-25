package org.example.project.notes.screens

import kotlinx.serialization.Serializable

@Serializable
sealed interface AppScreens {

    @Serializable
    data object TodoListScreen : AppScreens

    @Serializable
    data object NoteListScreen : AppScreens

    @Serializable
    data class AddNoteScreen(
        val id:Int? =null
    ) : AppScreens

    @Serializable
    data class NoteWebViewScreen(
        val url: String
    ) : AppScreens

    @Serializable
    data object PDfViewerScreen : AppScreens

    @Serializable
    data object PermissionScreen : AppScreens

}