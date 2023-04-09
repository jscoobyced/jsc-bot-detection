package io.narok.models

import kotlin.test.Test
import kotlin.test.assertEquals

class DeviceInformationTest {

    @Test
    fun `can transform a fully filled deviceInformationRequest to a deviceInformation`() {
        val deviceInformationRequest = DeviceInformationRequestBuilder(
            "https://example.com/blog?param=1&key=2", "User Agent 123", "192.168.1.10"
        ).withSessionId("123456789")
            .withWhiteListedCookies(mapOf("cookie1" to "value1", "cookie2" to "value2"))
            .build()

        val deviceInformation = DeviceInformation.fromDeviceInformationRequest(deviceInformationRequest)

        assertEquals(deviceInformationRequest.userAgent, deviceInformation.userAgent)
        assertEquals(deviceInformationRequest.sessionId, deviceInformation.sessionId)
        assertEquals(deviceInformationRequest.ipAddress, deviceInformation.ipAddress)
        assertEquals(deviceInformationRequest.whiteListedCookies, deviceInformation.whiteListedCookies)
        assertEquals("example.com", deviceInformation.domain)
        assertEquals("/blog", deviceInformation.path)
        assertEquals(mapOf("param" to listOf("1"), "key" to listOf("2")), deviceInformation.parameters)
        assertEquals(true, deviceInformation.isHttps)
    }

    @Test
    fun `can transform a simple deviceInformationRequest to a deviceInformation`() {
        val deviceInformationRequest = DeviceInformationRequestBuilder(
            "http://example.com", "User Agent 123", "192.168.1.10"
        ).build()

        val deviceInformation = DeviceInformation.fromDeviceInformationRequest(deviceInformationRequest)

        assertEquals(deviceInformationRequest.userAgent, deviceInformation.userAgent)
        assertEquals(deviceInformationRequest.sessionId, deviceInformation.sessionId)
        assertEquals(deviceInformationRequest.ipAddress, deviceInformation.ipAddress)
        assertEquals(deviceInformationRequest.whiteListedCookies, deviceInformation.whiteListedCookies)
        assertEquals("example.com", deviceInformation.domain)
        assertEquals("", deviceInformation.path)
        assertEquals(mapOf(), deviceInformation.parameters)
        assertEquals(false, deviceInformation.isHttps)
    }
}