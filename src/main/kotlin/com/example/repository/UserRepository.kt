package com.example.repository

import com.example.domain.User
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap

object UserRepository {

    private val users = ConcurrentHashMap<String, User>()

    fun register(username: String, password: String): User? {
        if (users.values.any { it.username == username }) return null

        val user = User(
            id = UUID.randomUUID().toString(),
            username = username,
            password = password
        )

        users[user.id] = user
        return user
    }

    fun getById(id: String): User? =
        users[id]

    fun getAll(): List<User> =
        users.values.toList()
}
