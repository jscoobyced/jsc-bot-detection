package io.narok.repo

import io.narok.models.DeviceInformation
import io.narok.services.AppLogger

class DummyQueueRepo : IQueueRepo {

    init {
        AppLogger.logger().info("Creating a DummyQueueRepo")
    }

    override fun pushDeviceInformationToQueue(deviceInformation: DeviceInformation) {
    }
}