package io.narok.plugins

import io.ktor.server.application.*
import io.sentry.Sentry

fun Application.configureSentry() {
    if (!environment.developmentMode) {
        val dsn = environment.config.property("sentry.dsn").getString()
        val sampling = environment.config.property("sentry.sampling").getString().toDouble()
        val isDebug = environment.config.property("sentry.debug").getString().toBoolean()

        if (dsn.isBlank()) throw RuntimeException("Cannot start without SENTRY_DSN environment variable.")

        Sentry.init { options ->
            options.dsn = dsn
            options.tracesSampleRate = sampling
            options.isDebug = isDebug
        }
    }
}