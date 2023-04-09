package io.narok.services

import io.narok.models.DeviceInformation
import io.narok.models.DeviceType
import io.narok.repo.IDeviceTypeRepo

class DeviceTypeService(private val deviceTypeRepo: IDeviceTypeRepo) : IDeviceTypeService {
    override fun createDeviceType(deviceInformation: DeviceInformation): DeviceType {
        require(deviceInformation.userAgent.isNotBlank()) { "User-Agent cannot be blank." }
        return deviceTypeRepo.getDeviceType(deviceInformation)
    }

}
