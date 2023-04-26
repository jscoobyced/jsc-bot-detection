package io.narok.services

import io.narok.models.DeviceInformation
import io.narok.models.DeviceInformationRequest
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.kodein.di.DI
import org.kodein.di.DIAware

class DeviceInformationService(
    override val di: DI,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default
) : IDeviceInformationService, DIAware {
    private val deviceSignatureService = DeviceSignatureService()
    private val deviceTypeService = DeviceTypeService(di)

    override suspend fun getDeviceInformation(deviceInformationRequest: DeviceInformationRequest): DeviceInformation {
        val deviceInformation = DeviceInformation.fromDeviceInformationRequest(deviceInformationRequest)

        val deviceInformationWithSignature = createSignature(deviceInformation)
        val deviceInformationWithDeviceType = createDeviceType(deviceInformation)

        return deviceInformation.withDeviceType(deviceInformationWithDeviceType.deviceType)
            .withSignature(deviceInformationWithSignature.deviceSignature)
    }

    private suspend fun createSignature(deviceInformation: DeviceInformation) = withContext(dispatcher) {
        deviceSignatureService.createSignature(deviceInformation)
    }

    private suspend fun createDeviceType(deviceInformation: DeviceInformation) = withContext(dispatcher) {
        deviceTypeService.createDeviceType(deviceInformation)
    }

}
