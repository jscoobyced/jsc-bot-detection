package io.narok.models

class DeviceInformationBuilder {

    private val domain = ""
    private val path = ""
    private val parameters = mapOf<String, List<String>>()
    private val isHttps = false
    private var userAgent = ""
    private var ipAddress: String = ""
    private val whiteListedCookies = mapOf<String, String>()
    private val whiteListedHttpHeaders = mapOf<String, String>()
    private val sessionId = ""
    private val deviceSignature: DeviceSignature? = null
    private val deviceType: DeviceType = DeviceType.UNASSIGNED
    private val userType: UserType? = null

    fun withUserAgent(userAgent: String): DeviceInformationBuilder {
        this.userAgent = userAgent
        return this
    }

    fun withIpAddress(ipAddress: String): DeviceInformationBuilder {
        this.ipAddress = ipAddress
        return this
    }

    fun build(): DeviceInformation {
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
}