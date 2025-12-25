package com.example.assessmenttask

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import com.example.assessmenttask.apisetup.di.initKoin
import org.example.project.database.getNoteDatabase
import org.example.project.nativedatepicker.LocalPlatformContext

fun MainViewController(
) = ComposeUIViewController(
    configure = {
        initKoin()
    }) {
    CompositionLocalProvider(LocalPlatformContext provides null) {
        App()
    }
}