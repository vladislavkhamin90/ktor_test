package com.example.domain

import kotlinx.serialization.Serializable

@Serializable
data class Message(
    val sender: String,
    val text: String,
    val timestamp: Long = System.currentTimeMillis()
)
