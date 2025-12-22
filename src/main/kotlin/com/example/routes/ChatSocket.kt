package com.plugin.routes

import com.plugin.domain.Message
import com.plugin.repository.UserRepository
import com.plugin.service.ChatService
import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import kotlinx.serialization.json.Json

fun Route.chatSocket() {

    webSocket("/ws/chat") {

        val token = call.parameters["token"]
            ?: return@webSocket close(
                CloseReason(CloseReason.Codes.CANNOT_ACCEPT, "No token")
            )

        val user = UserRepository.getById(token)
            ?: return@webSocket close(
                CloseReason(CloseReason.Codes.CANNOT_ACCEPT, "Invalid token")
            )

        ChatService.connect(user.id, this)

        try {
            for (frame in incoming) {
                if (frame is Frame.Text) {

                    val message =
                        Json.decodeFromString<Message>(frame.readText())

                    // отправляем обоим участникам
                    ChatService.send(message.to, Json.encodeToString(message))
                    ChatService.send(message.from, Json.encodeToString(message))
                }
            }
        } finally {
            ChatService.disconnect(user.id)
        }
    }
}
