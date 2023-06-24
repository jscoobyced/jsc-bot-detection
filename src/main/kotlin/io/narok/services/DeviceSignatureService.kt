package io.narok.services

import io.narok.models.DeviceInformation
import io.narok.models.DeviceSignature
import io.narok.models.DeviceSignatureVersion
import io.narok.services.Hash.toHex

class DeviceSignatureService : IDeviceSignatureService {
    override fun createSignature(deviceInformation: DeviceInformation): DeviceSignature {
        return createSignatureV1(deviceInformation)
    }

    private fun createSignatureV1(deviceInformation: DeviceInformation): DeviceSignature {
        require(deviceInformation.userAgent.isNotBlank() && deviceInformation.ipAddress.isNotBlank()) { "User-Agent nor IpAddress cannot be blank." }
        val md5 = Hash.md5("${deviceInformation.userAgent}${deviceInformation.ipAddress}")
        return DeviceSignature(md5.toHex(), DeviceSignatureVersion.ONE)
    }
}