package io.narok.services

import io.ktor.server.testing.*
import io.narok.models.DeviceType
import io.narok.models.badBotDeviceInformationRequest
import io.narok.models.defaultDesktopDeviceInformationRequest
import io.narok.models.defaultMobileDeviceInformationRequest
import io.narok.plugins.mainDI
import io.narok.plugins.queueRepoModule
import org.kodein.di.DI
import kotlin.test.*

class DeviceInformationServiceTest {

    @Test
    fun `should generate the full DeviceInformation if data is valid`() = testApplication {
        val testDi = DI {
            extend(mainDI())
            import(queueRepoModule, allowOverride = true)
        }

        val deviceInformationRequest = defaultDesktopDeviceInformationRequest()

        val deviceInformationService = DeviceInformationService(testDi)
        val deviceInformation = deviceInformationService.getDeviceInformation(deviceInformationRequest)
        val expectedSignature = "d0bbf8cd5ed9984915bf95c465b5202689ee8c62f0610e4eaeca93b5c4179b1b"

        assertNotNull(deviceInformation.domain)
        assertNotNull(deviceInformation.deviceSignature)
        assertEquals(expectedSignature, deviceInformation.deviceSignature?.signature)
        assertEquals(DeviceType.DESKTOP, deviceInformation.deviceType)
    }

    @Test
    fun `should identify a Mobile device`() = testApplication {
        val testDi = DI {
            extend(mainDI())
            import(queueRepoModule, allowOverride = true)
        }

        val deviceInformationRequest = defaultMobileDeviceInformationRequest()
        val deviceInformationService = DeviceInformationService(testDi)
        val deviceInformation = deviceInformationService.getDeviceInformation(deviceInformationRequest)
        assertEquals(DeviceType.MOBILE, deviceInformation.deviceType)
    }

    @Test
    fun `should identify a Bot`() = testApplication {
        val testDi = DI {
            extend(mainDI())
            import(queueRepoModule, allowOverride = true)
        }

        val deviceInformationRequest = badBotDeviceInformationRequest()
        val deviceInformationService = DeviceInformationService(testDi)
        val deviceInformation = deviceInformationService.getDeviceInformation(deviceInformationRequest)
        assertTrue(deviceInformation.userType?.bot?.isBadBot!!)
        assertFalse(deviceInformation.userType?.bot?.isKnownBot!!)
    }
}