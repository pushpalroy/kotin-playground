package coroutines.flows

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch

suspend fun main() {
    coroutineScope {
        launch {
            val musicFlow = musicFlow()
            musicFlow
                .onCompletion { cause ->
                    if (cause == null) {
                        println("Flow completed successfully")
                    } else {
                        println("Flow failed with exception: $cause")
                    }
                }
                .catch { throwable ->
                    println("Exception caught in catch {} 1: $throwable")
                    emitAll(fallbackMusicFlow())
                }
                .catch { throwable ->
                    println("Exception caught in catch {} 2: $throwable")
                }
                .collect { note ->
                    println("Collected: $note")
                }
        }
    }
}

fun musicFlow() = flow {
    delay(500)
    emit("A#")
    delay(500)
    emit("B")
    delay(500)
    emit("C")
    delay(500)
    emit("C#")
    delay(500)
    throw Exception("D Note exception")
}

fun fallbackMusicFlow() = flow {
    emit("D")
    delay(500)
    emit("D#")
    delay(500)
    emit("E")
    delay(500)
    emit("F")
    delay(500)
    emit("F#")
    delay(500)
    throw Exception("G Note exception")
}
