package io.narok.models

import kotlinx.serialization.Serializable

@Serializable
data class User(val username: String, val password: String)

@Serializable
data class Token(val token: String)