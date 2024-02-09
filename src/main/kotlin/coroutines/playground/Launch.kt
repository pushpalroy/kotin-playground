package coroutines.playground

import kotlinx.coroutines.*
import util.occurrenceOfWordInFile

fun main() {
    runBlocking {
        var count1: Int? = null
        val job1: Job = launch(context = Dispatchers.IO) {
            delay(1000)
            count1 = occurrenceOfWordInFile("some-build-file.log", "Kotlin")
        }
        var count2: Int? = null
        val job2: Job = launch(context = Dispatchers.IO) {
            delay(1000)
            count2 = occurrenceOfWordInFile("some-other-file.log", "build")
        }
        println("Calculating counts..")

        joinAll(job1, job2)
        println("Both results retrieved:\n$count1\n$count2")
    }
}