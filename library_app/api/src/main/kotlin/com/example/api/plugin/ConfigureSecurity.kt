package com.example.api.plugin

import com.example.api.config.JwtConfig
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.auth.Authentication
import io.ktor.server.auth.jwt.jwt

fun Application.configureSecurity() {
    install(Authentication) {
        jwt("auth-jwt") {
            JwtConfig.configureKtorFeature(this)
        }
    }
}
