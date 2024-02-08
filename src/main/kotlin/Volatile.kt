/**
 * In Kotlin, the @Volatile annotation marks a property as volatile, meaning that writes to this property are
 * immediately made visible to other threads. This is important for multithreaded applications where you need
 * to ensure that changes made by one thread are visible to all other threads in a timely manner, typically for
 * the purpose of achieving thread safety for certain operations without using synchronization mechanisms like
 * synchronized blocks or methods.
 *
 * Here's a simple example that demonstrates the use of the @Volatile annotation in Kotlin.
 * This example uses two threads: one that updates a @Volatile variable, and another that reads this variable.
 * The use of @Volatile ensures that the updates made to the variable by one thread are visible to the other thread.
 *
 * We have two threads:
 * incrementThread increments number from 1 to 1000.
 * readThread continuously reads number and prints a message whenever it observes a change in its value.
 *
 * Without the @Volatile annotation, there is no guarantee that the readThread would see the updates to number
 * made by the incrementThread promptly, possibly leading to a situation where readThread is stuck in an infinite
 * loop because it never observes the updates to number.
 */
class Counter {
    @Volatile
    var number: Int = 0
}

fun main() {
    val counter = Counter()

    // Thread for incrementing the number
    val incrementThread = Thread {
        for (i in 1..1000) {
            counter.number++
        }
    }

    // Thread for reading the number
    val readThread = Thread {
        var oldValue = counter.number
        while (oldValue < 1000) {
            val newValue = counter.number
            if (newValue != oldValue) {
                println("New value observed: $newValue")
                oldValue = newValue
            }
        }
    }

    // Start both threads
    readThread.start()
    incrementThread.start()

    // Wait for both threads to complete
    incrementThread.join()
    readThread.join()
}
