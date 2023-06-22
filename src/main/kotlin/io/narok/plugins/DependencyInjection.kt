package io.narok.plugins

import io.ktor.server.application.*
import io.narok.repo.DeviceTypeRepo
import io.narok.repo.IDeviceTypeRepo
import io.narok.repo.IQueueRepo
import io.narok.repo.RabbitMQueueRepo
import io.narok.services.AppLogger
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.ktor.di
import org.kodein.di.singleton

val fiftyOneDegreesDIModule = DI.Module("fiftyOneDegreesDIModule") {
    bind<IDeviceTypeRepo> { singleton { DeviceTypeRepo() } }
}

fun queueRepoDIModule(rabbitMqHost: String): DI.Module {
    return DI.Module("queueRepoDIModule") {
        AppLogger.logger().info("Binding RabbitMQueueRepo to IQueueRepo")
        bind<IQueueRepo> { singleton { RabbitMQueueRepo(rabbitMqHost) } }
    }
}

fun mainDI(rabbitMqHost: String = ""): DI {
    return DI {
        import(fiftyOneDegreesDIModule)
        import(queueRepoDIModule(rabbitMqHost), allowOverride = true)
    }
}

fun Application.configureDI(environment: ApplicationEnvironment) {
    val rabbitMqHost = environment.config.property("rabbitmq.host").getString()

    di {
        extend(mainDI(rabbitMqHost))
    }
}
