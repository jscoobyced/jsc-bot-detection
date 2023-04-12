package io.narok.routes

import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.testing.*
import io.narok.withToken
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class LoginRouteTest {
    @Test
    fun `login with valid credentials`() = testApplication {

        val httpClient = createClient {
            install(ContentNegotiation) {
                json()
            }
        }
        withToken(httpClient) { token ->
            assertNotNull(token)
        }
    }

    @Test
    fun `login with no credentials`() = testApplication {

        val httpClient = createClient {
            install(ContentNegotiation) {
                json()
            }
        }

        val response = httpClient.post(LoginRouteConfig.path) {
            contentType(ContentType.Application.Json)
        }
        assertEquals(HttpStatusCode.BadRequest, response.status)
    }

}