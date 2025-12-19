package com.example

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun main() {
    embeddedServer(Netty, port = System.getenv("PORT")?.toIntOrNull() ?: 8080, host = "0.0.0.0") {
        configureRouting()
    }.start(wait = true)
}

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello from Ktor on Render!")
        }
        
        get("/health") {
            call.respond(mapOf("status" to "OK"))
        }
        
        get("/api/hello") {
            val name = call.request.queryParameters["name"] ?: "World"
            call.respond(mapOf(
                "message" to "Hello, $name!",
                "timestamp" to System.currentTimeMillis()
            ))
        }
    }
}
