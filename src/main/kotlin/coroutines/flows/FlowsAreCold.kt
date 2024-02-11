package coroutines.flows

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

/**
 * Taken from: https://github.com/Kotlin/kotlinx.coroutines/blob/master/kotlinx-coroutines-core/jvm/test/guide/example-flow-05.kt
 *
 * Flows are cold streams similar to sequences — the code inside a flow builder does not run until the flow is collected.
 * This is a key reason the simple function (which returns a flow) is not marked with suspend modifier.
 * The simpleDo() call itself returns quickly and does not wait for anything.
 * The flow starts afresh every time it is collected and that is why we see "Flow started" every time we call collect again.
 */
fun simpleDo(): Flow<Int> = flow {
    println("Flow started")
    for (i in 1..3) {
        delay(1000)
        emit(i)
    }
}

fun main() = runBlocking {
    println("Calling simple function...")
    val flow = simpleDo()
    println("Calling collect...")
    flow.collect { value -> println(value) }
    println("Calling collect again...")
    flow.collect { value -> println(value) }
}