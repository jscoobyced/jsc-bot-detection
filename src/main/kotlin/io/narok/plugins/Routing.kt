package io.narok.plugins

import io.ktor.server.application.*
import io.narok.routes.defaultRouting
import io.narok.routes.deviceInformationRouting
import io.narok.routes.loginRouting
import io.narok.routes.shutdownRouting

object RoutingConfig {
    const val version = "/v1"
}

fun Application.configureRouting() {
    defaultRouting()
    shutdownRouting()
    loginRouting()
    deviceInformationRouting()
}
