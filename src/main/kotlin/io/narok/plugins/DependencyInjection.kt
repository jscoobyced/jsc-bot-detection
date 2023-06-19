package io.narok.plugins

import io.ktor.server.application.*
import io.narok.repo.FiftyOneDegreesRepo
import io.narok.repo.IFiftyOneDegreesRepo
import io.narok.repo.IQueueRepo
import io.narok.repo.RabbitMQueueRepo
import io.narok.services.DeviceInformationService
import io.narok.services.IDeviceInformationService
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.ktor.di
import org.kodein.di.singleton

val fiftyOneDegreesDIModule = DI.Module("fiftyOneDegreesDIModule") {
    bind<IFiftyOneDegreesRepo> { singleton { FiftyOneDegreesRepo() } }
}

fun queueRepoDIModule(rabbitMqHost: String): DI.Module {
    return DI.Module("queueRepoDIModule") {
        bind<IQueueRepo> { singleton { RabbitMQueueRepo(rabbitMqHost) } }
    }
}

fun mainDI(environment: ApplicationEnvironment): DI {
    val rabbitMqHost = environment.config.property("rabbitmq.host").getString()
    return DI {
        bind<IDeviceInformationService> { singleton { DeviceInformationService(di) } }
        import(fiftyOneDegreesDIModule)
        import(queueRepoDIModule(rabbitMqHost))
    }
}

fun Application.configureDI(environment: ApplicationEnvironment) {

    di {
        extend(mainDI(environment))
    }
}