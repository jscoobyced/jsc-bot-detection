package io.narok.services

import io.narok.models.DeviceInformation

fun interface IUserTypeService {
    fun createUserType(deviceInformation: DeviceInformation): DeviceInformation
}