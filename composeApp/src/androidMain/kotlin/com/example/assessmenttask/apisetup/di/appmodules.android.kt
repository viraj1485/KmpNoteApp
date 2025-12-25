package com.example.assessmenttask.apisetup.di

import com.example.assessmenttask.apisetup.network.createHttpClient
import com.example.assessmenttask.createDataStore
import io.ktor.client.engine.okhttp.OkHttp
import org.example.project.database.getNoteDatabase
import org.koin.dsl.module

actual val platFormModule = module {
    single {
        createHttpClient(OkHttp.create())
    }

    single {
        getNoteDatabase(get()).getNoteDao()
    }

    single {
        createDataStore(get())
    }
}