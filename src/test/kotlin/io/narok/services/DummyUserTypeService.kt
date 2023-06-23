package io.narok.services

import io.narok.models.Bot
import io.narok.models.DeviceInformation
import io.narok.models.UserType
import io.narok.models.botUserAgent

class DummyUserTypeService : IUserTypeService {
    override fun createUserType(deviceInformation: DeviceInformation): DeviceInformation {
        if (deviceInformation.userAgent == botUserAgent()) {
            return deviceInformation.withUserType(UserType(false, Bot("bad-bot", isKnownBot = false, isBadBot = true)))
        }
        return deviceInformation
    }
}