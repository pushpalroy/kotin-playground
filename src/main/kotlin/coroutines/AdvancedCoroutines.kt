package coroutines

import kotlinx.coroutines.*
import kotlin.random.Random

/**
 * Imagine we need to fetch user data and their corresponding posts from a remote service concurrently.
 * We'll simulate these operations with delay to mimic network calls and use async to fetch data in parallel.
 * Additionally, we'll handle potential exceptions gracefully and ensure our coroutine code is structured properly.
 */
// Simulate fetching user data from a remote service
suspend fun fetchUserData(userId: Int): String {
    delay(1000) // Simulate network delay
    println("User data for $userId fetched on thread: ${Thread.currentThread().name}")
    return "UserData(userId=$userId)"
}

// Simulate fetching user posts from a remote service
suspend fun fetchUserPosts(userId: Int): List<String> {
    delay(1000) // Simulate network delay
    println("Posts for $userId fetched on thread: ${Thread.currentThread().name}")
    return listOf("Post1", "Post2", "Post3")
}

// Main coroutine to fetch user data and posts concurrently and handle exceptions
fun main() = runBlocking {
    val userId = Random.nextInt(1, 100) // Generate a random userId
    try {
        coroutineScope {
            val userDataDeferred = async { fetchUserData(userId) }
            val userPostsDeferred = async { fetchUserPosts(userId) }

            val userData = userDataDeferred.await()
            val userPosts = userPostsDeferred.await()
            println("User Data: $userData")
            println("User Posts: $userPosts")
        }
    } catch (e: Exception) {
        println("Error fetching data: ${e.message}")
    }
}
