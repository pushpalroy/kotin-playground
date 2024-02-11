package coroutines.flows

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

/**
 * Flows adhere to the general cooperative cancellation of coroutines. As usual, a flow collection can be cancelled
 * when the flow is suspended in a cancellable suspending function (like delay). The following example shows how
 * the flow gets cancelled on a timeout when running in a withTimeoutOrNull block and stops executing its code
 */
fun simpleCancel(): Flow<Int> = flow {
    for (i in 1..3) {
        delay(100)
        println("Emitting $i")
        emit(i)
    }
}

fun main() = runBlocking {
    withTimeoutOrNull(250) { // Timeout after 250ms
        simpleCancel().collect { value -> println(value) }
    }
    println("Done")
}