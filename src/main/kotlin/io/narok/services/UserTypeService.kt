package io.narok.services

import io.narok.models.DeviceInformation
import io.narok.models.UserType

class UserTypeService : IUserTypeService {

    override fun createUserType(deviceInformation: DeviceInformation): DeviceInformation {
        return deviceInformation.withUserType(UserType(true))
    }

}
