package io.narok.services

import io.narok.models.DeviceInformation
import io.narok.models.DeviceSignature
import io.narok.services.Hash.toHex

class DeviceSignatureService : IDeviceSignatureService {

    private val version = 1

    override fun createSignature(deviceInformation: DeviceInformation): DeviceInformation {
        if (deviceInformation.userAgent.isBlank() || deviceInformation.ipAddress.isBlank()) return deviceInformation
        val md5 = Hash.md5("${deviceInformation.userAgent}${deviceInformation.ipAddress}")
        val deviceSignature = DeviceSignature(md5.toHex(), version)
        return deviceInformation.withSignature(deviceSignature)
    }
}