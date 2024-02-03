val numbers = listOf(1, 2, 3, 4)

/**
 * reduce operates on a collection and takes a binary operation function as a parameter.
 * It accumulates the collection's elements into a single value by applying the operation
 * from left to right to the current accumulator value and each element. Note that reduce
 * throws an exception if called on an empty collection because it requires at least one
 * element to function properly.
 *
 * Syntax: reduce(operation: (acc: T, T) -> T): T
 */
val reduceResult = numbers.reduce { acc, i -> acc * i }

/**
 * fold is similar to reduce but takes an initial value as its first parameter.
 * This initial value is used as the initial accumulator value. fold can be used
 * on an empty collection since the initial value is provided.
 *
 * Syntax: fold(initial: R, operation: (acc: R, T) -> R): R
 */
val foldResult = numbers.fold(10) { acc, i -> acc + i }
