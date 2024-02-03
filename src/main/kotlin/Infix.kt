infix fun Int.times(str: String) = str.repeat(this)

infix fun String.onto(other: String) = Pair(this, other)