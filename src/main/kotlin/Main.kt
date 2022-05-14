fun main(args: Array<String>) {
    println("Welcome")
    val array = intArrayOf(1,2,3,4,5,6)
    println(containsDuplicate1(array).toString())
    println(containsDuplicate2(array).toString())
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