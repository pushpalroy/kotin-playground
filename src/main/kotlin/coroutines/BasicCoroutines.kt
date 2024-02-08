package coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Coroutines in Kotlin are a powerful feature for managing asynchronous programming and concurrency in a more efficient and simpler
 * way than traditional threading. Coroutines allow for non-blocking asynchronous execution, enabling you to write code that is both
 * easy to read and efficient, without the complexity often associated with multi-threading and callback functions.
 *
 * Let's demonstrate coroutines by writing a simple example that simulates fetching data from a network and
 * updating the UI (in a conceptual way since we're not in an actual Android environment).
 *
 */
fun main() {
    // Define a coroutine scope
    val scope = CoroutineScope(Dispatchers.Default)

    // Launch a coroutine in the scope
    scope.launch {
        val data = fetchData() // Simulate fetching data
        println("Data fetched: $data")
    }

    // Keep the JVM alive
    Thread.sleep(2000) // In a real application, you would not use Thread.sleep to wait for coroutines.
}

// Simulate a suspend function for fetching data
suspend fun fetchData(): String {
    delay(1000) // Simulate network delay
    return "Sample Data"
}
