package io.narok.models

import kotlinx.serialization.Serializable

@Serializable
data class DeviceType(val isBot: Boolean, val isHuman: Boolean, val isKnownBot: Boolean, val isBadBot: Boolean)