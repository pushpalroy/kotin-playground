package coroutines

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

/**
 * Creating a custom coroutine scope can be useful for managing the lifecycle of coroutines within a specific context,
 * such as a UI component in Android or a service in a backend application. It allows you to tie the execution of coroutines
 * to the lifecycle of your application components, ensuring that coroutines are canceled when the component is destroyed or
 * no longer needed, thus preventing memory leaks and other related issues.
 *
 * Scenario
 *
 * Let's consider a scenario where we have a simple application component, such as a service in a backend application,
 * that needs to perform periodic tasks in the background while it is active. We'll create a custom coroutine scope for
 * this component and ensure that all coroutines launched within this scope are properly canceled when the component is stopped.
 */
// A simple application component that performs background tasks
class MyApplicationComponent : CoroutineScope {

    // Job for managing the lifecycle of coroutines within this component
    private val componentJob = Job()

    // Define the CoroutineScope's context combining a job and a dispatcher
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default + componentJob

    // Starts a background task that runs periodically
    fun startPeriodicTask() {
        launch {
            while (isActive) { // Continues until the scope's job is cancelled
                performTask()
                delay(1000) // Wait for 1 second before repeating the task
            }
        }
    }

    // Simulate performing a task
    private fun performTask() {
        println("Task performed at ${System.currentTimeMillis()}")
    }

    // Stops all coroutines when the component is stopped
    fun stop() {
        componentJob.cancel() // Cancels all coroutines started by this scope
    }
}

fun main() = runBlocking {
    val myComponent = MyApplicationComponent()
    myComponent.startPeriodicTask()

    // Simulate the component running for a bit before being stopped
    delay(5000) // Keep the component active for 5 seconds
    myComponent.stop() // Stop the component and its coroutines

    println("MyApplicationComponent stopped. Exiting...")
}
