package threads

/**
 * Let's consider a scenario where we implement a producer-consumer pattern using threads.
 * This pattern is a classic example of a multi-threading scenario where synchronization between threads is crucial
 * to ensure data integrity and to avoid race conditions.
 *
 * In this example, we'll create a simple queue where producers add items to the queue, and consumers remove
 * items from the queue. We'll ensure thread safety by using Kotlin's synchronized block and wait() / notify()
 * methods for communication between producer and consumer threads.
 */
class SimpleQueue<T> {
    private val lock = Object() // Lock object for synchronization
    private val queue = mutableListOf<T>()

    fun put(item: T) {
        synchronized(lock) {
            queue.add(item)
            println("Produced: $item")
            lock.notifyAll() // Notify all waiting threads that an item has been added
        }
    }

    fun take(): T {
        synchronized(lock) {
            while (queue.isEmpty()) {
                println("Queue is empty, consumer is waiting...")
                lock.wait() // Wait until the queue is not empty
            }
            return queue.removeAt(0).also {
                println("Consumed: $it")
            }
        }
    }
}

fun main() {
    val queue = SimpleQueue<Int>()
    val producer = Thread {
        repeat(5) {
            Thread.sleep((500..1000).random().toLong()) // Simulate work by sleeping
            queue.put(it)
        }
    }

    val consumer = Thread {
        repeat(5) {
            Thread.sleep((1000..1500).random().toLong()) // Simulate consuming work by sleeping
            queue.take()
        }
    }

    producer.start()
    consumer.start()

    producer.join()
    consumer.join()
}
