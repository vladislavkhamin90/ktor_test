package com.example

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

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
        
        get("/test") {
            call.respondText("Test route")
        }
    }
}
