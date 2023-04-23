package io.narok.services

import io.narok.models.DeviceInformation

interface IDeviceSignatureService {
    fun createSignature(deviceInformation: DeviceInformation): DeviceInformation
}