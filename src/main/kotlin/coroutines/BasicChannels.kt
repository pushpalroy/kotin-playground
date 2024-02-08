package coroutines

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel

/**
 * To illustrate how two coroutines can communicate with each other, we'll use a Channel in Kotlin.
 * Channels provide a way for coroutines to communicate by sending and receiving messages.
 * Think of a channel as a conduit for messages where one coroutine can send data into the channel,
 * and another coroutine can receive that data.
 *
 * Key Components
 *
 * Channel: We create a Channel<Int>() for sending and receiving integers between coroutines.
 * Channels are part of the kotlinx.coroutines library and serve as a communication mechanism between coroutines.
 *
 * Producer Coroutine: The producer coroutine sends integers from 1 to 5 into the channel using channel.send(x).
 * After sending all the integers, it closes the channel with channel.close(), signaling that no more data will be sent.
 *
 * Consumer Coroutine: The consumer coroutine receives integers from the channel using a for-loop (for (y in channel)),
 * which iterates over the channel's received values. The loop automatically terminates when the channel is closed, and all sent data has been received.
 *
 * Closing the Channel: It's important to close the channel when no more data will be sent. Closing the channel allows
 * the receiving side to finish, as the for-loop used for receiving data terminates once the channel is closed and all existing data has been processed.
 *
 * Waiting for Completion: The joinAll(producer, consumer) function waits for both coroutines to complete their execution.
 * This is necessary to ensure the program doesn't terminate prematurely, allowing both producer and consumer coroutines to finish their tasks.
 *
 * This example demonstrates basic communication between coroutines using a channel, which is a powerful concept in Kotlin
 * Coroutines for managing concurrent data flow. Channels support various modes and configurations, such as buffering options
 * and different types of channels (e.g., BroadcastChannel for broadcasting messages to multiple coroutines), providing flexibility
 * for more complex concurrency scenarios.
 */
fun main() = runBlocking {
    val channel = Channel<Int>() // Create a channel for integers

    // Producer coroutine
    val producer = launch {
        for (x in 1..5) {
            println("Producing $x")
            channel.send(x) // Send data into the channel
            delay(500) // Simulate work
        }
        channel.close() // Close the channel when done sending
    }

    // Consumer coroutine
    val consumer = launch {
        for (y in channel) { // Receive data from the channel
            println("Consuming $y")
        }
    }

    // Wait for both coroutines to finish
    joinAll(producer, consumer)
}
