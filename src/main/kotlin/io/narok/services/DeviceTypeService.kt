package io.narok.services

import io.narok.models.DeviceInformation
import io.narok.models.DeviceType
import io.narok.repo.IFiftyOneDegreesRepo
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.instance

class DeviceTypeService(override val di: DI) : IDeviceTypeService, DIAware {
    override fun createDeviceType(deviceInformation: DeviceInformation): DeviceType {
        require(deviceInformation.userAgent.isNotBlank()) { "User-Agent cannot be blank." }
        val fiftyOneDegreesRepo: IFiftyOneDegreesRepo by di.instance<IFiftyOneDegreesRepo>()
        return fiftyOneDegreesRepo.getDeviceType(deviceInformation)
    }

}
