package com.rmworld.core.network

import javax.inject.Qualifier

import kotlin.annotation.AnnotationRetention.RUNTIME

@Qualifier
@Retention(RUNTIME)
annotation class Dispatcher(val niaDispatcher: RMWorldDispatchers)

enum class RMWorldDispatchers {
    Default,
    IO,
}