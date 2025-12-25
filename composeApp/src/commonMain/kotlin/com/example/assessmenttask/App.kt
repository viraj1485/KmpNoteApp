package com.example.assessmenttask

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute

import com.example.assessmenttask.apisetup.screens.TodoListScreen
import com.example.assessmenttask.apisetup.viewmodel.MainViewModel
import com.example.assessmenttask.notes.screen.PermissionScreen
import org.example.project.notes.screen.AddNoteScreen
import org.example.project.notes.screen.NoteListScreen
import org.example.project.notes.screen.NoteWebViewScreen
import org.example.project.notes.screen.PdfViewerScreen
import org.example.project.notes.screens.AppScreens
import org.example.project.notes.viewmodel.NoteViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun App() {
    MaterialTheme {
        val navController = rememberNavController()
        val viewmodel= koinViewModel<NoteViewModel>()
        val MainViewModel = koinViewModel<MainViewModel>()

        NavHost(
            navController = navController,
            startDestination = AppScreens.NoteListScreen
        ) {
            composable<AppScreens.TodoListScreen> {
                TodoListScreen(MainViewModel)
            }
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
//                        navController.navigate(AppScreens.PDfViewerScreen)
//                        navController.navigate(AppScreens.PermissionScreen)
                        navController.navigate(AppScreens.TodoListScreen)
                    },
                    onClick = {
                        navController.navigate(
                            AppScreens.AddNoteScreen(
                                id = it
                            )
                        )
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

            composable<AppScreens.PermissionScreen> {
                PermissionScreen()
            }
        }
    }
}