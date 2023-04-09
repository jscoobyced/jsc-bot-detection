package io.narok.routes

import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.testing.*
import io.narok.withToken
import kotlin.test.Test

class ShutdownRouteTest {
    @Test
    fun testRoot() = testApplication {

        val httpClient = createClient {
            install(ContentNegotiation) {
                json()
            }
        }

        withToken(httpClient) { token ->
            client.get("/shutdown") {
                bearerAuth(token)
            }
        }
    }
}