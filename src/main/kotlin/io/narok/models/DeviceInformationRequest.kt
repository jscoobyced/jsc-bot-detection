package io.narok.models

import kotlinx.serialization.Serializable

@Serializable
data class DeviceInformationRequest(
    val url: String,
    override val userAgent: String,
    override val ipAddress: String,
    override val whiteListedCookies: Map<String, String>? = mapOf(),
    override val whiteListedHttpHeaders: Map<String, String>? = mapOf(),
    override val sessionId: String?
) : IDeviceInformation

class DeviceInformationRequestBuilder(
    private val url: String, private val userAgent: String, private val ipAddress: String
) {
    private var whiteListedCookies: MutableMap<String, String> = HashMap()
    private var whiteListedHttpHeaders: MutableMap<String, String> = HashMap()
    private var sessionId: String? = null

    fun withWhiteListedCookies(cookies: Map<String, String>): DeviceInformationRequestBuilder {
        this.whiteListedCookies.putAll(cookies)
        return this
    }

    fun withSessionId(sessionId: String?): DeviceInformationRequestBuilder {
        this.sessionId = sessionId
        return this
    }

    fun build(): DeviceInformationRequest {
        return DeviceInformationRequest(
            url,
            userAgent,
            ipAddress,
            whiteListedCookies,
            whiteListedHttpHeaders,
            sessionId
        )
    }
}
