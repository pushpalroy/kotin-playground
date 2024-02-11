package coroutines.flows

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    listOf(1, 2, 3, 4, 5, 6, 7, 8)
        .asFlow()
        .take(3)
        .collect {
            println(it)
        }
}