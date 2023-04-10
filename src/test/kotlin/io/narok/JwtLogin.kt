package io.narok

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.narok.models.Token
import io.narok.models.User

suspend fun <T> withToken(httpClient: HttpClient, block: suspend (String) -> T): T {
    val user = User("jsc", "password")
    val response = httpClient.post("/login") {
        contentType(ContentType.Application.Json)
        setBody<User>(user)
    }
    val token = response.body<Token>()
    return block(token.token)
}
