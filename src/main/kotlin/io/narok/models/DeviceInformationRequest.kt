package io.narok.models

import kotlinx.serialization.Serializable

@Serializable
data class DeviceInformationRequest(
    val url: String,
    override val userAgent: String,
    override val whiteListedCookies: Map<String, String>? = mapOf(),
    override val ipAddress: String?,
    override val sessionId: String?
) : IDeviceInformation

class DeviceInformationRequestBuilder(private val url: String, private val userAgent: String) {
    private var whiteListedCookies: MutableMap<String, String> = HashMap()
    private var ipAddress: String? = null
    private var sessionId: String? = null

    fun withWhiteListedCookies(cookies: Map<String, String>): DeviceInformationRequestBuilder {
        this.whiteListedCookies.putAll(cookies)
        return this
    }

    fun withIpAddress(ipAddress: String?): DeviceInformationRequestBuilder {
        this.ipAddress = ipAddress
        return this
    }

    fun withSessionId(sessionId: String?): DeviceInformationRequestBuilder {
        this.sessionId = sessionId
        return this
    }

    fun build(): DeviceInformationRequest {
        return DeviceInformationRequest(url, userAgent, whiteListedCookies, ipAddress, sessionId)
    }
}
