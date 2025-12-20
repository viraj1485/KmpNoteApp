package com.example.assessmenttask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalContext
import org.example.project.database.getNoteDatabase
import org.example.project.nativedatepicker.LocalPlatformContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        val dao = getNoteDatabase(applicationContext).getNoteDao()

        setContent {
            val activityContext = LocalContext.current // This IS an Activity Context!
            CompositionLocalProvider(LocalPlatformContext provides activityContext) {
                App(dao)
            }

        }
    }
}
