package io.narok

import io.ktor.server.application.*
import io.ktor.server.netty.*
import io.narok.plugins.*

fun main(args: Array<String>): Unit = EngineMain.main(args)

@Suppress("Unused")
fun Application.module() {
    configureDI(environment)
    configureSentry()
    configureSerialization()
    configureHTTP()
    configureRouting()
}
