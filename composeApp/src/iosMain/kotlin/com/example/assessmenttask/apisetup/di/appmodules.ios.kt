package com.example.assessmenttask.apisetup.di

import com.example.assessmenttask.createDataStore
import io.ktor.client.engine.darwin.Darwin
import org.example.project.database.getNoteDatabase
import org.koin.dsl.module

actual val platFormModule = module {
    single {
        Darwin.create()
    }

    single {
        getNoteDatabase().getNoteDao()
    }

    single {
        createDataStore()
    }
}