package io.narok.plugins

import io.ktor.server.application.*
import io.narok.repo.fiftyOneDegreesDIModule
import io.narok.services.DeviceInformationService
import io.narok.services.IDeviceInformationService
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.ktor.di
import org.kodein.di.singleton

val mainDI = DI {
    bind<IDeviceInformationService> { singleton { DeviceInformationService(di) } }
    import(fiftyOneDegreesDIModule)
}

fun Application.configureDI() {

    di {
        extend(mainDI)
    }
}