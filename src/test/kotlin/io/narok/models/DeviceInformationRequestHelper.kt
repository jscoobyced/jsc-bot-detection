package io.narok.models

fun defaultDesktopDeviceInformationRequest(): DeviceInformationRequest {
    return DeviceInformationRequestBuilder("http://localhost", googleChromeLinuxUserAgent(), "192.168.1.1").build()
}

fun defaultMobileDeviceInformationRequest(): DeviceInformationRequest {
    return DeviceInformationRequestBuilder("http://localhost", mobileChromeLinuxUserAgent(), "192.168.1.1").build()
}

fun badBotDeviceInformationRequest(): DeviceInformationRequest {
    return DeviceInformationRequestBuilder("http://localhost", botUserAgent(), "192.168.1.1").build()
}

fun noIpAddressDeviceInformationRequest(): DeviceInformationRequest {
    return DeviceInformationRequestBuilder("http://localhost", googleChromeLinuxUserAgent(), "").build()
}

fun noUserAgentDeviceInformationRequest(): DeviceInformationRequest {
    return DeviceInformationRequestBuilder("http://localhost", "", "192.168.1.1").build()
}

fun malFormedUrlDeviceInformationRequest(): DeviceInformationRequest {
    return DeviceInformationRequestBuilder("http:/localhost", "", "192.168.1.1").build()
}
