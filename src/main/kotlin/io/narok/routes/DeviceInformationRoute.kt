package io.narok.routes

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.narok.models.DeviceInformation
import io.narok.models.DeviceInformationRequest
import io.narok.models.deviceInformationStorage
import io.narok.plugins.RoutingConfig

object DeviceInformationRouteConfig {
    private const val route: String = "deviceInformation"
    const val path: String = "${RoutingConfig.version}/${route}"
}

fun Application.deviceInformationRouting() {
    routing {
        authenticate("auth-jwt") {
            post(DeviceInformationRouteConfig.path) {
                val deviceInformationRequest = call.receive<DeviceInformationRequest>()
                deviceInformationStorage.add(deviceInformationRequest)
                val deviceInformation = DeviceInformation.fromDeviceInformationRequest(deviceInformationRequest)
                call.respond(deviceInformation)
            }
        }
    }
}