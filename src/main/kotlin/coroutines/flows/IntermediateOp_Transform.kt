package coroutines.flows

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    listOf(1, 2, 3, 4, 5, 6, 7, 8)
        .asFlow()
        .transform {
            emit(it)
            emit(it * 2)
            kotlinx.coroutines.delay(800)
        }
        .collect {
            println(it)
        }
}