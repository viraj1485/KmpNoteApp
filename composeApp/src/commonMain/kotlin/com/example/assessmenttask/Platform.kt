package com.example.assessmenttask

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform