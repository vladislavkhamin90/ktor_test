package com.example

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.example.plugins.*
import com.example.routes.chatSocket
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun main() {
    embeddedServer(
        Netty,
        port = System.getenv("PORT")?.toInt() ?: 8080,
        host = "0.0.0.0"
    ) {
        module()
    }.start(wait = true)
}

fun Application.module() {
    configureSerialization()
    configureWebSockets()
    configureRouting()
    }
}
