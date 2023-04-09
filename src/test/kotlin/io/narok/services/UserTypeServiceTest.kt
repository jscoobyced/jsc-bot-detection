package io.narok.services

import io.narok.models.DeviceInformationBuilder
import io.narok.models.googleChromeLinuxUserAgent
import junit.framework.TestCase.assertNull
import kotlin.test.Test
import kotlin.test.assertNotNull

class UserTypeServiceTest {

    @Test
    fun `should generate the UserType with valid data`() {
        val deviceInformation =
            DeviceInformationBuilder().withUserAgent(googleChromeLinuxUserAgent()).withIpAddress("192.168.1.1").build()
        val userTypeService = UserTypeService()

        val deviceInformationResult = userTypeService.createUserType(deviceInformation)

        assertNotNull(deviceInformationResult.userType)
        assertNull(deviceInformationResult.userType?.bot)
    }
}