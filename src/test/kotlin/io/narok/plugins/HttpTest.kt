package io.narok.plugins

import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.testing.*
import io.narok.routes.defaultRouting
import kotlin.test.Test
import kotlin.test.assertEquals

class HttpTest {

    @Test
    fun `Check CORS can be setup`() = testApplication {
        application {
            configureHTTP()
            defaultRouting()
        }

        client.patch("/").apply {
            assertEquals(HttpStatusCode.MethodNotAllowed, status)
        }
    }
}