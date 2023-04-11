package io.narok.routes

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.narok.models.DeviceInformation
import io.narok.models.DeviceInformationRequest
import io.narok.plugins.RoutingConfig
import io.sentry.Sentry

object DeviceInformationRouteConfig {
    private const val route: String = "deviceInformation"
    const val path: String = "${RoutingConfig.version}/${route}"
}

fun Application.deviceInformationRouting() {
    routing {
        authenticate("auth-jwt") {
            post(DeviceInformationRouteConfig.path) {
                val transaction = Sentry.startTransaction(DeviceInformationRouteConfig.path,"post")
                try {
                    val deviceInformationRequest = call.receive<DeviceInformationRequest>()
                    val deviceInformation = DeviceInformation.fromDeviceInformationRequest(deviceInformationRequest)
                    call.respond(deviceInformation)
                } catch (exception: ContentTransformationException) {
                    Sentry.captureException(exception)
                    throw exception
                } finally {
                    transaction.finish()
                }
            }
        }
    }
}
