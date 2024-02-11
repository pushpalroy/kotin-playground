package coroutines.flows

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlin.coroutines.EmptyCoroutineContext

fun main() {
    val flow = flow {

        delay(500)
        println("Emitting 1")
        emit(1)

        delay(1000)
        println("Emitting 2")
        emit(2)

        delay(1500)
        println("Emitting 3")
        emit(3)
    }

    val scope = CoroutineScope(EmptyCoroutineContext)
    flow
        .onEach { println("Received in launchIn(): $it") }
        .onStart { println("The flow starts to be collected") } // Lifecycle operator
        .onCompletion { println("The flow has completed") } // Lifecycle operator
        .launchIn(scope)

    // The above code is same as
    //    scope.launch {
    //        flow.collect {
    //            println("Received in flow.collect(): $it")
    //        }
    //    }

    Thread.sleep(3500)
}