package com.example.assessmenttask.apisetup.data

import kotlinx.serialization.Serializable

@Serializable
data class TodoResponseItem(
    val completed: Boolean,
    val id: Int,
    val title: String,
    val userId: Int
)