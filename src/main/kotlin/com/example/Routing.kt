package com.example

import com.example.routes.authRoutes
import com.example.routes.userRoutes
import com.example.routes.chatSocket
import io.ktor.server.application.*
import io.ktor.server.response.*
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
