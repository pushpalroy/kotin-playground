import kotlin.reflect.KProperty

class Example {
    var p: String by Delegate()

    override fun toString() = "Example Class"
}

class Delegate {
    operator fun getValue(thisRef: Any?, prop: KProperty<*>): String {
        return "$thisRef, thank you for delegating '${prop.name}' to me!"
    }

    operator fun setValue(thisRef: Any?, prop: KProperty<*>, value: String) {
        println("$value has been assigned to ${prop.name} in $thisRef")
    }
}

class LazySample {
    init {
        println("created!")
    }

    // If you want thread safety, use blockingLazy() instead: it guarantees that the values
    // will be computed only in one thread and that all threads will see the same value.
    val lazyStr: String by lazy {
        println("computed!")
        "my lazy"
    }
}

/**
 * Kotlin provides a mechanism of delegated properties that allows delegating the calls of the property
 * set and get methods to a certain object. The delegate object in this case should have the method getValue.
 * For mutable properties, you'll also need setValue.
 */
fun main() {
    val example = Example()
    println(example.p)
    example.p = "NEW"

    // Standard delegates
    // The Kotlin standard library contains a bunch of useful delegates, like lazy, observable, and others.
    // You may use them as is. For example lazy is used for lazy initialization.
    val sample = LazySample()
    println("lazyStr = ${sample.lazyStr}")
    println(" = ${sample.lazyStr}")
}