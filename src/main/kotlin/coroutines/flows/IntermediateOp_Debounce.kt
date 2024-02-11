package coroutines.flows

import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

/**
 * Debounce: Returns a flow that mirrors the original flow, but filters out values that are followed by the
 * newer values within the given timeout. The latest value is always emitted.
 *
 * Note that the resulting flow does not emit anything as long as the original flow emits items faster than every timeoutMillis milliseconds.
 */
@OptIn(FlowPreview::class)
fun main() {
    runBlocking {
        flow {
            emit(1)
            delay(90)
            emit(2)
            delay(500)
            emit(3)
            delay(1010)
            emit(4)
            delay(1200)
            emit(5)
        }
            .debounce(700)
            .collect {
                println(it)
            }
    }
}