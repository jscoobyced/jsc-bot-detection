package io.narok.repo

import com.rabbitmq.client.ConnectionFactory
import io.narok.models.DeviceInformation
import io.narok.services.AppLogger
import org.apache.commons.lang3.SerializationUtils


class RabbitMQueueRepo(private val rabbitMqHost: String) : IQueueRepo {

    private val queueName = "device_information"
    private val factory by lazy {
        val factory = ConnectionFactory()
        factory.host = rabbitMqHost
        factory
    }

    init {
        AppLogger.logger().info("Creating a RabbitMQueueRepo")
    }


    override fun pushDeviceInformationToQueue(deviceInformation: DeviceInformation) {
        factory.newConnection().use { connection ->
            connection.createChannel().use { channel ->
                channel.queueDeclare(queueName, false, false, false, null)
                val data = SerializationUtils.serialize(deviceInformation)
                channel.basicPublish("", queueName, null, data)
                AppLogger.logger().info("Sent '${deviceInformation.deviceSignature?.signature}' to queue.")
            }
        }
    }
}
