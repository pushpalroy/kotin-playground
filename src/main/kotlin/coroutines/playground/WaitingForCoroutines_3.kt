package coroutines.playground

import kotlinx.coroutines.*

/**
 * Joining all jobs running inside a coroutine is not a requirement to ensure their completion is waited for.
 * By using the coroutineScope builder (a function), you can create a parent/child relationship between coroutines.
 * More precisely, the coroutineScope builder will only progress once all the coroutines inside it have completed.
 * This is effectively doing a joinAll on all the jobs inside the coroutineScope.
 *
 * Each parent coroutine had to wait until their children finished.
 * This was enabled by using coroutineScope, ensuring each coroutine launched inside it had completed before moving on.
 */
fun main() {
    runBlocking {
        val jobs: List<Job> = (1..2).map { parentNumber ->
            // This coroutine is joined on inside [runBlocking] to allow the last [println]
            launch(context = Dispatchers.Default) {
                // The [coroutineScope] block cannot be left until the 2 corountines launched inside have finished
                coroutineScope {
                    println("[${Thread.currentThread().name}] Launched parent: $parentNumber")
                    (1..2).map { childNumber ->
                        launch {
                            println("[${Thread.currentThread().name}] Launched child: $parentNumber - $childNumber")
                            delay(1000)
                            println("[${Thread.currentThread().name}] Finished child: $parentNumber - $childNumber")
                        }
                    }
                }
                println("[${Thread.currentThread().name}] Finished parent: $parentNumber")
            }
        }
        println("[${Thread.currentThread().name}] Created all coroutines")
        jobs.joinAll()
        println("[${Thread.currentThread().name}] Finished all coroutines")
    }
}