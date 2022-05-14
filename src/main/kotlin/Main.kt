fun main(args: Array<String>) {
    println("Welcome")
    val array = intArrayOf(1,2,3,4,5,6)
    println(containsDuplicate1(array).toString())
    println(containsDuplicate2(array).toString())
    println("isAnagram1 " + isAnagram1("cat","tac").toString())
    println("isAnagram2 " + isAnagram2("anagram","nagaram").toString())
}

//1. Easy -> Given an integer array nums, return true if any value appears at least twice in the array, and return false if every element is distinct.
// SOLUTION 1
fun containsDuplicate1(nums: IntArray): Boolean {
    val numbers = HashSet<Int>()
    for(num in nums) {
        if(numbers.contains(num)) return true
        numbers.add(num)
    }

    return false
}

// SOLUTION 2
fun containsDuplicate2(nums: IntArray): Boolean {
    val set = nums.toHashSet()
    if(nums.size == set.size) return false
    return true
}

//<------------------------------------------------------------------------------------------------------->

//2. Easy -> Given two strings s and t, return true if t is an anagram of s, and false otherwise.
// SOLUTION 1
fun isAnagram1(s: String, t: String): Boolean {

    if(s.length != t.length) return false

    val sCharArray = s.toCharArray()
    val tCharArray = t.toCharArray()

    tCharArray.sort()
    sCharArray.sort()

    return sCharArray.contentEquals(tCharArray)
}

// SOLUTION 2
fun isAnagram2(s: String, t: String): Boolean {

    val count = IntArray(26)

    if(s.length != t.length) return false

    val sCharArray = s.toCharArray()
    val tCharArray = t.toCharArray()

    for(i in sCharArray.indices) {
        count[sCharArray[i] - 'a']++
        count[tCharArray[i] - 'a']--
    }

    count.forEach {
        if(it !=0) return false
    }

    return true
}

