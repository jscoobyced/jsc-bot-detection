package io.narok.services

import io.ktor.server.testing.*
import io.narok.models.DeviceInformationBuilder
import io.narok.models.DeviceType
import io.narok.models.googleChromeLinuxUserAgent
import io.narok.models.mobileChromeLinuxUserAgent
import io.narok.repo.IDeviceTypeRepo
import org.kodein.di.instance
import org.kodein.di.ktor.closestDI
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class DeviceTypeServiceTest {

    @Test
    fun `should return valid DeviceType if input is valid`() = testApplication {
        application {
            val deviceTypeRepo by closestDI().instance<IDeviceTypeRepo>()
            val deviceTypeService: IDeviceTypeService = DeviceTypeService(deviceTypeRepo)

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
            val deviceTypeRepo by closestDI().instance<IDeviceTypeRepo>()
            val deviceTypeService: IDeviceTypeService = DeviceTypeService(deviceTypeRepo)
            val deviceInformation = DeviceInformationBuilder().build()

            assertFailsWith<IllegalArgumentException> {
                deviceTypeService.createDeviceType(deviceInformation)
            }
        }
    }
}