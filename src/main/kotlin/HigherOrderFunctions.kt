fun calculate(x: Int, y: Int, operation: (Int, Int) -> Int): Int {
    return operation(x, y)
}

fun sum(x: Int, y: Int) = x + y

fun operation(): (Int) -> Int {
    return ::square
}

fun square(x: Int) = x * x

fun <A, B, R> partial(a: A, function: (A, B) -> R): (B) -> R = { b: B -> function(a, b) }

fun main() {

    // Taking Functions as Parameters
    val sumResult = calculate(4, 5, ::sum)
    val mulResult = calculate(4, 5) { a, b -> a * b }

    println("sumResult $sumResult, mulResult $mulResult")

    // Returning Functions
    val func = operation()
    println(func(2))

    // With generics
    val addToFive = partial(5, ::sum)
    val result = addToFive(3)
    println(result)
}