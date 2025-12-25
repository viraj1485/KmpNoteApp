package com.example.assessmenttask.apisetup.viewmodel

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assessmenttask.apisetup.data.TodoResponseItem
import com.example.assessmenttask.apisetup.network.RepositoryImpl
import com.example.assessmenttask.apisetup.util.onError
import com.example.assessmenttask.apisetup.util.onSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(
    private val repositoryImpl: RepositoryImpl,
    private val pref: DataStore<Preferences>
) : ViewModel() {
    private val _todoList = MutableStateFlow<List<TodoResponseItem>>(emptyList())
    val todoList = _todoList
        .onStart {
            getTodos()
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())


    val scroll_position = pref.data.map {
        val counterKey = intPreferencesKey("scroll_position")
        it[counterKey] ?: 0
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    fun getTodos() {
        viewModelScope.launch {
            repositoryImpl.getTodos().onSuccess { todoList ->
                _todoList.update {
                    todoList
                }
            }.onError {
                _todoList.update {
                    emptyList()
                }
                println("Error happens")
            }
        }
    }

    fun updatePosition(index: Int) {
        viewModelScope.launch {
            pref.edit { dataStore ->
                val counterKey = intPreferencesKey("scroll_position")
                dataStore[counterKey] = index
            }
        }
    }

}