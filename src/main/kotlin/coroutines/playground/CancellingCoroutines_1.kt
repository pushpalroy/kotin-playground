package coroutines.playground

import kotlinx.coroutines.*

/**
 * A coroutine needs to cooperate to be cancellable.
 * In other words, you need to take into account the contents of your coroutines to ensure that they can be cancelled.
 * You can make your coroutines cancellable by following the two options below:
 *
 * Calling any suspending functions from kotlinx.coroutines
 * Using CoroutineScope.isActive and handling the outcome appropriately
 *
 * This example checks the isActive flag as part of the while loop. Once the job is cancelled, the value becomes false, and the loop ends.
 */
fun main() {
    runBlocking {
        val job = launch(Dispatchers.Default) {
            while (isActive) {
                withContext(Dispatchers.IO) {
                    Thread.sleep(300)
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