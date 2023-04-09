package io.narok.services

import io.narok.models.DeviceInformation
import io.narok.models.DeviceType

fun interface IDeviceTypeService {
    fun createDeviceType(deviceInformation: DeviceInformation): DeviceType

}
