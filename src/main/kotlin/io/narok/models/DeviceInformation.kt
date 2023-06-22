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
    override val ipAddress: String,
    override val whiteListedCookies: Map<String, String>? = mapOf(),
    override val whiteListedHttpHeaders: Map<String, String>? = mapOf(),
    override val sessionId: String?,
    val deviceSignature: DeviceSignature? = null,
    val deviceType: DeviceType = DeviceType.UNASSIGNED,
    val userType: UserType? = null
) : IDeviceInformation, java.io.Serializable {

    fun withSignature(deviceSignature: DeviceSignature?): DeviceInformation {
        return DeviceInformation(
            domain,
            path,
            parameters,
            isHttps,
            userAgent,
            ipAddress,
            whiteListedCookies,
            whiteListedHttpHeaders,
            sessionId,
            deviceSignature,
            deviceType,
            userType
        )
    }

    fun withDeviceType(deviceType: DeviceType): DeviceInformation {
        return DeviceInformation(
            domain,
            path,
            parameters,
            isHttps,
            userAgent,
            ipAddress,
            whiteListedCookies,
            whiteListedHttpHeaders,
            sessionId,
            deviceSignature,
            deviceType,
            userType
        )
    }

    fun withUserType(userType: UserType?): DeviceInformation {
        return DeviceInformation(
            domain,
            path,
            parameters,
            isHttps,
            userAgent,
            ipAddress,
            whiteListedCookies,
            whiteListedHttpHeaders,
            sessionId,
            deviceSignature,
            deviceType,
            userType
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
                deviceInformationRequest.ipAddress,
                deviceInformationRequest.whiteListedCookies,
                deviceInformationRequest.whiteListedHttpHeaders,
                deviceInformationRequest.sessionId
            )
        }
    }
}
