package io.narok.plugins

import io.narok.repo.DummyQueueRepo
import io.narok.repo.IQueueRepo
import io.narok.services.AppLogger
import io.narok.services.DummyUserTypeService
import io.narok.services.IUserTypeService
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton

val queueRepoModule = DI.Module("overridingModules") {
    AppLogger.logger().info("Overriding test dependencies.")
    bind<IQueueRepo>(overrides = true) { singleton { DummyQueueRepo() } }
    bind<IUserTypeService>(overrides = true) { singleton { DummyUserTypeService() } }
}