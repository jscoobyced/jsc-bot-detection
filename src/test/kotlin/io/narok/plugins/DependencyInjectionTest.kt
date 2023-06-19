package io.narok.plugins

import io.narok.repo.DummyQueueRepo
import io.narok.repo.IQueueRepo
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton

val queueRepoModule = DI.Module("queueRepoModule") {
    bind<IQueueRepo> { singleton { DummyQueueRepo() } }
}