package coroutines.flows

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.flow

suspend fun main() {
    coroutineScope {
        flow {
            try {
                emit(1)
            } catch (e: Exception) {
                println("Exception in flow builder: $e")
                emit(2)
            }
        }
            .collect { emittedValue ->
                throw Exception("Exception in collect")
            }
    }
}