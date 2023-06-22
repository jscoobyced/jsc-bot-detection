package io.narok.services

import io.narok.models.DeviceInformation
import io.narok.models.DeviceInformationRequest
import io.narok.models.DeviceSignature
import io.narok.models.DeviceType
import io.narok.repo.IDeviceTypeRepo
import io.narok.repo.IQueueRepo
import io.sentry.Sentry
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.instance

class DeviceInformationService(
    override val di: DI, private val dispatcher: CoroutineDispatcher = Dispatchers.Default
) : IDeviceInformationService, DIAware {

    private val deviceSignatureService = DeviceSignatureService()
    private val deviceTypeRepo: IDeviceTypeRepo by di.instance<IDeviceTypeRepo>()
    private val deviceTypeService = DeviceTypeService(deviceTypeRepo)
    private val userTypeService = UserTypeService()
    private val queueRepo: IQueueRepo by di.instance<IQueueRepo>()

    override suspend fun getDeviceInformation(deviceInformationRequest: DeviceInformationRequest): DeviceInformation {
        val deviceInformation = DeviceInformation.fromDeviceInformationRequest(deviceInformationRequest)

        val deviceSignature = createSignature(deviceInformation)
        val deviceType = createDeviceType(deviceInformation)
        val deviceInformationWithUserType = createUserType(deviceInformation)

        AppLogger.logger().info("Done processing device information request.")

        val processedDeviceInformation = deviceInformation.withDeviceType(deviceType).withSignature(deviceSignature)
            .withUserType(deviceInformationWithUserType.userType)
        // try {
        queueRepo.pushDeviceInformationToQueue(processedDeviceInformation)
        /*
        } catch (exception: Exception) {
                Sentry.captureException(exception)
            }

         */
        return processedDeviceInformation
    }

    private fun createSignature(deviceInformation: DeviceInformation): DeviceSignature {
        return try {
            deviceSignatureService.createSignature(deviceInformation)
        } catch (exception: IllegalArgumentException) {
            Sentry.captureException(exception)
            throw exception
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
