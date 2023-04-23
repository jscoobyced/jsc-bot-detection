package io.narok.services

import io.narok.models.DeviceInformation

fun interface IDeviceSignatureService {
    fun createSignature(deviceInformation: DeviceInformation): DeviceInformation
}