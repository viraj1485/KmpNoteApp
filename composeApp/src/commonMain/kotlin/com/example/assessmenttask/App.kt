package com.example.assessmenttask

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import assessmenttask.composeapp.generated.resources.Res
import assessmenttask.composeapp.generated.resources.compose_multiplatform
import org.example.project.database.NoteDao
import org.example.project.notes.screen.AddNoteScreen
import org.example.project.notes.screen.NoteListScreen
import org.example.project.notes.screen.NoteWebViewScreen
import org.example.project.notes.screen.PdfViewerScreen
import org.example.project.notes.screens.AppScreens
import org.example.project.notes.viewmodel.NoteViewModel

@Composable
fun App(
    noteDao: NoteDao
) {
    MaterialTheme {
        val navController = rememberNavController()
        val viewmodel = viewModel {
            NoteViewModel(noteDao)
        }

        NavHost(
            navController = navController,
            startDestination = AppScreens.NoteListScreen
        ) {
            composable<AppScreens.NoteListScreen> {
                NoteListScreen(
                    viewModel = viewmodel,
                    onSubtitle = {
                        navController.navigate(AppScreens.NoteWebViewScreen(it))
                    },
                    onAddIconClick = {
                        navController.navigate(AppScreens.AddNoteScreen())
                    },
                    onInfoClick = {
                        navController.navigate(AppScreens.PDfViewerScreen)
                    },
                    onClick = {
                        navController.navigate(AppScreens.AddNoteScreen(
                            id = it
                        ))
                    }
                )
            }
            composable<AppScreens.AddNoteScreen> {
                val data = it.toRoute<AppScreens.AddNoteScreen>()
                AddNoteScreen(
                    viewModel = viewmodel,
                    noteId = data.id
                ) {
                    navController.navigateUp()
                }
            }

            composable<AppScreens.NoteWebViewScreen> {
                val data = it.toRoute<AppScreens.NoteWebViewScreen>()
                NoteWebViewScreen(
                    url = data.url
                ) {
                    navController.navigateUp()
                }
            }

            composable<AppScreens.PDfViewerScreen> {
                PdfViewerScreen {
                    navController.navigateUp()
                }
            }
        }
    }
}