package com.ritty27.testcontainer

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension

class RedisContainerTest1 {

    companion object {
        @JvmField
        @RegisterExtension
        val redis = RedisTestContainer(listOf("spring.redis.host"), listOf("spring.redis.port"), false)
    }

    @Test
    fun test() {
        val host = System.getProperty("spring.redis.host")
        val port = System.getProperty("spring.redis.port")

        println("redis host: $host, port: $port")
    }
}