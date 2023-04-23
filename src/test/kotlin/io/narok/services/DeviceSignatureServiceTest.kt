package io.narok.services

import io.narok.models.DeviceInformationBuilder
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class DeviceSignatureServiceTest {

    private val userAgent = "User-Agent"
    private val ipAddress = "1.2.3.4"
    private val expected = "fc50ae0fc75899e0555e242b5861f50cd596c2abf2160024201e07b8bfc573a6"

    @Test
    fun `DeviceInformation passed is valid`() {
        val deviceSignatureService: IDeviceSignatureService = DeviceSignatureService()
        var deviceInformation = DeviceInformationBuilder().build()
        var result = deviceSignatureService.createSignature(deviceInformation)
        assertNull(result.deviceSignature)

        deviceInformation = DeviceInformationBuilder().withIpAddress(ipAddress).build()
        result = deviceSignatureService.createSignature(deviceInformation)
        assertNull(result.deviceSignature)

        deviceInformation = DeviceInformationBuilder().withUserAgent(userAgent).build()
        result = deviceSignatureService.createSignature(deviceInformation)
        assertNull(result.deviceSignature)
    }

    @Test
    fun `DeviceInformation signature is calculated`() {
        val deviceSignatureService: IDeviceSignatureService = DeviceSignatureService()
        val deviceInformation = DeviceInformationBuilder().withUserAgent(userAgent).withIpAddress(ipAddress).build()
        val result = deviceSignatureService.createSignature(deviceInformation)

        assertEquals(expected, result.deviceSignature?.signature)
    }
}