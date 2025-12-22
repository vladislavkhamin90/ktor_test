package com.example.routes

import com.example.repository.UserRepository
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.userRoutes() {
    get("/users") {
        call.respond(UserRepository.getAll())
    }
}
