package io.narok

import io.ktor.server.application.*
import io.ktor.server.netty.*
import io.narok.plugins.configureHTTP
import io.narok.plugins.configureRouting
import io.narok.plugins.configureSerialization

fun main(args: Array<String>): Unit = EngineMain.main(args)

@Suppress("Unused")
fun Application.module() {
    configureSerialization()
    configureHTTP()
    configureRouting()
}
