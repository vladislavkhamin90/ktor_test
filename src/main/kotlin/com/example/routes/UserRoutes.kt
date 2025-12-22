package com.plugin.routes

import com.plugin.repository.UserRepository
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.userRoutes() {

    get("/users") {
        call.respond(UserRepository.getAll())
    }
}
