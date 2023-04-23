package io.narok.services

import fiftyone.devicedetection.DeviceDetectionPipelineBuilder
import fiftyone.devicedetection.hash.engine.onpremise.flowelements.DeviceDetectionHashEngine
import fiftyone.devicedetection.hash.engine.onpremise.flowelements.DeviceDetectionHashEngineBuilder
import fiftyone.devicedetection.shared.DeviceData
import fiftyone.pipeline.core.data.FlowData
import fiftyone.pipeline.engines.Constants
import fiftyone.pipeline.util.FileFinder.getFilePath
import io.narok.models.DeviceInformation
import io.narok.models.DeviceType
import io.sentry.Sentry

class DeviceTypeService : IDeviceTypeService {
    override fun createDeviceType(deviceInformation: DeviceInformation): DeviceInformation {
        if (deviceInformation.userAgent.isBlank()) return deviceInformation
        try {
            val dataFile: String = getFilePath("51Degrees-LiteV4.1.hash").absolutePath
            val pipeline = DeviceDetectionPipelineBuilder()
                .useOnPremise(dataFile, false)
                .build()
            val flowData = pipeline.createFlowData()
            flowData.addEvidence("header.user-agent",deviceInformation.userAgent)
            flowData.process()
            val device: DeviceData = flowData.get(DeviceData::class.java)
            val deviceType = DeviceType(
                device.isCrawler?.value!!,
                device.isEReader?.value!! || device.isEmailBrowser?.value!! || device.isMobile?.value!! || device.isTablet?.value!!,
                false,
                false
            )
            return deviceInformation.withDeviceType(deviceType)
        } catch (exception: Exception) {
            Sentry.captureException(exception)
        }
        return deviceInformation
    }

}
