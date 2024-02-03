fun main() {
    // Operator Functions
    val str = "Always forgive your enemies; nothing annoys them so much."
    println(str[0..14])
}

operator fun String.get(range: IntRange) = substring(range)

// operator fun Int.times(str: String) = str.repeat(this)
// println(2 * "Bye ")

