package io.narok.plugins

import io.ktor.server.application.*
import io.narok.routes.defaultRouting
import io.narok.routes.deviceInformationRouting

object RoutingConfig {
    const val version = "/v1"
}

fun Application.configureRouting() {
    defaultRouting()
    deviceInformationRouting()
}
