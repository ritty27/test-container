package com.ritty27.testcontainer

import org.junit.jupiter.api.extension.AfterAllCallback
import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.ExtensionContext

/**
 * 환경변수로 host, port를 설정해야하는 경우:
 *  넘겨준 프로퍼티의 값으로 만들어진 컨테이너의 설정 지정된다.
 *
 *  @param isStopContainer
 *      true: 해당 테스트 종료 이후 컨테이너 제거
 *      false: 모든 테스트 종료 이후 컨테이너 제거
 */
open class RedisTestContainer(
    private val needPropertySettingHostNames: List<String>,
    private val needPropertySettingPortNames: List<String>,
    private val isStopContainer: Boolean
) : BeforeAllCallback, AfterAllCallback {

    companion object {
        private val redis by lazy { RedisContainer() }
    }

    override fun beforeAll(context: ExtensionContext?) {
        redis.start()

        needPropertySettingHostNames.forEach {
            System.setProperty(it, redis.getHost())
        }

        needPropertySettingPortNames.forEach {
            System.setProperty(it, redis.getPort())
        }
    }

    override fun afterAll(context: ExtensionContext?) {
        if(isStopContainer) redis.stop()
    }

}