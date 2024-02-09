package coroutines.playground

import kotlinx.coroutines.*
import util.occurrenceOfWordInFile

fun main() {
    runBlocking {
        val count1: Deferred<Int> = async(context = Dispatchers.IO) {
            delay(1000)
            occurrenceOfWordInFile("some-build-file.log", "Kotlin")
        }
        val count2: Deferred<Int> = async(context = Dispatchers.IO) {
            delay(1000)
            occurrenceOfWordInFile("some-other-file.log", "build")
        }
        println("Calculating counts..")
        println("Both results retrieved:\n${count1.await()}\n${count2.await()}")
    }
}