package coroutines.playground

import kotlinx.coroutines.*

/**
 * Structured concurrency is one of the main benefits of using Kotlin Coroutines.
 * In traditional programming, when we start a thread, it operates independently of the code that started it.
 * With structured concurrency, the newly created coroutine is bound within a specific scope and gets cancelled when the scope is cancelled.
 */
fun main() = runBlocking {
    withTimeout(2500) {
        launch {
            delay(1000L)
            println("Task from runBlocking")
        }

        coroutineScope {
            launch {
                delay(2000L)
                println("Task from nested launch")
            }

            delay(500L)
            println("Task from coroutine scope")
        }
    }
    println("Coroutine scope is over")
}