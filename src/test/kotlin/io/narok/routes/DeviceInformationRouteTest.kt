package io.narok.routes

import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.testing.*
import io.narok.models.*
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

            val deviceInformationRequest = defaultDeviceInformationRequest()
            val response = httpClient.post(DeviceInformationRouteConfig.path) {
                contentType(ContentType.Application.Json)
                bearerAuth(token)
                setBody<DeviceInformationRequest>(deviceInformationRequest)
            }
            val deviceInformation = response.body<DeviceInformation>()
            assertNotNull(deviceInformation)
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
            assertEquals(HttpStatusCode.InternalServerError, response.status)
        }
    }

    @Test
    fun `Post a DeviceInformation with empty string IP Address`() = testApplication {
        val httpClient = createClient {
            install(ContentNegotiation) {
                json()
            }
        }
        withToken(httpClient) { token ->

            val response = httpClient.post(DeviceInformationRouteConfig.path) {
                contentType(ContentType.Application.Json)
                bearerAuth(token)
                setBody(noIpAddressDeviceInformationRequest())
            }
            assertEquals(HttpStatusCode.InternalServerError, response.status)
            val errorResponse = response.body<ErrorResponse>()
            assertEquals(
                "java.lang.IllegalArgumentException: User-Agent nor IpAddress cannot be blank.",
                errorResponse.message
            )
        }
    }

    @Test
    fun `Post a DeviceInformation with empty string User-Agent`() = testApplication {
        val httpClient = createClient {
            install(ContentNegotiation) {
                json()
            }
        }
        withToken(httpClient) { token ->

            val response = httpClient.post(DeviceInformationRouteConfig.path) {
                contentType(ContentType.Application.Json)
                bearerAuth(token)
                setBody(noUserAgentDeviceInformationRequest())
            }
            assertEquals(HttpStatusCode.InternalServerError, response.status)
            val errorResponse = response.body<ErrorResponse>()
            assertEquals(
                "java.lang.IllegalArgumentException: User-Agent nor IpAddress cannot be blank.",
                errorResponse.message
            )
        }
    }

    @Test
    fun `Post an invalid DeviceInformation model`() = testApplication {
        val httpClient = createClient {
            install(ContentNegotiation) {
                json()
            }
        }
        withToken(httpClient) { token ->

            val response = httpClient.post(DeviceInformationRouteConfig.path) {
                contentType(ContentType.Application.Json)
                bearerAuth(token)
                setBody("bad format")
            }
            assertEquals(HttpStatusCode.InternalServerError, response.status)
        }
    }

    @Test
    fun `Post an invalid DeviceInformation with malformed URL`() = testApplication {
        val httpClient = createClient {
            install(ContentNegotiation) {
                json()
            }
        }
        withToken(httpClient) { token ->

            val response = httpClient.post(DeviceInformationRouteConfig.path) {
                contentType(ContentType.Application.Json)
                bearerAuth(token)
                setBody(malFormedUrlDeviceInformationRequest())
            }
            assertEquals(HttpStatusCode.InternalServerError, response.status)
        }
    }

    @Test
    fun `Post a DeviceInformation unauthorized`() = testApplication {
        val httpClient = createClient {
            install(ContentNegotiation) {
                json()
            }
        }
        val response = httpClient.post(DeviceInformationRouteConfig.path) {
            contentType(ContentType.Application.Json)
        }
        assertEquals(HttpStatusCode.Unauthorized, response.status)
    }
}