package io.narok.services

import io.ktor.server.testing.*
import io.narok.models.DeviceInformationBuilder
import io.narok.models.DeviceType
import io.narok.models.googleChromeLinuxUserAgent
import io.narok.models.mobileChromeLinuxUserAgent
import org.kodein.di.ktor.closestDI
import kotlin.test.Test
import kotlin.test.assertEquals

class DeviceTypeServiceTest {

    @Test
    fun `should return valid DeviceType if input is valid`() = testApplication {
        application {
            val di = closestDI()
            val deviceTypeService: IDeviceTypeService = DeviceTypeService(di)

            var deviceInformation = DeviceInformationBuilder().withUserAgent(googleChromeLinuxUserAgent()).build()
            var deviceInformationWithDeviceType = deviceTypeService.createDeviceType(deviceInformation)
            var deviceType = deviceInformationWithDeviceType.deviceType
            assertEquals(DeviceType.DESKTOP, deviceType)

            deviceInformation = DeviceInformationBuilder().withUserAgent(mobileChromeLinuxUserAgent()).build()
            deviceInformationWithDeviceType = deviceTypeService.createDeviceType(deviceInformation)
            deviceType = deviceInformationWithDeviceType.deviceType
            assertEquals(DeviceType.MOBILE, deviceType)
        }
    }

    @Test
    fun `should fail if DeviceInformation has no User-Agent`() = testApplication {
        application {
            val di = closestDI()
            val deviceInformation = DeviceInformationBuilder().build()
            val deviceTypeService: IDeviceTypeService = DeviceTypeService(di)

            val deviceInformationWithDeviceType = deviceTypeService.createDeviceType(deviceInformation)
            val deviceType = deviceInformationWithDeviceType.deviceType

            assertEquals(DeviceType.UNASSIGNED, deviceType)
        }
    }

}