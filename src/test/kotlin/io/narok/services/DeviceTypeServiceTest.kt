package io.narok.services

import io.narok.models.DeviceInformationBuilder
import io.narok.models.crawlerUserAgent
import io.narok.models.googleChromeLinuxUserAgent
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class DeviceTypeServiceTest {

    @Test
    fun `should return valid DeviceType if input is valid`() {
        val deviceInformation = DeviceInformationBuilder().withUserAgent(crawlerUserAgent()).build()
        val deviceTypeService: IDeviceTypeService = DeviceTypeService()

        val deviceInformationWithDeviceType = deviceTypeService.createDeviceType(deviceInformation)
        val nullableDeviceType = deviceInformationWithDeviceType.deviceType

        assertNotNull(nullableDeviceType)
        val deviceType = nullableDeviceType!!
        assertFalse(deviceType.isBot)
        assertFalse(deviceType.isBadBot)
        assertFalse(deviceType.isKnownBot)
        assertTrue(deviceType.isHuman)
    }

    @Test
    fun `should fail if DeviceInformation has no User-Agent`() {
        val deviceInformation = DeviceInformationBuilder().build()
        val deviceTypeService: IDeviceTypeService = DeviceTypeService()

        val deviceInformationWithDeviceType = deviceTypeService.createDeviceType(deviceInformation)
        val nullableDeviceType = deviceInformationWithDeviceType.deviceType

        assertNotNull(nullableDeviceType)
        val deviceType = nullableDeviceType!!
        assertFalse(deviceType.isBot)
        assertFalse(deviceType.isBadBot)
        assertFalse(deviceType.isKnownBot)
        assertTrue(deviceType.isHuman)
    }

}