package io.narok.routes

import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlin.test.Test
import kotlin.test.assertEquals

class DefaultRouteTest {
    @Test
    fun testRoot() = testApplication {

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