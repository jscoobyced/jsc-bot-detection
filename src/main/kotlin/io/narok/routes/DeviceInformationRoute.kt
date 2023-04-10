package io.narok.routes

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.narok.models.DeviceInformation
import io.narok.models.DeviceSignature
import io.narok.models.DeviceType
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
                val deviceInformation = call.receive<DeviceInformation>()
                deviceInformationStorage.add(deviceInformation)
                val deviceInformationWithSignature = DeviceInformation(
                    deviceInformation.url,
                    deviceInformation.userAgent,
                    deviceInformation.whiteListedCookies,
                    deviceInformation.ipAddress,
                    deviceInformation.sessionId,
                    DeviceSignature("9876543210", 1),
                    DeviceType(isBot = true, isHuman = false, isKnownBot = true, isBadBot = true)
                )
                call.respond(deviceInformationWithSignature)
            }
        }
    }
}