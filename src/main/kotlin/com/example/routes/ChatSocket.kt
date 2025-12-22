package com.example.routes

import com.example.domain.Message
import com.example.repository.UserRepository
import com.example.service.ChatService
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString
import kotlinx.serialization.decodeFromString

fun Route.chatSocket() {

    val json = Json { ignoreUnknownKeys = true }

    webSocket("/ws/chat") {

        val token = call.parameters["token"]
            ?: return@webSocket close(
                CloseReason(
                    CloseReason.Codes.CANNOT_ACCEPT,
                    "No token"
                )
            )

        val user = UserRepository.getById(token)
            ?: return@webSocket close(
                CloseReason(
                    CloseReason.Codes.CANNOT_ACCEPT,
                    "Invalid token"
                )
            )

        ChatService.connect(user.id, this)

        try {
            for (frame in incoming) {
                if (frame is Frame.Text) {

                    val message =
                        json.decodeFromString<Message>(frame.readText())

                    val encoded =
                        json.encodeToString(message)

                    ChatService.send(message.to, encoded)
                    ChatService.send(message.from, encoded)
                }
            }
        } finally {
            ChatService.disconnect(user.id)
        }
    }
}
