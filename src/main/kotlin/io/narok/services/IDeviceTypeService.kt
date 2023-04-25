package io.narok.services

import io.narok.models.DeviceInformation

fun interface IDeviceTypeService {
    fun createDeviceType(deviceInformation: DeviceInformation): DeviceInformation

}
