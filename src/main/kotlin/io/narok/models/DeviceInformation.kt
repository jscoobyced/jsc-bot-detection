package io.narok.models

import io.ktor.http.*
import io.ktor.util.*
import kotlinx.serialization.Serializable
import java.net.URI

@Serializable
data class DeviceInformation(
    val domain: String,
    val path: String,
    val parameters: Map<String, List<String>>? = mapOf(),
    val isHttps: Boolean,
    override val userAgent: String,
    override val whiteListedCookies: Map<String, String>? = mapOf(),
    override val ipAddress: String?,
    override val sessionId: String?,
    val deviceSignature: DeviceSignature? = null,
    val deviceType: DeviceType? = null
) : IDeviceInformation {

    fun withSignature(deviceSignature: DeviceSignature): DeviceInformation {
        return DeviceInformation(
            domain, path, parameters, isHttps, userAgent, whiteListedCookies, ipAddress, sessionId, deviceSignature
        )
    }

    companion object {
        fun fromDeviceInformationRequest(deviceInformationRequest: DeviceInformationRequest): DeviceInformation {
            val uri = URI(deviceInformationRequest.url)
            return DeviceInformation(
                uri.host,
                uri.path,
                Url(deviceInformationRequest.url).parameters.toMap(),
                uri.scheme?.toLowerCasePreservingASCIIRules() == "https",
                deviceInformationRequest.userAgent,
                deviceInformationRequest.whiteListedCookies,
                deviceInformationRequest.ipAddress,
                deviceInformationRequest.sessionId
            )
        }
    }
}
