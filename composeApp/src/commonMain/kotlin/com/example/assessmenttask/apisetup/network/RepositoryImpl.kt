package com.example.assessmenttask.apisetup.network

import com.example.assessmenttask.apisetup.AppConstants.BASE_URL
import com.example.assessmenttask.apisetup.data.TodoResponseItem
import com.example.assessmenttask.apisetup.util.NetworkError
import com.example.assessmenttask.apisetup.util.Result
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.isSuccess

class RepositoryImpl(
    private val httpClient: HttpClient
) : Repository {
    override suspend fun getTodos(): Result<List<TodoResponseItem>, NetworkError> {
        return try {
            val response = httpClient.get(
                urlString = "$BASE_URL/todos"
            )
            if (response.status.isSuccess()) {
                Result.Success(data = response.body<List<TodoResponseItem>>())
            } else {
                Result.Error(NetworkError.UNKNOWN)
            }
        } catch (e: Exception) {
            Result.Error(NetworkError.UNKNOWN)
        }
    }
}