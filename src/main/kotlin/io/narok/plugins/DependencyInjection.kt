package io.narok.plugins

import io.ktor.server.application.*
import io.narok.repo.DeviceTypeRepo
import io.narok.repo.IDeviceTypeRepo
import io.narok.repo.IQueueRepo
import io.narok.repo.RabbitMQueueRepo
import io.narok.services.AppLogger
import io.narok.services.IUserTypeService
import io.narok.services.UserTypeService
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.ktor.di
import org.kodein.di.singleton

val fiftyOneDegreesDIModule = DI.Module("fiftyOneDegreesDIModule") {
    bind<IDeviceTypeRepo> { singleton { DeviceTypeRepo() } }
}

fun overrideableModules(rabbitMqHost: String): DI.Module {
    return DI.Module("overrideableModules") {
        bind<IQueueRepo> { singleton { RabbitMQueueRepo(rabbitMqHost) } }
        bind<IUserTypeService> { singleton { UserTypeService() } }
    }
}

fun mainDI(rabbitMqHost: String = ""): DI {
    AppLogger.logger().info("Binding main dependencies.")
    return DI {
        import(fiftyOneDegreesDIModule, allowOverride = true)
        import(overrideableModules(rabbitMqHost), allowOverride = true)
    }
}

fun Application.configureDI(environment: ApplicationEnvironment) {
    val rabbitMqHost = environment.config.property("rabbitmq.host").getString()

    di {
        extend(mainDI(rabbitMqHost))
    }
}
