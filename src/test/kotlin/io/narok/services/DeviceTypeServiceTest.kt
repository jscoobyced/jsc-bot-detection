package io.narok.services

import io.ktor.server.testing.*
import io.narok.models.DeviceInformationBuilder
import io.narok.models.DeviceType
import io.narok.models.googleChromeLinuxUserAgent
import io.narok.models.mobileChromeLinuxUserAgent
import io.narok.plugins.mainDI
import io.narok.repo.fiftyOneDegreesTestDIModule
import org.kodein.di.DI
import org.kodein.di.ktor.closestDI
import kotlin.test.Test
import kotlin.test.assertEquals

class DeviceTypeServiceTest {

    @Test
    fun `should return valid DeviceType if input is valid`() = testApplication {
        application {
            val deviceTypeService: IDeviceTypeService = DeviceTypeService(closestDI())

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
            val deviceTypeService: IDeviceTypeService = DeviceTypeService(closestDI())
            val deviceInformation = DeviceInformationBuilder().build()

            val deviceInformationWithDeviceType = deviceTypeService.createDeviceType(deviceInformation)
            val deviceType = deviceInformationWithDeviceType.deviceType

            assertEquals(DeviceType.UNASSIGNED, deviceType)
        }
    }

    @Test
    fun `should fail if DeviceInformation is null`() = testApplication {
        application {
            val testDi = DI {
                extend(mainDI)
                import(fiftyOneDegreesTestDIModule, allowOverride = true)
            }
            val deviceInformation = DeviceInformationBuilder().withUserAgent(googleChromeLinuxUserAgent()).build()
            val deviceTypeService: IDeviceTypeService = DeviceTypeService(testDi)

            val deviceInformationWithDeviceType = deviceTypeService.createDeviceType(deviceInformation)
            val deviceType = deviceInformationWithDeviceType.deviceType

            assertEquals(DeviceType.UNASSIGNED, deviceType)
        }
    }

}