package io.narok.models

interface IDeviceInformation {
    val userAgent: String
    val whiteListedCookies: Map<String, String>?
    val ipAddress: String?
    val sessionId: String?
}
