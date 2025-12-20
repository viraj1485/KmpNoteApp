package org.example.project.notes.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import assessmenttask.composeapp.generated.resources.Res
import assessmenttask.composeapp.generated.resources.app_name
import assessmenttask.composeapp.generated.resources.confirm
import assessmenttask.composeapp.generated.resources.dissmiss
import assessmenttask.composeapp.generated.resources.note_delete_warning
import assessmenttask.composeapp.generated.resources.rafiki
import org.example.project.notes.domain.AddNoteScreenEvent
import org.example.project.notes.resources.backgroundColor
import org.example.project.notes.resources.cardBackGroundColor
import org.example.project.notes.resources.dialogueBackGroundColor
import org.example.project.notes.resources.textColor
import org.example.project.notes.screen.components.NoteItem
import org.example.project.notes.screen.components.NoteListTopBar
import org.example.project.notes.screen.components.addButton
import org.example.project.notes.viewmodel.NoteViewModel
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun NoteListScreen(
    viewModel: NoteViewModel,
    onAddIconClick: () -> Unit,
    onSubtitle: (String) -> Unit,
    onInfoClick: () -> Unit,
    onClick:(Int)->Unit
) {
    val notes by viewModel.notes.collectAsStateWithLifecycle()
    var showDialogue by remember {
        mutableStateOf(false)
    }
    var noteId by remember { mutableStateOf(-1) }
    Scaffold(
        modifier = Modifier.fillMaxSize().background(
            backgroundColor
        ).statusBarsPadding().navigationBarsPadding(),
        containerColor = backgroundColor,
        topBar = {
            NoteListTopBar(onSearchClick = {

            }, onInfoClick = {
                onInfoClick()
            })
        },
        floatingActionButton = {
            addButton(
                onClick = {
                    onAddIconClick()
                }
            )
        },
    ) { innerPadding ->
        if (notes.isEmpty()) {
            Column(
                modifier = Modifier.fillMaxSize().padding(
                    innerPadding
                ).padding(
                    horizontal = 16.dp
                ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(Res.drawable.rafiki),
                    contentDescription = "",
                    modifier = Modifier.size(250.dp)
                        .align(Alignment.CenterHorizontally),
                    contentScale = ContentScale.Fit
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = innerPadding, verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                item {
                    Spacer(modifier = Modifier.height(15.dp))
                }
                items(notes) { note ->
                    NoteItem(note, onSubtitleClick = {
                        onSubtitle(it)
                    }, onLongPress = {
                        showDialogue = true
                        noteId = it
                    }, onClick = {
                        onClick(it)
                    })
                }
            }
        }
    }

    if (showDialogue) {
        AlertDialog(
            onDismissRequest = {
                showDialogue = false
                noteId = -1
            },
            title = {
                Text(
                    text = stringResource(Res.string.app_name),
                    color = textColor
                )
            },
            text = {
                Text(text = stringResource(Res.string.note_delete_warning))
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        showDialogue = false
                        viewModel.onEvent(AddNoteScreenEvent.OnDeleteClick(noteId))
                    }
                ) {
                    Text(text = stringResource(Res.string.confirm))
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showDialogue = false
                    }
                ) {
                    Text(
                        text = stringResource(Res.string.dissmiss)
                    )
                }
            },
            containerColor = dialogueBackGroundColor,
            textContentColor = textColor,
            shape = RoundedCornerShape(15.dp)
        )
    }

}