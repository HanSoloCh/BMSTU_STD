package com.example.api

import com.example.api.di.appModule
import com.example.api.plugin.configureLog
import com.example.api.plugin.configureSecurity
import com.example.api.plugin.configureSerialization
import com.example.api.plugin.configureStatusPages
import com.example.api.route.v1.apuRoutes
import com.example.api.route.v1.authRoutes
import com.example.api.route.v1.authorRoutes
import com.example.api.route.v1.bbkRoutes
import com.example.api.route.v1.bookRoutes
import com.example.api.route.v1.favoriteRoutes
import com.example.api.route.v1.issuanceRoutes
import com.example.api.route.v1.publisherRoutes
import com.example.api.route.v1.queueRoutes
import com.example.api.route.v1.reservationRoutes
import com.example.api.route.v1.userRoutes
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.netty.EngineMain
import io.ktor.server.plugins.cors.routing.CORS
import io.ktor.server.routing.routing
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun main(args: Array<String>) = EngineMain.main(args)

fun Application.module() {
    configureLog()
    configureSerialization()
    configureSecurity()
    configureStatusPages()

    install(CORS) {
        anyHost()
        allowHeader(HttpHeaders.ContentType)
        allowMethod(HttpMethod.Put)
        allowMethod(HttpMethod.Delete)
        allowMethod(HttpMethod.Post)
        allowMethod(HttpMethod.Get)
    }
    install(Koin) {
        slf4jLogger()
        modules(appModule(environment.config))
    }
    routing {
        apuRoutes()
        authorRoutes()
        bbkRoutes()
        bookRoutes()
        publisherRoutes()
        userRoutes()
        reservationRoutes()
        queueRoutes()
        issuanceRoutes()
        favoriteRoutes()
        authRoutes()
    }
}
