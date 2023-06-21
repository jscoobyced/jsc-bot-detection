package io.narok

import io.ktor.server.application.*
import io.narok.plugins.*
import io.narok.services.AppLogger
import org.kodein.di.ktor.di

fun Application.testModule() {
    AppLogger.logger().info("Starting test application.")

    di {
        extend(mainDI)
        import(queueRepoModule)
    }
    configureSentry()
    configureSerialization()
    configureHTTP()
    configureRouting()
}