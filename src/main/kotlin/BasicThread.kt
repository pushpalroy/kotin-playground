/**
 * In Kotlin, threading is a concept that allows you to perform tasks in parallel or concurrently within a program.
 * Kotlin runs on the Java Virtual Machine (JVM), and it utilizes the same threading model and APIs as Java.
 * This means you can use the Thread class and the Runnable interface to create and manage threads in Kotlin just like you would in Java.
 */
fun main() {
    // Creating a thread using the Thread constructor and passing a lambda as the task to run
    val thread1 = Thread {
        println("Running in a separate thread: ${Thread.currentThread().name}")
        for (i in 1..5) {
            println("i = $i")
            Thread.sleep(500) // Simulate some work by making the thread sleep
        }
    }

    // Start the thread
    thread1.start()

    // Continue doing something on the main thread
    println("This is the main thread: ${Thread.currentThread().name}")

    // Creating a Runnable
    val task = Runnable {
        println("Running in a separate thread: ${Thread.currentThread().name}")
        for (i in 1..5) {
            println("i = $i")
            Thread.sleep(500) // Simulate some work
        }
    }

    // Creating a thread with the runnable
    val thread2 = Thread(task)

    // Start the thread
    thread2.start()

    // Main thread
    println("This is the main thread: ${Thread.currentThread().name}")
}
