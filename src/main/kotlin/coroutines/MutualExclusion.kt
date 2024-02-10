package coroutines

import kotlinx.coroutines.*
import kotlinx.coroutines.sync.*
import kotlin.system.*

/**
 * Taken from: https://github.com/Kotlin/kotlinx.coroutines/blob/master/kotlinx-coroutines-core/jvm/test/guide/example-sync-06.kt
 *
 * Mutual exclusion solution to the problem is to protect all modifications of the shared state with a critical section
 * that is never executed concurrently. In a blocking world you'd typically use synchronized or ReentrantLock for that.
 * Coroutine's alternative is called Mutex. It has lock and unlock functions to delimit a critical section. The key
 * difference is that Mutex.lock() is a suspending function. It does not block a thread.
 *
 */
suspend fun massiveRun(action: suspend () -> Unit) {
    val n = 100  // number of coroutines to launch
    val k = 1000 // times an action is repeated by each coroutine
    val time = measureTimeMillis {
        coroutineScope { // scope for coroutines
            repeat(n) {
                launch {
                    repeat(k) { action() }
                }
            }
        }
    }
    println("Completed ${n * k} actions in $time ms")
}

val mutex = Mutex()
var counter = 0

fun main() = runBlocking {
    withContext(Dispatchers.Default) {
        massiveRun {
            // protect each increment with lock
            mutex.withLock {
                counter++
            }
        }
    }
    println("Counter = $counter")
}