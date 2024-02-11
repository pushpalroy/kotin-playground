package coroutines.flows

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    listOf(1, 2, 3)
        .asFlow()
        .map {
            delay(1000)
            "Emission: $it"
        }
        .collect {
            println(it)
        }
}