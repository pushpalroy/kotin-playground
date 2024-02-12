package coroutines.flows

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.*
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
                .onEach { note ->
                    println("Collected: $note")
                    throw Exception("Error while collection note")
                }
                .catch { throwable ->
                    println("Exception caught in catch {} 1: $throwable")
                    emitAll(fallbackMusicFlow())
                }
                .catch { throwable ->
                    println("Exception caught in catch {} 2: $throwable")
                }
                .launchIn(this)
        }
    }
}
