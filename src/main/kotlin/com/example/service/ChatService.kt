package com.example.service

import io.ktor.websocket.*
import java.util.concurrent.ConcurrentHashMap

object ChatService {

    private val sessions =
        ConcurrentHashMap<String, WebSocketSession>()

    fun connect(userId: String, session: WebSocketSession) {
        sessions[userId] = session
    }

    fun disconnect(userId: String) {
        sessions.remove(userId)
    }

    suspend fun send(userId: String, message: String) {
        sessions[userId]?.send(Frame.Text(message))
    }
}
