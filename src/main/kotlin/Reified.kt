/**
 * Reference: https://blog.mindorks.com/what-are-reified-types-in-kotlin/
 *
 * If a function is marked as inline, then wherever the function is called,
 * the compiler will paste the whole body of the function there.
 * Since we are using the inline function here, the code of the function will be copied and pasted wherever
 * we will call the function. Also, the compiler is replacing the type T with the actual type i.e. String or Int
 */
inline fun <reified T> genericsExample(value: T) {
    println(value)
    /**
     * To access the information about the type of class, we use a keyword called reified in Kotlin.
     * In order to use the reified type, we need to use the inline function .
     */
    println("Type of T: ${T::class.java}")
}

/**
 * Another usage: We want to keep the name, number, and type of arguments to the function as same but the return type as different.
 * A reified keyword can be also used for returning different data types(Int and String in our example) from the same function.
 */
inline fun <reified T> showMessage(marks: Int): T {
    return when (T::class) {
        Int::class -> marks as T
        String::class -> "Congratulations! you scored more than 90%" as T
        else -> "Please enter valid type" as T
    }
}

fun main() {
    genericsExample("Learning Generics!")
    genericsExample(100)

    val intMarks: Int = showMessage(70)
    val stringMessage: String = showMessage(95)
    println("Your marks: $intMarks \nMessage: $stringMessage")
}