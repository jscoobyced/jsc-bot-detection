package io.narok.repo

import io.narok.models.DeviceInformation
import io.narok.models.DeviceType
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton

class FiftyOneDegreesFailingRepo : IFiftyOneDegreesRepo {
    override fun getDeviceType(deviceInformation: DeviceInformation): DeviceType {
        throw NullPointerException("Testing only")
    }
}

val fiftyOneDegreesTestDIModule = DI.Module("fiftyOneDegreesTestDIModule") {
    bind<IFiftyOneDegreesRepo>(overrides = true) { singleton { FiftyOneDegreesFailingRepo() } }
}