/**
 * When dealing with multiple threads, it's crucial to consider thread safety, especially when threads are accessing
 * shared resources. Kotlin (and Java) provide various mechanisms to handle synchronization and ensure thread safety,
 * such as the synchronized block and concurrent data structures from the java.util.concurrent package.
 *
 * This lock object is used in the synchronized block to ensure that only one thread can execute the code block
 * inside synchronized(lock) at a time. This approach avoids the error and ensures that the increment operation
 * on the counter variable is thread-safe.
 */

var counter = 0 // Shared resource
val lock = Object() // A lock object for synchronization

fun main() {
    val thread1 = Thread {
        increaseCounter()
    }
    val thread2 = Thread { increaseCounter() }

    thread1.start()
    thread2.start()

    thread1.join()
    thread2.join()

    println("Final counter value: $counter")
}

fun increaseCounter() {
    repeat(100000) {
        synchronized(lock) { // Use the lock object for synchronization
            counter++
        }
    }
}
