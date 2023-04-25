package io.narok.models

import kotlinx.serialization.Serializable

@Serializable
enum class DeviceType {
    MOBILE, DESKTOP, TABLET, UNASSIGNED, UNKNOWN
}