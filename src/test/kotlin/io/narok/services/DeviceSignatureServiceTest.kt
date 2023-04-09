package io.narok.services

import io.narok.models.DeviceInformationBuilder
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class DeviceSignatureServiceTest {

    private val userAgent = "User-Agent"
    private val ipAddress = "1.2.3.4"
    private val expected = "fc50ae0fc75899e0555e242b5861f50cd596c2abf2160024201e07b8bfc573a6"

    @Test
    fun `should not generate a DeviceInformation with signature if input is invalid`() {
        val deviceSignatureService: IDeviceSignatureService = DeviceSignatureService()
        var deviceInformation = DeviceInformationBuilder().build()
        assertFailsWith<IllegalArgumentException> {
            deviceSignatureService.createSignature(deviceInformation)
        }

        deviceInformation = DeviceInformationBuilder().withIpAddress(ipAddress).build()
        assertFailsWith<IllegalArgumentException> {
            deviceSignatureService.createSignature(deviceInformation)
        }

        deviceInformation = DeviceInformationBuilder().withUserAgent(userAgent).build()
        assertFailsWith<IllegalArgumentException> {
            deviceSignatureService.createSignature(deviceInformation)
        }
    }

    @Test
    fun `should generate a DeviceInformation with signature if input is valid`() {
        val deviceSignatureService: IDeviceSignatureService = DeviceSignatureService()
        val deviceInformation = DeviceInformationBuilder().withUserAgent(userAgent).withIpAddress(ipAddress).build()
        val result = deviceSignatureService.createSignature(deviceInformation)

        assertEquals(expected, result.signature)
    }
}