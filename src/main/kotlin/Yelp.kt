import java.util.HashMap

// Question was to sort businesses based on location in descending order alphabatically also if same business exist on location then sort them addionally in desc order
//output should be
//Starbucks, berlin, 2 (count)
//Bamboo,Berlin, 1
//Zoo, Berlin, 1

fun main() {
    val b1 = Business("Starbucks", "Berlin", 1)

    val b2 = Business("Starbucks", "Berlin", 1)


    val b3 = Business("Starbucks", "Berlin", 3)

    val b6 = Business("Bamboo", "Berlin", 2)


    val b4 = Business("Kfc", "Munich", 4)


    val b5 = Business("Zoo", "Berlin", 5)

    val business = listOf(b1, b2, b3, b4, b5, b6)

    println(filterAndSortBusinesses(business, "Berlin"))
}

data class Business(val name:String, val location: String, val id: Int)

fun filterAndSortBusinesses(
    businesses: List<Business>,
    location: String
): List<Business> {
    val map: HashMap<Int, Business> = HashMap<Int, Business>()
    for (b in businesses) {
        if (b.location == location) {
            map[b.id] = b
        }
    }
    map.values.toList().sortedBy { it.name }
    return map.values.toList()
}
