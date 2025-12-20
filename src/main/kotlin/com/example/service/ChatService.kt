package com.example.service

import io.ktor.websocket.*
import java.util.concurrent.ConcurrentHashMap

object ChatService {

    private val sessions =
        ConcurrentHashMap<String, WebSocketSession>()

    fun addUser(username: String, session: WebSocketSession) {
        sessions[username] = session
    }

    fun removeUser(username: String) {
        sessions.remove(username)
    }

    suspend fun broadcast(message: String) {
        sessions.values.forEach { session ->
            session.send(Frame.Text(message))
        }
    }
}
