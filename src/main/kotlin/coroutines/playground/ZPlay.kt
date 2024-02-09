package coroutines.playground

import kotlinx.coroutines.*

@OptIn(ExperimentalCoroutinesApi::class, DelicateCoroutinesApi::class)
fun main() {
    runBlocking {
        launch { // context of the parent, main runBlocking coroutine
            println("main runBlocking: I'm working in thread ${Thread.currentThread().name}: ${Thread.currentThread().threadGroup.name}")
        }
        launch(Dispatchers.Unconfined) { // not confined -- will work with main thread
            println("Unconfined: I'm working in thread ${Thread.currentThread().name}: ${Thread.currentThread().threadGroup.name}")
        }
        launch(Dispatchers.Default) { // will get dispatched to DefaultDispatcher
            println("Default: I'm working in thread ${Thread.currentThread().name}: ${Thread.currentThread().threadGroup.name}")
        }
        launch(newSingleThreadContext("MyOwnThread")) { // will get its own new thread
            println("newSingleThreadContext: I'm working in thread ${Thread.currentThread().name}: ${Thread.currentThread().threadGroup.name}")
        }
    }
}

//suspend fun main() = withContext(Dispatchers.Default) {
//    repeat(times = 20) {
//        launch { doSomething() }
//    }
//}
//
//fun doSomething() = runBlocking {
//    launch(Dispatchers.Default) {
//        println("Hello!")
//    }
//}