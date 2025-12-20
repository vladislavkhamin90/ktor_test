package com.example

import com.example.plugins.*
import com.example.routes.chatSocket
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.module() {

    configureSerialization()
    configureWebSockets()

    routing {
        chatSocket()
    }
}
