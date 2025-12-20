package com.example.assessmenttask

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import org.example.project.database.getNoteDatabase
import org.example.project.nativedatepicker.LocalPlatformContext

fun MainViewController() = ComposeUIViewController {
    val dao = remember { getNoteDatabase().getNoteDao() }
    CompositionLocalProvider(LocalPlatformContext provides null) {
        App(dao)
    }
}