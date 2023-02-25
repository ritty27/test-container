package com.ritty27.testcontainer

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension

class RedisContainerTest2 {

    companion object {
        @JvmField
        @RegisterExtension
        val redis = RedisTestContainer(listOf("my-redis:host"), listOf("my-redis:port"), false)
    }

    @Test
    fun test() {
        val host = System.getProperty("my-redis:host")
        val port = System.getProperty("my-redis:port")

        println("redis host: $host, port: $port")
    }
}