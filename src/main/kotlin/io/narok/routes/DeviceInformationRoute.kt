package io.narok.routes

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.plugins.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.narok.models.DeviceInformationRequest
import io.narok.plugins.RoutingConfig
import io.narok.services.IDeviceInformationService
import io.sentry.Sentry
import org.kodein.di.instance
import org.kodein.di.ktor.closestDI

object DeviceInformationRouteConfig {
    private const val route: String = "deviceInformation"
    const val path: String = "${RoutingConfig.version}/${route}"
}

fun Application.deviceInformationRouting() {
    routing {
        authenticate("auth-jwt") {
            post(DeviceInformationRouteConfig.path) {
                val transaction = Sentry.startTransaction(DeviceInformationRouteConfig.path, "post")
                try {
                    val deviceInformationRequest = call.receive<DeviceInformationRequest>()
                    val deviceInformationService by call.closestDI().instance<IDeviceInformationService>()
                    val deviceInformation = deviceInformationService.getDeviceInformation(deviceInformationRequest)
                    call.respond(deviceInformation)
                } catch (exception: BadRequestException) {
                    Sentry.captureException(exception)
                    throw exception
                } finally {
                    transaction.finish()
                }
            }
        }
    }
}
