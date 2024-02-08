package coroutines

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*

/**
 * In this pattern, coroutines are arranged in a series where each coroutine performs a part of the work and
 * then passes the result to the next coroutine in the pipeline. This can be highly efficient for processing streams of data.
 *
 * Scenario
 * Imagine we have a stream of numbers that we want to process through a pipeline with the following stages:
 * Producer: Generates a sequence of numbers.
 * Processor: Receives numbers, squares them, and sends the results down the pipeline.
 * Consumer: Receives the squared numbers and prints them.
 *
 * Key Components and Concepts
 *
 * produce Builder: This coroutine builder is used for creating a ProducerScope that can send elements to a channel.
 * In this example, produceNumbers uses produce to generate a sequence of numbers.
 *
 * Processing Pipeline: The squareNumbers function demonstrates a simple processing step in the pipeline.
 * It receives a ReceiveChannel<Int> (the output from produceNumbers), processes each received number by squaring it, and sends the result to its own channel.
 *
 * consumeEach Method: This is used by the consumer to receive and act on each squared number produced by the squareNumbers pipeline stage.
 * It simplifies consuming all elements from a ReceiveChannel.
 *
 * Structured Concurrency: The example demonstrates structured concurrency by launching coroutines in the context of the main coroutine scope.
 * This ensures that if the main coroutine is cancelled, all child coroutines are also cancelled, preventing leaks and ensuring the application shuts down cleanly.
 *
 * Sequential Processing: Although the processing steps are executed concurrently, the data flows through the pipeline sequentially —
 * each number is produced, then squared, and finally consumed in order.
 *
 * This advanced example illustrates how channels can be used to set up a processing pipeline with Kotlin Coroutines, allowing for efficient,
 * concurrent data processing and transformation in a structured and clean manner.
 */
// Produces a sequence of integers from 1 to 5
@OptIn(ExperimentalCoroutinesApi::class)
fun CoroutineScope.produceNumbers() = produce<Int> {
    for (x in 1..5) {
        send(x) // Send numbers to the channel
        delay(100) // Simulate work
    }
}

// Processes integers by squaring them
@OptIn(ExperimentalCoroutinesApi::class)
fun CoroutineScope.squareNumbers(numbers: ReceiveChannel<Int>) = produce<Int> {
    for (x in numbers) {
        send(x * x) // Square the number and send it to the next channel
    }
}

fun main() = runBlocking {
    val numbers = produceNumbers() // Produces numbers from 1 to 5
    val squares = squareNumbers(numbers) // Squares each number

    // Consume and print each squared number
    squares.consumeEach {
        println(it)
    }

    println("Done processing numbers.")
}
