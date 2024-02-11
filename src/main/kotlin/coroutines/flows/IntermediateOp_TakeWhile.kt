package coroutines.flows

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.takeWhile
import kotlinx.coroutines.runBlocking

/**
 * Difference between takeWhile and filter:
 * Filter does not cancel the upstream flow, while in takeWhile once the predicate is false
 * the upstream flow is cancelled, and no more items are emitted.
 */
fun main() = runBlocking {
    listOf(1, 2, 3, 4, 5, 6, 7, 8)
        .asFlow()
//        .takeWhile {
//            it > 3
//        }
        .takeWhile {
            it < 3
        }
        .collect {
            println(it)
        }
}