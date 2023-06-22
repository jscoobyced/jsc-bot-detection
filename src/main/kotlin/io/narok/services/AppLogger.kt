package io.narok.services

import io.ktor.util.logging.*

object AppLogger {

    private val mainLogger = KtorSimpleLogger("io.narok")

    fun logger(): Logger {
        return mainLogger
    }
}