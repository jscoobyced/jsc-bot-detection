package io.narok.repo

import io.narok.models.DeviceInformation

fun interface IQueueRepo {
    fun pushDeviceInformationToQueue(deviceInformation: DeviceInformation)
}