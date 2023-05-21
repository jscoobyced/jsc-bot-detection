package io.narok.models

import kotlinx.serialization.Serializable

enum class DeviceSignatureVersion {
    INVALID,
    ONE
}

@Serializable
data class DeviceSignature(val signature: String, val version: DeviceSignatureVersion)