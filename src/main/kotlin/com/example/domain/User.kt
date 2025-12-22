package com.example.domain

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: String,
    val username: String,
    val password: String
)
