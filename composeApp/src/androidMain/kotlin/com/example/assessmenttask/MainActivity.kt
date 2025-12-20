package com.example.assessmenttask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import org.example.project.database.getPeopleDatabase
import org.example.project.nativedatepicker.LocalPlatformContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        val dao = getPeopleDatabase(applicationContext).getNoteDao()

        setContent {
            val activityContext = LocalContext.current // This IS an Activity Context!
            CompositionLocalProvider(LocalPlatformContext provides activityContext) {
                App(dao)
            }

        }
    }
}
