package io.narok.repo

import io.narok.models.DeviceInformation
import io.narok.models.DeviceType

fun interface IFiftyOneDegreesRepo {
    fun getDeviceType(deviceInformation: DeviceInformation): DeviceType
}