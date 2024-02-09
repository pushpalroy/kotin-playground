package coroutines.playground

import kotlinx.coroutines.*

/**
 * ensureActive is a helper function that throws a CancellationException if the coroutine has been cancelled.
 */
fun main() {
    runBlocking {
        val job = launch(Dispatchers.Default) {
            for (it in 0..1000) {
                ensureActive()
                withContext(Dispatchers.IO) {
                    Thread.sleep(500)
                }
                println("I am still going..")
            }
        }
        delay(2000)
        println("Cancelling..")

        job.cancelAndJoin()
        println("Cancelled")
    }
}