package com.ritty27.testcontainer

import org.testcontainers.containers.GenericContainer
import org.testcontainers.utility.DockerImageName

internal class RedisContainer {
    companion object {
        private val redis by lazy {
            object :
                GenericContainer<Nothing>(DockerImageName.parse("redis:7.0.8")) {}.apply {
                withExposedPorts(6379)
            }
        }

    }

    fun start() {
        if (redis.isRunning.not()) redis.start()
    }

    fun stop() {
        if (redis.isRunning) {
            redis.stop()
            println("stop")
        }
    }

    fun getHost(): String = redis.containerIpAddress

    fun getPort(): String = redis.firstMappedPort.toString()

}
