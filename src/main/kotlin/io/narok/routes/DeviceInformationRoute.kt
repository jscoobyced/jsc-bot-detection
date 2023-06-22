package io.narok.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.plugins.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.narok.models.DeviceInformationRequest
import io.narok.models.ErrorResponse
import io.narok.plugins.RoutingConfig
import io.narok.services.DeviceInformationService
import io.sentry.Sentry
import org.kodein.di.ktor.closestDI
import java.net.UnknownHostException

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
                    val deviceInformationService = DeviceInformationService(call.closestDI())
                    val deviceInformation = deviceInformationService.getDeviceInformation(deviceInformationRequest)
                    call.respond(deviceInformation)
                } catch (exception: CannotTransformContentToTypeException) {
                    Sentry.captureException(exception)
                    call.respond(HttpStatusCode.InternalServerError, ErrorResponse(exception.toString()))
                } catch (exception: NullPointerException) {
                    Sentry.captureException(exception)
                    call.respond(HttpStatusCode.InternalServerError, ErrorResponse(exception.toString()))
                } catch (exception: BadRequestException) {
                    Sentry.captureException(exception)
                    call.respond(HttpStatusCode.InternalServerError, ErrorResponse(exception.toString()))
                } catch (exception: IllegalArgumentException) {
                    Sentry.captureException(exception)
                    call.respond(HttpStatusCode.InternalServerError, ErrorResponse(exception.toString()))
                } catch (exception: UnknownHostException) {
                    Sentry.captureException(exception)
                    call.respond(HttpStatusCode.InternalServerError, ErrorResponse(exception.toString()))
                } finally {
                    transaction.finish()
                }
            }
        }
    }
}
