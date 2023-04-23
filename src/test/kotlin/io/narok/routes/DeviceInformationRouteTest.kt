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
import kotlin.test.assertNull

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
            val deviceInformationRequest = defaultDeviceInformationRequest()
            val response = httpClient.post(DeviceInformationRouteConfig.path) {
                contentType(ContentType.Application.Json)
                bearerAuth(token)
                setBody<DeviceInformationRequest>(deviceInformationRequest)
            }
            val expected = "72ddcdeb90707207fd6b8a0afd3dd0b2d6dd2f96c93d11a1b591c012bffb5de6"
            val deviceInformation = response.body<DeviceInformation>()
            assertNotNull(deviceInformation.domain)
            assertNotNull(deviceInformation.deviceSignature)
            assertEquals(expected, deviceInformation.deviceSignature?.signature)
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
            assertEquals(HttpStatusCode.OK, response.status)
            val deviceInformation = response.body<DeviceInformation>()
            assertNull(deviceInformation.deviceSignature)
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
            assertEquals(HttpStatusCode.OK, response.status)
            val deviceInformation = response.body<DeviceInformation>()
            assertNull(deviceInformation.deviceSignature)
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
            assertEquals(HttpStatusCode.BadRequest, response.status)
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