package io.narok.plugins

import io.ktor.server.application.*
import io.narok.repo.DeviceTypeRepo
import io.narok.repo.IDeviceTypeRepo
import io.narok.repo.IQueueRepo
import io.narok.repo.RabbitMQueueRepo
import io.narok.services.*
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.ktor.di
import org.kodein.di.singleton

val nonOverrideableDIModules = DI.Module("nonOverrideableDIModules") {
    bind<IDeviceTypeRepo> { singleton { DeviceTypeRepo() } }
}

fun overrideableDIModules(rabbitMqHost: String): DI.Module {
    return DI.Module("overrideableDIModules") {
        bind<IQueueRepo> { singleton { RabbitMQueueRepo(rabbitMqHost) } }
        bind<IUserTypeService> { singleton { UserTypeService() } }
        bind<IDeviceInformationService> { singleton { DeviceInformationService(di) }}
    }
}

fun mainDI(rabbitMqHost: String = ""): DI {
    AppLogger.logger().info("Binding main dependencies.")
    return DI {
        import(nonOverrideableDIModules, allowOverride = true)
        import(overrideableDIModules(rabbitMqHost), allowOverride = true)
    }
}

fun Application.configureDI(environment: ApplicationEnvironment) {
    val rabbitMqHost = environment.config.property("rabbitmq.host").getString()

    di {
        extend(mainDI(rabbitMqHost))
    }
}
