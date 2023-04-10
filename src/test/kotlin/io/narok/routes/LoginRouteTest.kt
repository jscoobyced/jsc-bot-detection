package io.narok.routes

import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.testing.*
import io.narok.withToken
import kotlin.test.Test
import kotlin.test.assertNotNull

class LoginRouteTest {
    @Test
    fun testRoot() = testApplication {

        val httpClient = createClient {
            install(ContentNegotiation) {
                json()
            }
        }
        withToken(httpClient) { token ->
            assertNotNull(token)
        }
    }
}