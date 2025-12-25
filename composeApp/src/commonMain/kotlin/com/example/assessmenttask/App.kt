package com.example.assessmenttask

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute

import com.example.assessmenttask.apisetup.screens.TodoListScreen
import com.example.assessmenttask.apisetup.viewmodel.MainViewModel
import com.example.assessmenttask.notes.screen.PermissionScreen
import com.example.assessmenttask.snackbarsetup.ObserveAsEvents
import com.example.assessmenttask.snackbarsetup.SnackbarController
import kotlinx.coroutines.launch
import org.example.project.notes.resources.backgroundColor
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
        val viewmodel = koinViewModel<NoteViewModel>()
        val MainViewModel = koinViewModel<MainViewModel>()

        val snackbarHostState = remember {
            SnackbarHostState()
        }
        val scope = rememberCoroutineScope()
        ObserveAsEvents(
            flow = SnackbarController.events,
            snackbarHostState
        ) { event ->
            scope.launch {
                snackbarHostState.currentSnackbarData?.dismiss()

                val result = snackbarHostState.showSnackbar(
                    message = event.message,
                    actionLabel = event.action?.name,
                    duration = SnackbarDuration.Short
                )

                if(result == SnackbarResult.ActionPerformed) {
                    event.action?.action?.invoke()
                }
            }
        }
        Scaffold(
            snackbarHost = {
                SnackbarHost(
                    hostState = snackbarHostState
                )
            },
            modifier = Modifier.fillMaxSize().background(
                backgroundColor
            ).statusBarsPadding().navigationBarsPadding(),
            containerColor = backgroundColor
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = AppScreens.NoteListScreen,
                modifier = Modifier.fillMaxSize().padding(
                    innerPadding
                )
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
}