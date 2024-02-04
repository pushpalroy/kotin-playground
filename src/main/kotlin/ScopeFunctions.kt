fun getNullableLength(ns: String?) {
    println("for \"$ns\":")
    ns?.run {
        println("\tis empty? " + isEmpty())
        println("\tlength = $length")
        length
    }
}

fun main() {
    // Let
    // When called on an object, let executes the given block of code and returns the result of its last expression.
    // The object is accessible inside the block by the reference it (by default) or a custom name.
    val empty = "test".let {
        print(it)
        it.isEmpty()
    }
    println(" is empty: $empty")

    // Run
    // Like let, run is another scoping function from the standard library.
    // Basically, it does the same: executes a code block and returns its result.
    // The difference is that inside run the object is accessed by this.
    // This is useful when you want to call the object's methods rather than pass it as an argument.
    getNullableLength(null)
    getNullableLength("")
    getNullableLength("some string with Kotlin")

    // With
    // A non-extension function that can access members of its argument concisely: you can omit the instance name when referring to its members.
    with(people.first()) {
        println("$name:$city")
    }

    // Apply
    // Executes a block of code on an object and returns the object itself.
    // Inside the block, the object is referenced by this.
    // This function is handy for initializing objects.
    val jake = Person()
    val stringDescription = jake.apply {
        name = "Jake"
        city = "Regina"
    }.toString()
    println(stringDescription)

    // Also
    // Works like apply: it executes a given block and returns the object called.
    // Inside the block, the object is referenced by it, so it's easier to pass it as an argument.
    // This function is handy for embedding additional actions, such as logging in call chains.
    val tom = Person("Tom", "Android developer")
        .also {
            // writeCreationLog(it)
            println(it)
        }
    println(tom)
}