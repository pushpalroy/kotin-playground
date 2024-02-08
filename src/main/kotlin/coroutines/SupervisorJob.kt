package coroutines

import kotlinx.coroutines.*

/**
 * In Kotlin coroutines, Job and SupervisorJob play crucial roles in managing the lifecycle and hierarchy of coroutines.
 * They allow for structured concurrency by providing means to control the execution, cancellation, and completion of coroutines.
 * Here's an overview and example of both:
 *
 * Job
 * A Job represents a cancellable task with a lifecycle, such as a coroutine.
 * When you launch a coroutine in a scope, it's associated with a Job object that can be used to manage
 * its execution (e.g., cancellation). A key feature of jobs is their hierarchical nature; cancelling a
 * parent job will cancel all of its child jobs.
 *
 * SupervisorJob
 * A SupervisorJob is similar to Job but with a key difference in error handling. With a regular Job,
 * if one child coroutine fails (throws an exception), the parent job will cancel all other children.
 * However, a SupervisorJob allows its children to fail independently without affecting each other.
 * This is useful in cases where you want to handle errors locally within coroutines and not have a single
 * failure cancel the entire job hierarchy.
 */
fun main() = runBlocking {
    // Example with regular Job
    val job = launch { // This uses a regular Job implicitly
        val child1 = launch {
            println("Child 1 is running")
            delay(100)
            println("Child 1 will fail")
            throw Exception("Child 1 failure")
        }
        val child2 = launch {
            delay(200)
            println("Child 2 will not run to completion because of sibling failure")
        }
        try {
            joinAll(child1, child2)
        } catch (e: Exception) {
            println("Caught exception: ${e.message}")
        }
    }
    job.join()

    println("----")

    // Example with SupervisorJob
    val supervisorJob = SupervisorJob()
    val scopeWithSupervisorJob = CoroutineScope(coroutineContext + supervisorJob)
    scopeWithSupervisorJob.launch {
        val child1 = launch {
            println("Supervisor child 1 is running")
            delay(100)
            println("Supervisor child 1 will fail")
            throw Exception("Supervisor child 1 failure")
        }
        val child2 = launch {
            delay(200)
            println("Supervisor child 2 runs to completion despite sibling failure")
        }
        try {
            joinAll(child1, child2)
        } catch (e: Exception) {
            println("This will not be reached, as exceptions are not propagated to the parent")
        }
    }.join()

    println("Supervisor job example completed")
}
