package io.narok.routes

import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.testing.*
import io.narok.models.DeviceInformation
import io.narok.models.DeviceInformationRequest
import io.narok.models.DeviceInformationRequestBuilder
import io.narok.withToken
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class DeviceInformationRouteTest {
    @Test
    fun `Post a DeviceInformation`() = testApplication {
        val httpClient = createClient {
            install(ContentNegotiation) {
                json()
            }
        }

        withToken(httpClient) { token ->

            val cookies = HashMap<String, String>()
            cookies["user-token"] = "123456789"
            val deviceInformationRequest =
                DeviceInformationRequestBuilder("http://example.com", "User Agent 123").build()
            val response = httpClient.post(DeviceInformationRouteConfig.path) {
                contentType(ContentType.Application.Json)
                bearerAuth(token)
                setBody<DeviceInformationRequest>(deviceInformationRequest)
            }
            val deviceInformation = response.body<DeviceInformation>()
            assertNotNull(deviceInformation.domain)
            assertEquals(HttpStatusCode.OK, response.status)
        }
    }

    @Test
    fun `Post a NULL DeviceInformation`() = testApplication {
        val httpClient = createClient {
            install(ContentNegotiation) {
                json()
            }
        }
        withToken(httpClient) { token ->

            val response = httpClient.post(DeviceInformationRouteConfig.path) {
                contentType(ContentType.Application.Json)
                bearerAuth(token)
            }
            assertEquals(HttpStatusCode.BadRequest, response.status)
        }
    }
}