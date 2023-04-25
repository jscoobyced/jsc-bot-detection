package io.narok.services

import io.narok.models.DeviceInformation
import io.narok.repo.IFiftyOneDegreesRepo
import io.sentry.Sentry
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.instance

class DeviceTypeService(override val di: DI) : IDeviceTypeService, DIAware {
    override fun createDeviceType(deviceInformation: DeviceInformation): DeviceInformation {
        if (deviceInformation.userAgent.isBlank()) return deviceInformation
        val fiftyOneDegreesRepo: IFiftyOneDegreesRepo by di.instance<IFiftyOneDegreesRepo>()

        try {
            val deviceType = fiftyOneDegreesRepo.getDeviceType(deviceInformation)
            return deviceInformation.withDeviceType(deviceType)
        } catch (exception: Exception) {
            Sentry.captureException(exception)
        }

        return deviceInformation
    }

}
