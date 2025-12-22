package com.plugin.dto

import kotlinx.serialization.Serializable

@Serializable
data class RegisterResponse(
    val userId: String,
    val token: String
)
