package io.narok.models

import kotlinx.serialization.Serializable

@Serializable
data class DeviceInformation(
    val url: String,
    val userAgent: String,
    val whiteListedCookies: Map<String, String>? = HashMap(),
    val ipAddress: String? = null,
    val sessionId: String? = null,
    val deviceSignature: DeviceSignature? = null,
    val deviceType: DeviceType? = null
)

val deviceInformationStorage = mutableListOf<DeviceInformation>()