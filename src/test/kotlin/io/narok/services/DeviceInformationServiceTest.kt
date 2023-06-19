package io.narok.services

import io.ktor.server.testing.*
import io.narok.models.DeviceType
import io.narok.models.defaultDeviceInformationRequest
import io.narok.plugins.mainDI
import io.narok.plugins.queueRepoModule
import org.kodein.di.DI
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class DeviceInformationServiceTest {

    @Test
    fun `should generate the full DeviceInformation if data is valid`() = testApplication {
        val testDi = DI {
            extend(mainDI(environment = createTestEnvironment {  }))
            import(queueRepoModule)
        }

        val deviceInformationRequest = defaultDeviceInformationRequest()

        val deviceInformationService = DeviceInformationService(testDi)
        val deviceInformation = deviceInformationService.getDeviceInformation(deviceInformationRequest)
        val expectedSignature = "d0bbf8cd5ed9984915bf95c465b5202689ee8c62f0610e4eaeca93b5c4179b1b"

        assertNotNull(deviceInformation.domain)
        assertNotNull(deviceInformation.deviceSignature)
        assertEquals(expectedSignature, deviceInformation.deviceSignature?.signature)
        assertEquals(DeviceType.DESKTOP, deviceInformation.deviceType)
    }
}