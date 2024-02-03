fun main() {
    // Infix functions
    println(2 times "Bye ")

    val myPair = "Pushpal" onto "Roy"
    println(myPair)
}

infix fun Int.times(str: String) = str.repeat(this)

infix fun String.onto(other: String) = Pair(this, other)