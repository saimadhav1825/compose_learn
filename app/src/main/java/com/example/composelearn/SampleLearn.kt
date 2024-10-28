package com.example.composelearn

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        println("Before")
        firstSampleCoroutine()
        println("After Close firstSampleCoroutine")
        secondSampleCoroutine()
        println("After Close secondSampleCoroutine")
    }
}

suspend fun firstSampleCoroutine() {
    coroutineScope {
        delay(1000L)
        println("After")
    }
}

suspend fun secondSampleCoroutine() {
    coroutineScope {
        delay(1000L)
        println("After secondSampleCoroutine")
    }
}

