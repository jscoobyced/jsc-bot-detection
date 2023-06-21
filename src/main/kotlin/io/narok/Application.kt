package io.narok

import io.ktor.server.application.*
import io.ktor.server.netty.*
import io.narok.plugins.*
import io.narok.services.AppLogger

fun main(args: Array<String>): Unit = EngineMain.main(args)

@Suppress("Unused")
fun Application.module() {
    AppLogger.logger().info("Starting main application.")

    configureDI(environment)
    configureSentry()
    configureSerialization()
    configureHTTP()
    configureRouting()
}