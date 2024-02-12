package coroutines.flows

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        launch {
            chordsFlow()
                .onCompletion { cause ->
                    println("Completed: $cause")
                }
                .catch { cause ->
                    println("Failed with exception: $cause")
                }
                .collect {
                    println("Collected: $it")
                }
        }
    }
}

fun chordsFlow() = flow {
    delay(500)
    emit("Am")
    delay(500)
    emit("C")
    delay(500)
    emit("Em")
    delay(500)
    throw NetworkException()
}.retryWhen { cause, attempt ->
    println("Retrying attempt: $attempt")
    delay(500 * attempt)
    attempt < 3 && cause is NetworkException
}

class NetworkException : Exception("Network failure")