/**
 * In Kotlin, inline, noinline, and crossinline are keywords used to control the behavior of higher-order functions and lambdas.
 * Here's an explanation of each, followed by an example that illustrates how to use them:
 *
 * inline: When you mark a function with the inline keyword, the compiler will inline the function and the
 * lambda expressions passed to it at the call sites. This means the bytecode of these functions and lambdas
 * is directly inserted into the places where the function is called, eliminating the overhead of creating
 * anonymous classes for lambdas and function calls. It's useful for performance optimization, especially for
 * functions with lambda parameters.
 *
 * noinline: Used within an inline function to mark certain lambda parameters that should not be inlined.
 *
 * crossinline: Allows you to mark a lambda parameter as non-local returns forbidden. This is used for lambdas
 * that are passed as arguments to an inline function but are executed in a different context, such as being
 * passed to another execution scope where the inline function cannot control its execution directly.
 * The crossinline keyword ensures that these lambdas cannot use non-local returns, meaning they cannot
 * make the enclosing function return.
 *
 * In this example:
 * performOperation is an inline function that takes three lambda parameters.
 * operation1 is a regular inline parameter and will be inlined at the call site.
 * operation2 is marked with noinline, meaning it won't be inlined, allowing it to be stored or passed around as a reference.
 * operation3 is marked with crossinline, making it possible to use in a context where the lambda is executed in a different
 * scope (like inside a Runnable in this example), but it can't use a non-local return to exit performOperation.
 * Using these keywords effectively allows you to optimize your Kotlin code, especially when working with higher-order
 * functions and lambdas, by reducing overhead and ensuring proper control flow and behavior.
 */
inline fun performOperation(
    operation1: () -> Unit,
    noinline operation2: () -> Unit,
    crossinline operation3: () -> Unit
) {
    println("Before operation1")
    operation1() // This will be inlined
    println("After operation1 and before operation2")

    // operation2 is not inlined, can be passed further or stored
    val storedOperation2 = operation2
    storedOperation2()

    println("After operation2 and before operation3")

    // operation3 is inlined but cannot return from performOperation
    val wrapper = Runnable { operation3() }
    wrapper.run()
}

fun main() {
    performOperation(
        operation1 = { println("Executing operation1") },
        operation2 = { println("Executing operation2") },
        operation3 = {
            println("Executing operation3")
            // Attempting to return from performOperation here would result in a compile error
            // return
        }
    )
}
