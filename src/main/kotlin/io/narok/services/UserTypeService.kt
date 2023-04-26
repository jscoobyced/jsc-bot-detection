package io.narok.services

import io.narok.models.Bot
import io.narok.models.DeviceInformation
import io.narok.models.UserType

class UserTypeService : IUserTypeService {

    override fun createUserType(deviceInformation: DeviceInformation): DeviceInformation {
        val bot = Bot(
            "Johnny", true, isBadBot = true
        )
        return deviceInformation.withUserType(UserType(false, bot))
    }

}
