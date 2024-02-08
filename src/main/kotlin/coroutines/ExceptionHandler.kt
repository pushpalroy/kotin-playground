package coroutines

import kotlinx.coroutines.*

/**
 * Utilizing a SupervisorJob with a custom coroutine exception handler allows you to achieve fine-grained control over
 * how exceptions are handled in your Kotlin coroutines. This setup is particularly useful when you want to prevent one
 * failing coroutine from affecting others in the same scope, while also logging or handling errors in a centralized manner.
 *
 * Scenario
 *
 * Let's say we have a supervisor scope where multiple coroutines perform various tasks. One of these tasks will fail, but we want to ensure that:
 * The failure does not cause other tasks to be cancelled.
 * The exception is handled gracefully by logging it or performing some recovery actions.
 */
fun main() = runBlocking {
    // Define a coroutine exception handler
    val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        println("Coroutine exception: ${exception.localizedMessage}")
    }

    val supervisorJob = SupervisorJob()
    // Create a CoroutineScope with a SupervisorJob and the exception handler
    val supervisorScope = CoroutineScope(supervisorJob + coroutineExceptionHandler)


    // Task 1 (will complete successfully)
    supervisorScope.launch {
        println("Task 1 is running")
        delay(2000) // Simulate some work
        println("Task 1 completed successfully")
    }

    // Task 2 (will fail)
    supervisorScope.launch {
        println("Task 2 is running")
        delay(200) // Simulate some work
        throw IllegalArgumentException("Task 2 encountered an error") // Fail
    }

    // Task 3 (will complete successfully despite Task 2's failure)
    supervisorScope.launch {
        println("Task 3 is running")
        delay(3000) // Simulate some work
        println("Task 3 completed successfully")
    }

    // Wait for all tasks to complete or fail
    supervisorJob.join()

    println("Supervisor scope has completed.")
}