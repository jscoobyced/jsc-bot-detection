package io.narok.models

import kotlinx.serialization.Serializable

@Serializable
data class UserType(val isHuman: Boolean, val bot: Bot? = null): java.io.Serializable