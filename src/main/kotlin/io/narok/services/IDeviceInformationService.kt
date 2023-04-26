package io.narok.services

import io.narok.models.DeviceInformation
import io.narok.models.DeviceInformationRequest

fun interface IDeviceInformationService {
    suspend fun getDeviceInformation(deviceInformationRequest: DeviceInformationRequest): DeviceInformation
}