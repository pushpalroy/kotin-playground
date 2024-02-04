val A = listOf("a", "b", "c")
val B = listOf(1, 2, 3, 4)

// Merges them into a list of pairs. The infix notation is used here.
val resultPairs = A zip B

// Merges them into a list of String values by the given transformation.
val resultReduce = A.zip(B) { a, b -> "$a$b" }

val numbersList = listOf(1, -2, 3, -4, 5, -6)

// Splits numbers into a Pair of lists with even and odd numbers.
val evenOdd = numbersList.partition { it % 2 == 0 }

data class Person(var name: String = "", var city: String = "", val phone: String = "")

val people = listOf(
    Person("John", "Boston", "+1-888-123456"),
    Person("Sarah", "Munich", "+49-777-789123"),
    Person("Svyatoslav", "Saint-Petersburg", "+7-999-456789"),
    Person("Vasilisa", "Saint-Petersburg", "+7-999-123456")
)

fun main() {

    // Zip
    println(resultPairs)
    println(resultReduce)

    // Partition
    // Splits numbers into two lists with positive and negative numbers.
    // Pair destructuring is applied here to get the Pair members
    val (positives, negatives) = numbersList.partition { it > 0 }
    println(evenOdd)
    println(positives)
    println(negatives)

    // AssociateBy, GroupBy

    // Builds a map from phone numbers to their owners' information. it.phone is the keySelector here.
    // The valueSelector is not provided, so the values of the map are Person objects themselves.
    val phoneBook = people.associateBy { it.phone }
    println("PhoneBook: $phoneBook")

    // Builds a map from phone numbers to the cities where owners live.
    // Person::city is the valueSelector here, so the values of the map contain only cities.
    val cityBook = people.associateBy(Person::phone, Person::city)
    println("CityBook: $cityBook")

    // Builds a map that contains cities and people living there.
    // The values of the map are lists of person names.
    val peopleCities = people.groupBy(Person::city, Person::name)
    println("PeopleCities: $peopleCities")

    // Builds a map that contains cities and the last person living there.
    // The values of the map are names of the last person living in each city.
    val lastPersonCity = people.associateBy(Person::city, Person::name)
    println("LastPersonCity: $lastPersonCity")
}