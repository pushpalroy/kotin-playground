/**
 * The tailrec keyword in Kotlin is used to mark a function as tail recursive.
 * A tail recursive function is a special case of recursion that allows the compiler to optimize the recursive
 * calls to avoid stack overflow errors for deep recursion. This optimization is called tail call optimization (TCO),
 * where the compiler transforms the recursive calls into a loop under the hood.
 *
 * To be eligible for tail recursion optimization, the recursive call must be the last operation in the function.
 * Here's a simple example to illustrate the use of the tailrec keyword in Kotlin with a function that calculates
 * the factorial of a number in a tail-recursive manner.
 *
 * Using the tailrec keyword allows this factorial function to compute large values without risking a stack
 * overflow error, because the Kotlin compiler optimizes the tail-recursive calls to iterative loops internally.
 */
tailrec fun factorial(n: Long, accumulator: Long = 1): Long {
    return if (n <= 1) accumulator
    else factorial(n - 1, n * accumulator)
    // The recursive call to factorial is the last operation performed in the function
}

fun main() {
    val number = 10L
    val result = factorial(number)
    println("Factorial of $number is: $result")
}
