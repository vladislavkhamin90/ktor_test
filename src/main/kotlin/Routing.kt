package com.plugin.plugins

import com.plugin.example.routes.authRoutes
import com.plugin.example.routes.userRoutes
import com.plugin.example.routes.chatSocket
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Message server is running")
        }

        authRoutes()
        userRoutes()
        chatSocket()
    }
}
