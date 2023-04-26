package io.narok.models

import kotlinx.serialization.Serializable

@Serializable
data class Bot(val name: String, val isKnownBot: Boolean, val isBadBot: Boolean)
