package io.narok.routes

import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import io.narok.plugins.configureHTTP
import kotlin.test.Test
import io.narok.plugins.configureRouting
import io.narok.plugins.configureSerialization
import kotlin.test.assertEquals

class DefaultRouteTest {
    @Test
    fun testRoot() = testApplication {
        application {
            configureSerialization()
            configureHTTP()
            configureRouting()
        }
        client.get("/").apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals("", bodyAsText())
        }

        client.post("/").apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals("", bodyAsText())
        }
    }
}