package coroutines.flows

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    listOf(1, 2, 3)
        .asFlow()
        .filterIsInstance<Int>()
        .filter {
            delay(1000)
            it > 1
        }
        .collect {
            println(it)
        }
}