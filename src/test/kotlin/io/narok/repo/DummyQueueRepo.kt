package io.narok.repo

import io.narok.models.DeviceInformation

class DummyQueueRepo: IQueueRepo {
    override fun pushDeviceInformationToQueue(deviceInformation: DeviceInformation) {

    }
}