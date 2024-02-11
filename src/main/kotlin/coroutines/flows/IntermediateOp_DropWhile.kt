package coroutines.flows

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.dropWhile
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    listOf(1, 2, 3, 4, 5, 6, 7, 8)
        .asFlow()
        .dropWhile {
            it < 3
        }
        .collect {
            println(it)
        }
}