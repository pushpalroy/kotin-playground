interface SoundBehavior {
    fun makeSound()
}

class ScreamBehavior(private val n: String) : SoundBehavior {
    override fun makeSound() = println("${n.uppercase()} !!!")
}

class RockAndRollBehavior(private val n: String) : SoundBehavior {
    override fun makeSound() = println("I'm The King of Rock 'N' Roll: $n")
}

class TomAraya(n: String) : SoundBehavior by ScreamBehavior(n)
class ElvisPresley(n: String) : SoundBehavior by RockAndRollBehavior(n)

/**
 * The Delegation pattern has proven to be a good alternative to implementation inheritance,
 * and Kotlin supports it natively requiring zero boilerplate code.
 */
fun main() {
    val tomAraya = TomAraya("Thrash Metal")
    tomAraya.makeSound()

    val elvisPresley = ElvisPresley("Dancin' to the Jailhouse Rock.")
    elvisPresley.makeSound()
}