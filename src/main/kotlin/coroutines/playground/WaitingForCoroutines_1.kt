package coroutines.playground

import kotlinx.coroutines.*

/**
 * join is a suspending function. Meaning that the coroutine calling it will be suspended until it is told to resume.
 * At the point of suspension, the executing thread is released to any other available coroutines (that are sharing that thread or thread pool).
 *
 * The main thread is blocked while it waits for the job/coroutine to finish.
 * Note, that the main thread is used by runBlocking, while the child is launched using one of the default thread pools.
 */
fun main() {
    runBlocking {
        val job: Job = launch(context = Dispatchers.Default) {
            println("[${Thread.currentThread().name}] Launched coroutine")
            delay(3000)
            println("[${Thread.currentThread().name}] Finished coroutine")
        }
        println("[${Thread.currentThread().name}] Created coroutine")
        job.join()
        println("[${Thread.currentThread().name}] Finished coroutine")
    }
}