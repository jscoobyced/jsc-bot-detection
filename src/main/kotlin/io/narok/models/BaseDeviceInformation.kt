package io.narok.models

interface IDeviceInformation {
    val userAgent: String
    val ipAddress: String?
    val whiteListedCookies: Map<String, String>?
    val whiteListedHttpHeaders: Map<String, String>?
    val sessionId: String?
}
