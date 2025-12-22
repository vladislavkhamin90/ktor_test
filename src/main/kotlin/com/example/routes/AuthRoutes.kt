package com.plugin.routes

import com.plugin.dto.RegisterRequest
import com.plugin.dto.RegisterResponse
import com.plugin.repository.UserRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.authRoutes() {

    post("/register") {
        val req = call.receive<RegisterRequest>()

        val user = UserRepository.register(req.username, req.password)
            ?: return@post call.respond(HttpStatusCode.Conflict)

        call.respond(
            RegisterResponse(
                userId = user.id,
                token = user.id // временно
            )
        )
    }
}
