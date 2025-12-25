package com.example.assessmenttask.apisetup.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assessmenttask.apisetup.data.TodoResponseItem
import com.example.assessmenttask.apisetup.network.RepositoryImpl
import com.example.assessmenttask.apisetup.util.onError
import com.example.assessmenttask.apisetup.util.onSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(
    private val repositoryImpl: RepositoryImpl
) : ViewModel() {
    private val _todoList = MutableStateFlow<List<TodoResponseItem>>(emptyList())
    val todoList = _todoList
        .onStart {
            getTodos()
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

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
}