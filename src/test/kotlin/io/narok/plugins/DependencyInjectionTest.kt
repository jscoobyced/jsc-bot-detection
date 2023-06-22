package io.narok.plugins

import io.narok.repo.DummyQueueRepo
import io.narok.repo.IQueueRepo
import io.narok.services.AppLogger
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton

val queueRepoModule = DI.Module("queueRepoModule") {
    AppLogger.logger().info("Binding DummyQueueRepo to IQueueRepo")
    bind<IQueueRepo>(overrides = true) { singleton { DummyQueueRepo() } }
}