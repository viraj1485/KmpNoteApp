package com.example.assessmenttask.apisetup.di

import com.example.assessmenttask.apisetup.network.Repository
import com.example.assessmenttask.apisetup.network.RepositoryImpl
import com.example.assessmenttask.apisetup.viewmodel.MainViewModel
import org.example.project.notes.viewmodel.NoteViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platFormModule: Module

val appModule = module {
    single {
        RepositoryImpl(get())
    }.bind<Repository>()

    viewModelOf(::MainViewModel)

    viewModelOf(::NoteViewModel)

}