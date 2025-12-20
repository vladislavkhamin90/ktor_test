package com.example.routes

import com.example.domain.Message
import com.example.service.ChatService
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

fun Route.chatSocket() {

    webSocket("/ws/chat") {

        val username = call.parameters["username"]
            ?: return@webSocket close(
                CloseReason(
                    CloseReason.Codes.CANNOT_ACCEPT,
                    "No username"
                )
            )

        ChatService.addUser(username, this)

        try {
            for (frame in incoming) {
                if (frame is Frame.Text) {
                    val text = frame.readText()

                    val message = Message(
                        sender = username,
                        text = text
                    )

                    ChatService.broadcast(
                        Json.encodeToString(message)
                    )
                }
            }
        } finally {
            ChatService.removeUser(username)
        }
    }
}
