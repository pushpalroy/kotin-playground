package coroutines.flows

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    listOf(1, 1, 1, 2, 3, 4, 4, 5, 1)
        .asFlow()
        .distinctUntilChanged()
        .collect {
            println(it)
        }
}