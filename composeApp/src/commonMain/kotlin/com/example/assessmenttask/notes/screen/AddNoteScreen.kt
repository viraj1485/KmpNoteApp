package org.example.project.notes.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.example.project.nativedatepicker.LocalPlatformContext
import org.example.project.nativedatepicker.pickDate
import org.example.project.notes.domain.AddNoteScreenEvent
import org.example.project.notes.resources.backgroundColor
import org.example.project.notes.resources.textColor
import org.example.project.notes.screen.components.AddNoteTopBar
import org.example.project.notes.screen.components.NoteTextField
import org.example.project.notes.viewmodel.NoteViewModel

@Composable
fun AddNoteScreen(
    viewModel: NoteViewModel,
    noteId: Int? = null,
    onBack: () -> Unit
) {
    val addNoteScreenState by viewModel.addNoteScreenState.collectAsStateWithLifecycle()
    val keyboard = LocalSoftwareKeyboardController.current
    val platformContext = LocalPlatformContext.current

    LaunchedEffect(noteId){
        viewModel.getNote(noteId)
    }

    DisposableEffect(Unit){
        onDispose {
            viewModel.clearState()
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize().background(
            backgroundColor
        ).statusBarsPadding().navigationBarsPadding(),
        containerColor = backgroundColor,
        topBar = {
            AddNoteTopBar(
                showEditIcon = noteId!=null,
                onSaveClick = {
                    if (addNoteScreenState.title.isNotBlank() && addNoteScreenState.subtitle.isNotEmpty()){
                        viewModel.onEvent(
                            AddNoteScreenEvent.OnSaveButtonClick
                        )
                        onBack()
                    }
                },
                onBackClick = {
                    keyboard?.hide()
                    onBack()
                },
                onDateIconClick = {
                    pickDate(platformContext) {
                        viewModel.onEvent(
                            AddNoteScreenEvent.OnDateSelected(it)
                        )
                    }
                },
                onEditIconClick = {
                    noteId?.let { noteId->
                        viewModel.onEvent(AddNoteScreenEvent.OnEditClick(noteId))
                        onBack()
                    }
                }
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(
                innerPadding
            ).padding(
                horizontal = 16.dp
            )
        ) {
            Spacer(modifier = Modifier.height(15.dp))
            NoteTextField(
                text = addNoteScreenState.title,
                onValueChange = {
                    viewModel.onEvent(
                        AddNoteScreenEvent.TitleChange(it)
                    )
                },
                hintText = "title",
                fontSize = 40.sp,
            )
            Spacer(modifier = Modifier.height(10.dp))
            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                thickness = 2.dp,
                color = textColor
            )
            Spacer(modifier = Modifier.height(15.dp))
            NoteTextField(
                text = addNoteScreenState.subtitle,
                onValueChange = {
                    viewModel.onEvent(
                        AddNoteScreenEvent.SubtitleChange(it)
                    )
                },
                hintText = "subtitle",
                fontSize = 25.sp,
                maxLines = 100
            )
        }
    }
}