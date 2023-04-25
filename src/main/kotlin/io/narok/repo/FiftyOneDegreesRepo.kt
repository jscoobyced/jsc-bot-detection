package io.narok.repo

import fiftyone.devicedetection.DeviceDetectionPipelineBuilder
import fiftyone.devicedetection.shared.DeviceData
import fiftyone.pipeline.core.flowelements.Pipeline
import fiftyone.pipeline.util.FileFinder
import io.narok.models.DeviceInformation
import io.narok.models.DeviceType
import io.sentry.Sentry

class FiftyOneDegreesRepo : IFiftyOneDegreesRepo {

    private val lazyPipeline: Pipeline by lazy {
        val dataFile: String = FileFinder.getFilePath("51Degrees-LiteV4.1.hash").absolutePath
        DeviceDetectionPipelineBuilder()
            .useOnPremise(dataFile, false)
            .build()
    }

    override fun getDeviceType(deviceInformation: DeviceInformation): DeviceType {
        try {

            val flowData = lazyPipeline.createFlowData()
            flowData.addEvidence("header.user-agent", deviceInformation.userAgent)
            flowData.process()
            val device: DeviceData = flowData.get(DeviceData::class.java)
            return if (device.isMobile.hasValue() && device.isMobile.value) DeviceType.MOBILE else DeviceType.DESKTOP
        } catch (exception: NullPointerException) {
            Sentry.captureException(exception)
        }

        return DeviceType.UNKNOWN
    }
}