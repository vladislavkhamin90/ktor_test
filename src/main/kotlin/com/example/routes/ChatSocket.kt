package com.example.routes

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
            val json = Json { ignoreUnknownKeys = true }
            for (frame in incoming) {
                if (frame is Frame.Text) {
                    val message = json.decodeFromString(
                        Message.serializer(),
                        frame.readText()
                    )
            
                    val encoded = json.encodeToString(
                        Message.serializer(),
                        message
                    )
            
                    ChatService.send(message.to, encoded)
                    ChatService.send(message.from, encoded)
                }
            }

        } finally {
            ChatService.disconnect(user.id)
        }
    }
}
