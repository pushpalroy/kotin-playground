package coroutines.playground

import kotlinx.coroutines.*

/**
 * Just like futures and threads, many coroutines can be created and waited for through the use of join.
 * This is also made slightly easier by the convenient joinAll extension function.
 */
fun main() {
    runBlocking {
        val jobs: List<Job> = (1..5).map {
            launch(context = Dispatchers.Default) {
                println("[${Thread.currentThread().name}] Launched coroutine: $it")
                delay(2000)
                println("[${Thread.currentThread().name}] Finished coroutine: $it")
            }
        }
        println("[${Thread.currentThread().name}] Created all coroutines")
        jobs.joinAll()
        println("[${Thread.currentThread().name}] Finished all coroutines")
    }
}