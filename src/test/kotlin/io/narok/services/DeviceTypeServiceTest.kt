package io.narok.services

import io.ktor.server.testing.*
import io.narok.models.DeviceInformationBuilder
import io.narok.models.DeviceType
import io.narok.models.googleChromeLinuxUserAgent
import io.narok.models.mobileChromeLinuxUserAgent
import org.kodein.di.ktor.closestDI
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class DeviceTypeServiceTest {

    @Test
    fun `should return valid DeviceType if input is valid`() = testApplication {
        application {
            val deviceTypeService: IDeviceTypeService = DeviceTypeService(closestDI())

            var deviceInformation = DeviceInformationBuilder().withUserAgent(googleChromeLinuxUserAgent()).build()
            var deviceType = deviceTypeService.createDeviceType(deviceInformation)
            assertEquals(DeviceType.DESKTOP, deviceType)

            deviceInformation = DeviceInformationBuilder().withUserAgent(mobileChromeLinuxUserAgent()).build()
            deviceType = deviceTypeService.createDeviceType(deviceInformation)
            assertEquals(DeviceType.MOBILE, deviceType)
        }
    }

    @Test
    fun `should fail if DeviceInformation has no User-Agent`() = testApplication {
        application {
            val deviceTypeService: IDeviceTypeService = DeviceTypeService(closestDI())
            val deviceInformation = DeviceInformationBuilder().build()

            assertFailsWith<IllegalArgumentException> {
                deviceTypeService.createDeviceType(deviceInformation)
            }
        }
    }
}