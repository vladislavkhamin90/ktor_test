package com.plugin.domain

import kotlinx.serialization.Serializable

@Serializable
data class Message(
    val from: String,
    val to: String,
    val text: String,
    val timestamp: Long = System.currentTimeMillis()
)
