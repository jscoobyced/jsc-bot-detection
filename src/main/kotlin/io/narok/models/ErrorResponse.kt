package io.narok.models

import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(val message: String)
