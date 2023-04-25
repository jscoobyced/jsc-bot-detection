package io.narok.plugins

import io.ktor.server.application.*
import io.narok.repo.FiftyOneDegreesRepo
import io.narok.repo.IFiftyOneDegreesRepo
import io.narok.services.DeviceSignatureService
import io.narok.services.IDeviceSignatureService
import org.kodein.di.bind
import org.kodein.di.ktor.di
import org.kodein.di.singleton

fun Application.configureDI() {
    di {
        bind<IDeviceSignatureService> { singleton { DeviceSignatureService() } }
        bind<IFiftyOneDegreesRepo> { singleton { FiftyOneDegreesRepo() } }
    }
}