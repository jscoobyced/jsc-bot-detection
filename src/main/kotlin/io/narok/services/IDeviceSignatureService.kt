package io.narok.services

import io.narok.models.DeviceInformation
import io.narok.models.DeviceSignature

fun interface IDeviceSignatureService {
    fun createSignature(deviceInformation: DeviceInformation): DeviceSignature
}