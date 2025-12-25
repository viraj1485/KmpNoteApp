package com.example.assessmenttask.apisetup.network

import com.example.assessmenttask.apisetup.data.TodoResponseItem
import com.example.assessmenttask.apisetup.util.NetworkError
import com.example.assessmenttask.apisetup.util.Result

interface Repository {
    suspend fun getTodos(): Result<List<TodoResponseItem>, NetworkError>
}