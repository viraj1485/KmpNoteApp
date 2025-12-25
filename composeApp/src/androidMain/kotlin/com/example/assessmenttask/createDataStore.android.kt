package com.example.assessmenttask

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.assessmenttask.datastorepref.DATA_STORE_FILE_NAME

fun createDataStore(context: Context): DataStore<Preferences> {
    return com.example.assessmenttask.datastorepref.createDataStore {
        context.filesDir.resolve(DATA_STORE_FILE_NAME).absolutePath
    }
}