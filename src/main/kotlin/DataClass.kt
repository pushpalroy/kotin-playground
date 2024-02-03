/**
 * Defines a data class with the data modifier.
 * Override the default equals method by declaring users equal if they have the same id.
 * Method toString is auto-generated, which makes println output look nice.
 * Our custom equals considers two instances equal if their ids are equal.
 * Data class instances with exactly matching attributes have the same hashCode.
 * Auto-generated copy function makes it easy to create a new instance.
 * copy creates a new instance, so the object and its copy have distinct references.
 * When copying, you can change values of certain properties. copy accepts arguments in the same order as the class constructor.
 * Use copy with named arguments to change the value despite the properties order.
 * Auto-generated componentN functions let you get the values of properties in the order of declaration.
 */
data class User(val name: String, val id: Int) {
    override fun equals(other: Any?) =
        other is User && other.id == this.id
}

fun main() {
    val user = User("Alex", 1)
    println(user)

    val secondUser = User("Alex", 1)
    val thirdUser = User("Max", 2)

    println("user == secondUser: ${user == secondUser}")
    println("user == thirdUser: ${user == thirdUser}")

    // hashCode() function
    println(user.hashCode())
    println(secondUser.hashCode())
    println(thirdUser.hashCode())

    // copy() function
    println(user.copy())
    println(user === user.copy())
    println(user.copy("Max"))
    println(user.copy(id = 3))

    println("name = ${user.component1()}")
    println("id = ${user.component2()}")
}