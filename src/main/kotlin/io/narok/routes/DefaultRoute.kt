package io.narok.routes

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.defaultRouting() {
    routing {
        get("/") {
            call.respond("")
        }
    }
}