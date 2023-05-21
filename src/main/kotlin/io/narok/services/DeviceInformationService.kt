package io.narok.services

import io.narok.models.*
import io.sentry.Sentry
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.kodein.di.DI
import org.kodein.di.DIAware

class DeviceInformationService(
    override val di: DI, private val dispatcher: CoroutineDispatcher = Dispatchers.Default
) : IDeviceInformationService, DIAware {
    private val deviceSignatureService = DeviceSignatureService()
    private val deviceTypeService = DeviceTypeService(di)
    private val userTypeService = UserTypeService()

    override suspend fun getDeviceInformation(deviceInformationRequest: DeviceInformationRequest): DeviceInformation {
        val deviceInformation = DeviceInformation.fromDeviceInformationRequest(deviceInformationRequest)

        val deviceSignature = createSignature(deviceInformation)
        val deviceType = createDeviceType(deviceInformation)
        val deviceInformationWithUserType = createUserType(deviceInformation)

        return deviceInformation.withDeviceType(deviceType).withSignature(deviceSignature)
            .withUserType(deviceInformationWithUserType.userType)
    }

    private suspend fun createSignature(deviceInformation: DeviceInformation) = withContext(dispatcher) {
        try {
            deviceSignatureService.createSignature(deviceInformation)
        } catch (exception: IllegalArgumentException) {
            Sentry.captureException(exception)
            DeviceSignature("", DeviceSignatureVersion.INVALID)
        }
    }

    private suspend fun createDeviceType(deviceInformation: DeviceInformation) = withContext(dispatcher) {
        try {
            deviceTypeService.createDeviceType(deviceInformation)
        } catch (exception: Exception) {
            Sentry.captureException(exception)
            DeviceType.UNKNOWN
        }
    }

    private suspend fun createUserType(deviceInformation: DeviceInformation) = withContext(dispatcher) {
        userTypeService.createUserType(deviceInformation)
    }

}
