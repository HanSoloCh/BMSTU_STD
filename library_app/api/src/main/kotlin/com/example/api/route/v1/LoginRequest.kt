package com.example.api.route.v1

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    val phone: String,
    val password: String,
)
