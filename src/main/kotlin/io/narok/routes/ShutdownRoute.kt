package io.narok.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.engine.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.shutdownRouting() {
    val shutdown = ShutDownUrl("") { 1 }

    routing {
        authenticate("auth-jwt") {
            get("/shutdown") {
                if (!this@authenticate.environment?.developmentMode!!) {
                    shutdown.doShutdown(call)
                } else {
                    call.respond(HttpStatusCode.MethodNotAllowed)
                }
            }
        }
    }
}